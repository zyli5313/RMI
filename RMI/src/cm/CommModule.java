package cm;

public class CommModule {
  
  public CommSender sender;
  public Serializer ser;
  
  public CommModule() {
    ser = new Serializer();
  }
  
  public void marshallSend(RMIMessage rmimsg) {
    if(rmimsg == null)  return;
    
    byte[] sendBytes = ser.serializeObj(rmimsg);
    sender = new CommSender(rmimsg.ror.ip, rmimsg.ror.port, rmimsg.type, sendBytes);
    
    sender.run();
    
    Util.printDebugInfo("marshalled and sent");
  }
  
  public RMIMessage unmarshall(byte[] recvBytes) {
    if(recvBytes == null) return null;
    
    RMIMessage rmimsg = (RMIMessage) ser.deserializeObj(recvBytes);
    return rmimsg;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
