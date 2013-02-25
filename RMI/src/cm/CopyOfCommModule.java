package cm;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

public class CopyOfCommModule {

  // public CommSender sender;
  private Socket ClientSocket;

  private DataOutputStream out;

  private DataInputStream in;

  private String hostname;

  private int port;

  public Serializer ser;

  public CopyOfCommModule() {
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
    byte[] sendBytes = ser.serializeObj(rmimsg);
    marshallSend(sendBytes);

    // recv reply
    byte[] recvBytes = unmarshallRecv();
    INVOMessage recmsg = (INVOMessage) ser.deserializeObj(recvBytes);
    
    // close 
    try {
      in.close();
      out.close();
      ClientSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return recmsg;
  }
  
  public byte[] unmarshallRecv() {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try {
      in = new DataInputStream(ClientSocket.getInputStream());
      
      byte buffer[] = new byte[1024];
      int s;
      byte[] bytearray = null;

      int cnt = 0;

      Util.printDebugInfo("SlaveListen: total num: " + cnt);

      for (; (s = in.read(buffer)) != -1;) {
        Util.printDebugInfo("SlaveListen: " + s);
        baos.write(buffer, 0, s);
        cnt += s;
      }
      Util.printDebugInfo("SlaveListen: total num: " + cnt);
      bytearray = baos.toByteArray();

      if (bytearray == null && cnt == 0) {
        System.err.println("Recv error!");
      }
      
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return baos.toByteArray();
  }

  public void marshallSend(byte[] msg) {
    try {
      out = new DataOutputStream(ClientSocket.getOutputStream());

    } catch (UnknownHostException e) {
      System.err.println("ByteSender: Don't know about host: " + hostname);
      System.exit(1);
    } catch (IOException e) {
      System.err.println("ByteSender: Couldn't get I/O for the connection to:" + hostname);
      System.exit(1);
    }
    if (msg != null) {

      try {
        for (byte b : msg) {
          out.writeByte(b);
          out.flush();
        }
      } catch (IOException e) {
        System.err.println(e);
        e.printStackTrace();
      }

      Util.printDebugInfo("finished");
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
