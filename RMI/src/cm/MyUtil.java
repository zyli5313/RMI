package cm;

public class MyUtil {

  public static boolean debug = true;
  public static String scope = "Not specified scope";
  
  public MyUtil(){
    
  }
  public MyUtil(String scope){
    this.scope = scope;
    
  }
  
  public void printDebugInfo(String s) {
    if (debug)
      System.out.println(scope + ": " + s);
  }
  
  // registry socket msg
  public static final String LOOKUP = "look up";
  public static final String REBIND = "rebind";
  public static final String WHO = "who are you?";
  public static final String IAMREG = "I am a simple registry.";
  public static final String FOUND = "found service";
  public static final String NOTFOUND = "not found service";
}
