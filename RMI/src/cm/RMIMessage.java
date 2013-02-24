package cm;

import java.io.Serializable;
import java.util.Arrays;

import ror.Remote440;
import ror.RemoteObjectRef;

public class RMIMessage implements Remote440 {

  public static final byte INV = 1;

  public static final byte RET = 2;

  public static final byte EX = 3;

  public static final int TYPELEN = 1;

  public byte type;

  public RemoteObjectRef ror;

  public String methodName;

  public Object[] args; // incorporate multiple types

  public Serializable ret;

  public Serializable ex;

  // invoke msg
  public RMIMessage(byte type, RemoteObjectRef ror, String methodName, Object[] args) {
    this.type = type;
    this.ror = ror;
    this.methodName = methodName;
    this.args = args;
    this.ret = null;
    this.ex = null;
  }

  // return and exception msg
  public RMIMessage(byte type, RemoteObjectRef ror, String methodName, Object[] args,
          Serializable sobj) {
    this.type = type;
    this.ror = ror;
    this.methodName = methodName;
    this.args = args;
    
    if(type == RET) {
      this.ret = sobj;
      this.ex = null;
    }
    else if(type == EX) {
      this.ex = sobj;
      this.ret = null;
    }
  }

}
