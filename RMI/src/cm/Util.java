package cm;

public class Util {

  public static boolean debug = true;
  public static void printDebugInfo(String s) {
    if (debug)
      System.out.println("ByteSender: " + s);
  }
  
  // registry socket msg
  public static final String LOOKUP = "look up";
  public static final String REBIND = "rebind";
  public static final String WHO = "who are you?";
  public static final String IAMREG = "I am a simple registry.";
  public static final String FOUND = "found service";
  public static final String NOTFOUND = "not found service";
}
