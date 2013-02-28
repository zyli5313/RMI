package testzip;
import java.io.*;

import java.lang.reflect.*;

import cm.CommModule;
import cm.INVOMessage;

import ror.RemoteObjectRef;

/**
 * ZipCodeServerImpl_stub: stub class for ZipCodeServerImpl
 * 
 * @author Zeyuan Li
 * */
public class ZipCodeServer_stub implements ZipCodeServer {
  private RemoteObjectRef ror;
  private CommModule cm = new CommModule();

  // this is a constructor.
  public ZipCodeServer_stub() {
  }

  // when this is called, marshalled data
  // should be sent to this remote object,
  // and reconstructed.
  public void initialise(ZipCodeList newlist) {
    Object[] args = new Object[1];
    args[0] = newlist;
    String[] argsType = new String[1];
    
    Method method = null;
    try {
      method = ZipCodeServer_stub.class.getMethod("initialise", ZipCodeList.class);
    } catch (SecurityException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }

    argsType[0] = method.getGenericParameterTypes()[0].toString();
    //String retType = method.getGenericReturnType().toString();
    String retType = null;
    
    INVOMessage invomsg = new INVOMessage(ror, "initialise", args, argsType, retType);
    // get return value
    INVOMessage recvmsg = cm.marsSendUnmarsRecv(invomsg);
    // TODO: if exception on return msg, some logic
    
  }

  // basic function: gets a city name, returns the zip code.
  public String find(String request) {
    Object[] args = new Object[1];
    args[0] = request;
    String[] argsType = new String[1];
    
    Method method = null;
    try {
      method = ZipCodeServer_stub.class.getMethod("find", String.class);
    } catch (SecurityException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }
    
    argsType[0] = method.getGenericParameterTypes()[0].toString();
    String retType = method.getGenericReturnType().toString();
    
    INVOMessage invomsg = new INVOMessage(ror, "find", args, argsType, retType);
    // get return value
    INVOMessage recvmsg = cm.marsSendUnmarsRecv(invomsg);
    
    return (String)recvmsg.getresult();
  }

  // this very short method should send the marshalled
  // whole list to the local site.
  public ZipCodeList findAll() {
//    Object[] args = new Object[0];
//    String[] argsType = new String[1];
    Object[] args = null;
    String[] argsType = null;
    
    Method method = null;
    try {
      
      method = ZipCodeServer_stub.class.getMethod("findAll", null);
    } catch (SecurityException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }
    
    String retType = method.getGenericReturnType().toString();
    
    INVOMessage invomsg = new INVOMessage(ror, "findAll", args, argsType, retType);
    // get return value
    INVOMessage recvmsg = cm.marsSendUnmarsRecv(invomsg);
    
    return (ZipCodeList) recvmsg.getresult();
  }

  // this method does printing in the remote site, not locally.
  public void printAll() {
//    Object[] args = new Object[0];
//    String[] argsType = new String[1];
//    argsType[0] = ((ParameterizedType)(void.class.getGenericSuperclass())).getActualTypeArguments()[0].toString();
//    String retType = ((ParameterizedType)(void.class.getGenericSuperclass())).getActualTypeArguments()[0].toString();
    Object[] args = null;
    String[] argsType = null;
    String retType = null;
    
    INVOMessage invomsg = new INVOMessage(ror, "printAll", args, argsType, retType);
    // get return value
    INVOMessage recvmsg = cm.marsSendUnmarsRecv(invomsg);
    
  }
  
  public RemoteObjectRef getRor() {
    return ror;
  }

  public void setRor(RemoteObjectRef ror) {
    this.ror = ror;
  }
}
