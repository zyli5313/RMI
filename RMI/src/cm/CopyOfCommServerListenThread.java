package cm;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.lang.reflect.Type;

import ror.RORtbl;
import ror.RemoteObjectRef;

public class CopyOfCommServerListenThread extends Thread{
      private Socket socket;
      private RORtbl tbl;
      private InputStream is;
      private Serializer ser;
      
      private MyUtil myutil = new MyUtil("CommServerListenThread");
      
      public CopyOfCommServerListenThread(Socket socket, RORtbl tbl){
        this.socket = socket;
        this.tbl = tbl;
      }
      
      
      public void serverhandler(byte[] bytearray){
       
        ser = new Serializer();
        INVOMessage invomsg = (INVOMessage) ser.deserializeObj(bytearray);
        if(invomsg.gettype() != 1){
          System.out.println("Error type in INVOMessage.");
          return;
        }
        
        RemoteObjectRef ror = invomsg.getror();
        String methodname = invomsg.getmethod();
        Object[] args = invomsg.getargs();
        String[] argtypes = invomsg.getargtype();
        String returntype = invomsg.getreturntype();
        
        // (4) gets the real object reference from tbl.
        Object calledObj = tbl.findObj(ror);

        
        // (5) Either:
        // -- using the interface name, asks the skeleton,
        // together with the object reference, to unmartial
        // and invoke the real object.
        // -- or do unmarshalling directly and involkes that
        // object directly.
        INVOMessage returnmsg = null;
        try {
          Method[] allMethods = calledObj.getClass().getDeclaredMethods();
          //Find the corresponding method
          //reason for not using getDeclaredMethod(name) is the type object hard to be reversed.
          for (Method m : allMethods) {
            String mname = m.getName();
            if (!mname.equals(methodname)
                || !(m.getGenericReturnType().toString().equals(returntype))) {
                continue;
            }
            Type[] pType = m.getGenericParameterTypes();
            if (pType.length != args.length){
                continue;
            }
            
            int unmatch = 0;
            for(int i = 0; i < args.length; i++){
              if(!(pType[i].toString().equals(argtypes[i]))){
                unmatch = 1;
                break;
              }
            }
            
            if(unmatch == 1)continue;
            
            myutil.printDebugInfo("invoking "+ mname);
            
            try {
                m.setAccessible(true);
                // (6) receives the return value, which (if not marshalled
                Object o = m.invoke(calledObj, args);
                returnmsg  = new INVOMessage((Serializable)o);

            // Handle any exceptions thrown by method to be invoked.
            } catch (InvocationTargetException x) {
                System.out.println("invocation of " + mname + "failed.");
            }
          }

        } catch (IllegalAccessException x) {
            x.printStackTrace();
        }
        
        
        
        //marshal the return value here and send it out to the
        // the source of the invoker.
        if(returnmsg == null){
          System.out.println("no return object from invoked method: " + methodname);
          return;
        }
        
        byte[] returnbytes = ser.serializeObj(returnmsg);
        
        OutputStream os;
        DataOutputStream out;
        try {
          os = socket.getOutputStream();
          out = new DataOutputStream(os);
          for (byte b : returnbytes) {
            out.writeByte(b);
            out.flush();
          }
          
          
          out.close();
          os.close();
        } catch (IOException e) {
          e.printStackTrace();
          myutil.printDebugInfo("sending return bytes error.");
        }
        
        // (7) closes the socket.
      }
      
      
      public void run(){
        try {
          // (1) receives an invocation request.
          is = socket.getInputStream();
          
          // (2) creates a socket and input/output streams.
          DataInputStream dis = new DataInputStream(is);

          ByteArrayOutputStream baos = new ByteArrayOutputStream();
          byte buffer[] = new byte[1024];
          int s;
          byte[] bytearray = null;

          int cnt = 0;

          for (; (s = dis.read(buffer)) != -1;) {
            System.out.println("SlaveListen: " + s);
            baos.write(buffer, 0, s);
            cnt += s;
          }
          bytearray = baos.toByteArray();
          
          dis.close();
          is.close();

          if (bytearray != null && cnt != 0)
            serverhandler(bytearray);


        } catch (IOException e) {
          e.printStackTrace();
        }
      };
}
