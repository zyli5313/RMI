package cm;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.UUID;

/**
 * Serializer can do serialization and deserialization
 * 
 * @author Zeyuan Li
 * */
public class Serializer {
  private boolean debug = false;

  public void printDebugInfo(String s) {
    if (debug)
      System.out.println("Serializer: " + s);
  }

  public byte[] serializeObj(Serializable obj) {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    ObjectOutput out = null;
    byte[] outbytes = null;

    printDebugInfo(" start");

    try {
      out = new ObjectOutputStream(bos);
      out.writeObject(obj);
      out.flush();
      outbytes = bos.toByteArray();

      bos.close();
      out.close();

    } catch (FileNotFoundException e1) {
      System.err.println(e1);
      e1.printStackTrace();
    } catch (IOException e1) {
      System.err.println(e1);
      e1.printStackTrace();
    }
    return outbytes;
  }

  public Object deserializeObj(byte[] objbytes) {
    Serializable obj = null;
    ByteArrayInputStream bis = new ByteArrayInputStream(objbytes);
    ObjectInput in = null;

    if (objbytes != null) {
      try {
        in = new ObjectInputStream(bis);
        obj = (Serializable) in.readObject();

      } catch (IOException e) {
        System.err.println("Deserialize IOException");
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        System.err.println("Deserialize ClassNotFoundException ");
        e.printStackTrace();
      }
    } else {
      System.out.println("Deserialize failed: obj byte array null");
    }
    if (obj == null)
      System.out.println("Deserialized result null");

    try {
      bis.close();
      in.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return obj;
  }


}
