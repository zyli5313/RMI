package cm;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * ByteSender: send bytes in socket communication
 * 
 * @author Zeyuan Li
 * */
public class CommSender {

  private Socket ClientSocket;

  private String hostname;

  private int port;

  private OutputStream os;

  DataOutputStream out;

  private byte[] msg;

  private byte type;

  // hostname, port, type, info
  public CommSender(String hostname, int port, byte type, byte[] msg) {
    this.hostname = hostname;
    this.msg = msg;
    this.type = type;
    this.port = port;
    this.ClientSocket = null;
  }

  public CommSender(Socket socket, byte type, byte[] msg) {
    this.ClientSocket = socket;
    this.msg = msg;
    this.type = type;
  }

  public void run() {
    out = null;
    if (ClientSocket != null) {
      try {
        os = ClientSocket.getOutputStream();
        out = new DataOutputStream(os);
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    } else {
      try {
        ClientSocket = new Socket(hostname, port);
        os = ClientSocket.getOutputStream();
        out = new DataOutputStream(os);

      } catch (UnknownHostException e) {
        System.err.println("ByteSender: Don't know about host: " + hostname);
        System.exit(1);
      } catch (IOException e) {
        System.err.println("ByteSender: Couldn't get I/O for the connection to:" + hostname);
        System.exit(1);
      }
    }
    if (msg != null) {
      Util.printDebugInfo("Sending: " + (msg.length+1));
      // <type + msg>
      byte[] sendingbarray = new byte[1 + msg.length];
      sendingbarray[0] = type;
      System.arraycopy(msg, 0, sendingbarray, 1, msg.length);
      // write type

      try {

        for (byte b : sendingbarray) {
          out.writeByte(b);
          out.flush();
        }
      } catch (IOException e) {
        System.err.println(e);
        e.printStackTrace();
      }
    }
    // IMP: close
    try {
      out.close();
      os.close();
      ClientSocket.close();
      Util.printDebugInfo("close socket");
    } catch (IOException e) {
      e.printStackTrace();
    }
    Util.printDebugInfo("finished");
  }

  public Socket socket() {
    if (ClientSocket == null) {
      Util.printDebugInfo("null socket");
    }
    return this.ClientSocket;
  }

}
