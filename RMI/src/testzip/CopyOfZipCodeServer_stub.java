package testzip;
import java.io.*;
import java.util.List;

import cm.CommModule;
import cm.RMIMessage;

import ror.RemoteObjectRef;

/**
 * ZipCodeServerImpl_stub: stub class for ZipCodeServerImpl
 * 
 * */
public class CopyOfZipCodeServer_stub implements ZipCodeServer {
  private RemoteObjectRef ror;
  private CommModule cm = new CommModule();

  // this is a constructor.
  public CopyOfZipCodeServer_stub() {
  }

  // when this is called, marshalled data
  // should be sent to this remote object,
  // and reconstructed.
//  public void initialise(ZipCodeList newlist) {
//    Object[] args = new Object[1];
//    args[0] = newlist;
//    RMIMessage rmimsg = new RMIMessage(RMIMessage.INV, ror, "initialise", args);
//    // get return value
//    RMIMessage recvmsg = cm.marsSendUnmarsRecv(rmimsg);
//    // TODO: if exception on return msg, some logic
//    
//  }
//
//  // basic function: gets a city name, returns the zip code.
//  public String find(String request) {
//    Object[] args = new Object[1];
//    args[0] = request;
//    RMIMessage rmimsg = new RMIMessage(RMIMessage.INV, ror, "find", args);
//    // get return value
//    RMIMessage recvmsg = cm.marsSendUnmarsRecv(rmimsg);
//    
//    return (String)recvmsg.ret;
//  }
//
//  // this very short method should send the marshalled
//  // whole list to the local site.
//  public ZipCodeList findAll() {
//    Object[] args = new Object[0];
//    RMIMessage rmimsg = new RMIMessage(RMIMessage.INV, ror, "findAll", args);
//    // get return value
//    RMIMessage recvmsg = cm.marsSendUnmarsRecv(rmimsg);
//    
//    return (ZipCodeList) recvmsg.ret;
//  }
//
//  // this method does printing in the remote site, not locally.
//  public void printAll() {
//    Object[] args = new Object[0];
//    RMIMessage rmimsg = new RMIMessage(RMIMessage.INV, ror, "printAll", args);
//    // get return value (no return value)
//    RMIMessage recvmsg = cm.marsSendUnmarsRecv(rmimsg);
//    
//  }
  
  public RemoteObjectRef getRor() {
    return ror;
  }

  public void setRor(RemoteObjectRef ror) {
    this.ror = ror;
  }

  @Override
  public void initialise(ZipCodeList newlist) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public String find(String city) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ZipCodeList findAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void printAll() {
    // TODO Auto-generated method stub
    
  }
}
