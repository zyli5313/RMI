package test;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

import util.Pair;
import util.RMIMessage;

public class TestServer implements Runnable{

  private boolean debug = true;
  
  private int port = 4444;
  
  public void printDebugInfo(String s) {
    if (debug)
      System.out.println("CommSlaveListenThread: " + s);
  }
  
  public void testServer() {
    try {
      ServerSocket socket = new ServerSocket(port);
      Socket insocket = socket.accept( );
      
      InputStream is = insocket.getInputStream();
      DataInputStream dis = new DataInputStream(is);

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      byte buffer[] = new byte[1024];
      int s;
      byte[] bytearray = null;

      int cnt = 0;

      printDebugInfo("SlaveListen: total num: " + cnt);

      for (; (s = dis.read(buffer)) != -1;) {
        printDebugInfo("SlaveListen: " + s);
        baos.write(buffer, 0, s);
        cnt += s;
      }
      printDebugInfo("SlaveListen: total num: " + cnt);
      bytearray = baos.toByteArray();

      if (bytearray != null && cnt != 0) {
        byte[] res = baos.toByteArray();
        RMIMessage msg = new RMIMessage();
        Pair<Serializable, String[]> pair = (Pair<Serializable, String[]>)msg.unmarshallInvoke(res);
        Hello hl = (Hello)pair.getLeft();
        hl.say(pair.getRight());
      }
        
      baos.close();
      dis.close();
      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  @Override
  public void run() {
    // TODO Auto-generated method stub
    testServer();
  }
  
  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Thread ts = new Thread(new TestServer());
    ts.start();
    Thread t = new Thread(new TestClient());
    t.start();
  }


}
