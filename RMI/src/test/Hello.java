package test;

import java.io.Serializable;

public class Hello implements Serializable{
  
  public void say(String[] args) {
    System.out.print("Hello " );
    for(String s : args)
      System.out.print(s + " ");
  }

}
