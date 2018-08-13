
/**
 * This class contains the program's main method
 * @author Andres Beltran
 * @version 1.0
 */

public class Main {
  
   /** The main method
    * @param args The Command Line arguments' array
    */
  public static void main (String[] args){
 
    try{
      
      WordPath path = new WordPath(args[0]);
      path.getWordPath();
      
    }catch(Exception e){
      
      System.out.print("Invalid file!\n"
                      + "To run the program: java -jar WordPath.jar [SmallWordIndex, Length3WordIndex, or LargeWordIndex]");
      
    }
  }
  
}