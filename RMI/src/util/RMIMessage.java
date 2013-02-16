package util;


import java.io.Serializable;
import java.util.Arrays;

public class RMIMessage {
  
  private Serializer ser = null;
  
  public static final byte INV = 1;
  public static final byte RET = 2;
  public static final byte EX = 3;
  public static final int TYPELEN = 1;
  
  public RMIMessage() {
    ser = new Serializer();
  }
  
  /**
   * marshall method invocation
   * */
  public byte[] marshallInvoke(Serializable obj, String[] args) {
    if(obj == null)
      System.err.println("obj null pointer");
     
    Pair<Serializable, String[]> sendpair = new Pair<Serializable, String[]>(obj, args);
    byte[] objarr = ser.serializeObj(sendpair);
    byte[] res = new byte[objarr.length + TYPELEN];
    res[0] = INV;
    System.arraycopy(objarr, 0, res, TYPELEN, objarr.length);
    return res;
  }
  
  /**
   * marshall return value
   * */
  public byte[] marshallRet(Serializable obj) {
    if(obj == null)
      System.err.println("obj null pointer");
    byte[] objarr = ser.serializeObj(obj);
    byte[] res = new byte[objarr.length + TYPELEN];
    res[0] = RET;
    System.arraycopy(objarr, 0, res, TYPELEN, objarr.length);
    return res;
  }
  
  /**
   * marshall exception (exception implements serializable)
   * */
  public byte[] marshallEx(byte type, Exception ex) {
    if(ex == null)
      System.err.println("obj null pointer");
    byte[] objarr = ser.serializeObj(ex);
    byte[] res = new byte[objarr.length + TYPELEN];
    res[0] = EX;
    System.arraycopy(objarr, 0, res, TYPELEN, objarr.length);
    return res;
  }
  
  
  /**
   * unmarshall method invocation
   * */
  public Pair<Serializable, String[]> unmarshallInvoke(byte[] bytes) {
    if(bytes == null || bytes.length == 0)
      System.err.println("input null or 0 length");
    
    byte type = bytes[0];
    byte[] content = Arrays.copyOfRange(bytes, 1, bytes.length);
    
    Pair<Serializable, String[]> pair = (Pair<Serializable, String[]>)ser.deserializeObj(content);
    return pair;
  }
  
  /**
   * unmarshall return value
   * */
  public Serializable unmarshallRet(byte[] bytes) {
    if(bytes == null || bytes.length == 0)
      System.err.println("input null or 0 length");
    
    byte type = bytes[0];
    byte[] content = Arrays.copyOfRange(bytes, 1, bytes.length);
    
    Serializable ret = (Serializable)ser.deserializeObj(content);
    return ret;
  }
  
  /**
   * unmarshall exception
   * */
  public Exception unmarshallEx(byte[] bytes) {
    if(bytes == null || bytes.length == 0)
      System.err.println("input null or 0 length");
    
    byte type = bytes[0];
    byte[] content = Arrays.copyOfRange(bytes, 1, bytes.length);
    
    Exception ret = (Exception)ser.deserializeObj(content);
    return ret;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
