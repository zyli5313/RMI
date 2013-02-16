package ror;

import java.util.*;

// This is simple. ROR needs a new object key for each remote object (or its skeleton). 
// This can be done easily, for example by using a counter.
// We also assume a remote object implements only one interface, which is a remote interface.

public class RORtbl {
  // I omit all instance variables. you can use hash table, for example.
  // The table would have a key by ROR.
  private HashMap<RemoteObjectRef, Object> map = null;
  private static int cnt = 0;

  // make a new table.
  public RORtbl() {
    map = new HashMap<RemoteObjectRef, Object>();
  }

  // add a remote object to the table.
  // Given an object, you can get its class, hence its remote interface.
  // Using it, you can construct a ROR.
  // The host and port are not used unless it is exported outside.
  // In any way, it is better to have it for uniformity.
  public void addObj(String host, int port, Object o) {
    if (o == null)
      return;

    // assume object o implements only one interface
    for (Class c : o.getClass().getInterfaces()) {
      RemoteObjectRef ror = new RemoteObjectRef(host, port, cnt, c.getName());
      map.put(ror, o);
      cnt++;
    }
  }

  // given ror, find the corresponding object.
  public Object findObj(RemoteObjectRef ror) {
    // if you use a hash table this is easy.
    if(map.containsKey(ror))
      return map.get(ror);
    else
      return null;
  }
}
