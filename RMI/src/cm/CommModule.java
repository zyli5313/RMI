package cm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class CommModule {

  // public CommSender sender;
  private Socket ClientSocket;

  private DataOutputStream out;

  private DataInputStream in;

  private String hostname;

  private int port;

  public Serializer ser;

  public CommModule() {
    ser = new Serializer();
  }

  // public void marshallSend(RMIMessage rmimsg) {
  // if(rmimsg == null) return;
  //
  // byte[] sendBytes = ser.serializeObj(rmimsg);
  // sender = new CommSender(rmimsg.ror.ip, rmimsg.ror.port, rmimsg.type, sendBytes);
  //
  // sender.run(); // close
  //
  // Util.printDebugInfo("marshalled and sent");
  // }

  public INVOMessage marsSendUnmarsRecv(INVOMessage rmimsg) {
    hostname = rmimsg.ror.ip;
    port = rmimsg.ror.port;
    try {
      ClientSocket = new Socket(hostname, port);
    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // send invoke
    //byte[] sendBytes = ser.serializeObj(rmimsg);
    marshallSend(rmimsg);

    // recv reply
    INVOMessage recmsg = null;
    if(rmimsg.getreturntype() != null && !rmimsg.getreturntype().equals("void"))
      recmsg = unmarshallRecv();
    
    // close 
    try {
      if(in != null)
        in.close();
      if(out != null)
        out.close();
      if(ClientSocket != null)
        ClientSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return recmsg;
  }
  
  public INVOMessage unmarshallRecv() {
    ObjectInputStream in = null;
    INVOMessage recvmsg = null;
    try {
      in = new ObjectInputStream(ClientSocket.getInputStream());  
      
      recvmsg = (INVOMessage)in.readObject();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    
    return recvmsg;
  }

  public void marshallSend(INVOMessage msg) {
    ObjectOutputStream out = null;
    try {
      out = new ObjectOutputStream(ClientSocket.getOutputStream());   

      Util.printDebugInfo(msg.toString());
      
      out.writeObject(msg);
      
      Util.printDebugInfo("finished");
    } catch (UnknownHostException e) {
      System.err.println("ByteSender: Don't know about host: " + hostname);
      System.exit(1);
    } catch (IOException e) {
      System.err.println("ByteSender: Couldn't get I/O for the connection to:" + hostname);
      System.exit(1);
    }

  }

//  public RMIMessage unmarshall(byte[] recvBytes) {
//    if (recvBytes == null)
//      return null;
//
//    RMIMessage rmimsg = (RMIMessage) ser.deserializeObj(recvBytes);
//    return rmimsg;
//  }


}
