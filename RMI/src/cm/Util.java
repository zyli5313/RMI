package cm;

public class Util {

  public static boolean debug = true;
  public static void printDebugInfo(String s) {
    if (debug)
      System.out.println("ByteSender: " + s);
  }
}
