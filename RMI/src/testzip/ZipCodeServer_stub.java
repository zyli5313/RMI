package testzip;
import java.io.*;

import java.lang.reflect.*;

import cm.CommModule;
import cm.INVOMessage;

import ror.RemoteObjectRef;

/**
 * ZipCodeServerImpl_stub: stub class for ZipCodeServerImpl
 * 
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
    String[] argsType = null;
    String retType = null;
//    String[] argsType = new String[1];
//    argsType[0] = ZipCodeList.class.getGenericSuperclass().toString();
//    String retType = void.class.getGenericSuperclass().toString();
    
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
    argsType[0] = String.class.getGenericSuperclass().toString();
    String retType = ((ParameterizedType)(String.class.getGenericSuperclass())).getActualTypeArguments()[0].toString();
    
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
//    argsType[0] = ((ParameterizedType)(void.class.getGenericSuperclass())).getActualTypeArguments()[0].toString();
    String retType = ((ParameterizedType)(ZipCodeList.class.getGenericSuperclass())).getActualTypeArguments()[0].toString();
    
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
