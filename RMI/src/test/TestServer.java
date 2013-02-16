package test;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

import cm.CommReceiver;
import cm.CommSender;
import cm.Pair;
import cm.RMIMessage;


public class TestServer implements Runnable {

  private boolean debug = true;

  private int port = 4444;

  public void printDebugInfo(String s) {
    if (debug)
      System.out.println("CommSlaveListenThread: " + s);
  }

  public void testServer() {
    CommReceiver recver = new CommReceiver();
    byte[] res = recver.recv();
    byte type = res[0];
    byte[] objarr = Arrays.copyOfRange(res, RMIMessage.TYPELEN, res.length);

    RMIMessage msg = new RMIMessage();
    Pair<Serializable, String[]> pair = (Pair<Serializable, String[]>) msg.unmarshallInvoke(objarr);
    Hello hl = (Hello) pair.getLeft();
    hl.say(pair.getRight());
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
