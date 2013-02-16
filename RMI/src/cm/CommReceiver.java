package cm;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

import test.Hello;

public class CommReceiver {

  private int port = 4444;

  public byte[] recv() {
    ByteArrayOutputStream baos = null;
      try {
        ServerSocket socket = new ServerSocket(port);
        Socket insocket = socket.accept( );
        
        InputStream is = insocket.getInputStream();
        DataInputStream dis = new DataInputStream(is);

        baos = new ByteArrayOutputStream();
        byte buffer[] = new byte[1024];
        int s;
        byte[] bytearray = null;

        int cnt = 0;

        Util.printDebugInfo("SlaveListen: total num: " + cnt);

        for (; (s = dis.read(buffer)) != -1;) {
          Util.printDebugInfo("SlaveListen: " + s);
          baos.write(buffer, 0, s);
          cnt += s;
        }
        Util.printDebugInfo("SlaveListen: total num: " + cnt);
        bytearray = baos.toByteArray();

        if (bytearray == null && cnt == 0) {
          System.err.println("Recv error!");
        }
          
        baos.close();
        dis.close();
        socket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      
      return baos.toByteArray();
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
