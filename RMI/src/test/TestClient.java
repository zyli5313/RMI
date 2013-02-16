package test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import cm.CommSender;
import cm.RMIMessage;


public class TestClient implements Runnable{
  
  
  private String host = "localhost";
  private int port = 4444;
  
  @Override
  public void run() {
    // TODO Auto-generated method stub
    Hello hl = new Hello();
    String[] args = {"12","34"};
    byte type = RMIMessage.INV;
    RMIMessage msg = new RMIMessage();
    
    byte[] res = msg.marshallInvoke(hl, args);
    CommSender sender = new CommSender(host, port, type, res);
    sender.run();
    System.out.println("client finish");
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
