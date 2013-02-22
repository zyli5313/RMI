package cm;


import java.io.Serializable;
import java.util.Arrays;

public class CopyOfRMIMessage {
  
  private Serializer ser = null;
  
  public static final byte INV = 1;
  public static final byte RET = 2;
  public static final byte EX = 3;
  public static final int TYPELEN = 1;
  
  public CopyOfRMIMessage() {
    ser = new Serializer();
  }
  
  /**
   * marshall method invocation
   * */
  public byte[] marshallInvoke(Serializable obj, String[] args) {
    if(obj == null)
      System.err.println("obj null pointer");
     
    Pair<Serializable, String[]> sendpair = new Pair<Serializable, String[]>(obj, args);
    // only obj byte array, type byte is added in ByteSender
    byte[] objarr = ser.serializeObj(sendpair);
    return objarr;
  }
  
  /**
   * marshall return value
   * */
  public byte[] marshallRet(Serializable obj) {
    if(obj == null)
      System.err.println("obj null pointer");
    byte[] objarr = ser.serializeObj(obj);
    return objarr;
  }
  
  /**
   * marshall exception (exception implements serializable)
   * */
  public byte[] marshallEx(byte type, Exception ex) {
    if(ex == null)
      System.err.println("obj null pointer");
    byte[] objarr = ser.serializeObj(ex);
    return objarr;
  }
  
  
  /**
   * unmarshall method invocation
   * */
  public Pair<Serializable, String[]> unmarshallInvoke(byte[] bytes) {
    if(bytes == null || bytes.length == 0)
      System.err.println("input null or 0 length");
    
//    byte type = bytes[0];
//    byte[] content = Arrays.copyOfRange(bytes, 1, bytes.length);
    
    Pair<Serializable, String[]> pair = (Pair<Serializable, String[]>)ser.deserializeObj(bytes);
    return pair;
  }
  
  /**
   * unmarshall return value
   * */
  public Serializable unmarshallRet(byte[] bytes) {
    if(bytes == null || bytes.length == 0)
      System.err.println("input null or 0 length");
    
//    byte type = bytes[0];
//    byte[] content = Arrays.copyOfRange(bytes, 1, bytes.length);
    
    Serializable ret = (Serializable)ser.deserializeObj(bytes);
    return ret;
  }
  
  /**
   * unmarshall exception
   * */
  public Exception unmarshallEx(byte[] bytes) {
    if(bytes == null || bytes.length == 0)
      System.err.println("input null or 0 length");
    
//    byte type = bytes[0];
//    byte[] content = Arrays.copyOfRange(bytes, 1, bytes.length);
    
    Exception ret = (Exception)ser.deserializeObj(bytes);
    return ret;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
