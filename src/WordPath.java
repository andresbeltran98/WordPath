
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

/**
 * This class represents a <i>WordPath</i>
 * @author Andres Beltran
 * @version 1.0
 */

public class WordPath {
  
  /**Stores a hash table that maps the word to its data */
  private Hashtable<String, WordData> table;
  
  /**Stores a <code>WordData</code> array that maps the word's index to its data */
  private WordData[] wordArray;
  
  
  /** Initializes a new WordPath object 
    * @param file The file to be parsed
    * @throws IOException if there is a problem with the file
    */
  public WordPath(String file) throws IOException{
      makeWordTable(file);
  }
  
  /** Initializes an empty WordPath instance (for Testing purposes)
    */
  public WordPath(){ 
  }
  
  /** Returns the number of lines in a file
    * @param reader The <code>Reader</code> object
    * @return The number of lines in a file
    * @throws IOException if there's a problem with the reader
    */
  public static int numLines(Reader reader) throws IOException{
    
    int counter = 0; //Counts the number of lines in the file
    BufferedReader br = new BufferedReader(reader);

    //Counts the number of lines
    while(br.readLine() != null)
      counter++;
    
    br.close();
   
    return counter; 
  }
  
  
  /** Sets the <code>table</code> and <code>wordArray</code> fields
    * @param file The file to be parsed
    * @throws IOException if there is an invalid file
    */
  public void makeWordTable(String file) throws IOException{
    
    FileReader reader = new FileReader(file);
    int capacity = numLines(reader); //Stores the number of words
    reader.close(); 
    
    Hashtable<String, WordData> table = new Hashtable<>();
    WordData[] wordArray = new WordData[capacity];
    
    BufferedReader br = new BufferedReader(new FileReader(file));
    
    for(int i = 0; i < capacity; i++) {
      WordData line = WordData.parseWordData(br.readLine());
      table.put(line.getWord(), line);
      wordArray[i] = line;
    }
    
    br.close();
    
    this.table = table; 
    this.wordArray = wordArray;
  }
  
  
  /** Checks if there is a path between words. If so, it sets each word's predecessor in the path
    * @param start The starting word's index
    * @param destination The final word's index
    * @return true If there is a path,
    *         false if not
    */
  private boolean hasPath(int start, int destination){
    
    Queue<Integer> queue = new LinkedList<>();
    
    //Add the first word to the queue
    queue.add(start);
   
    //Similar to BFS algorithm. Returns true if the destination has been reached
    while (!queue.isEmpty()){
      
      int parent = queue.poll();
      LinkedList<Integer> indices = wordArray[parent].getIndices();
      
      for (Integer i : indices){
        
        if (!wordArray[i].isVisited()) {
          wordArray[i].setPredecessor(parent);
          
          if (i == destination) return true;
          
          wordArray[i].setVisited(true);
          queue.add(i);
        }
        
      }
    }
    return false;
  }
  
  
  /** Creates a Linked List with the path's indices 
    * @param start The starting word's index
    * @param destination The final word's index
    * @return A LinkedList with the path's indices
    */
  public LinkedList<Integer> getPath (int start, int destination){
    
    if (hasPath(start,destination)){
      LinkedList<Integer> path = new LinkedList<>();
      
      int previous = destination;
      
      //Go from destination to start, checking each word's predecessor
      while (previous != start){
        path.addFirst(previous);
        previous = wordArray[previous].getPredecessor();
      }
      
      path.addFirst(start);
      return path;
    }
      
    return null;
  }
  

  /** 
   * Prints the final path of words
   */
  public void getWordPath(){
    
	//Runs until the user quits
    while (true) {
      
      //Stores the first word
      String startWord = JOptionPane.showInputDialog("Enter the exact starting word (no whitespace characters):" 
                                                       + "\n(Click \"Cancel\" if you want to quit)");
      if(startWord == null)
        return;
      
      //Stores the second word
      String finalWord = JOptionPane.showInputDialog("Enter the exact destination word (no whitespace characters):" 
                                                       + "\n(Click \"Cancel\" if you want to quit)");
      if(finalWord == null)
        return;
      

      if (table.get(startWord) == null || table.get(finalWord) == null)
        System.out.print("Either word does not appear in the file\n\n");
      
      else {
        
        int startIndex = table.get(startWord).getIndex();
        int finalIndex = table.get(finalWord).getIndex();
        
        //Stores the path as indices
        LinkedList<Integer> path = getPath(startIndex, finalIndex);
        
        //If there's a path, print the words
        if (path != null){
          
          boolean isFirstWord = true;
          
          System.out.print("[");
          for (Integer i : path){
            
            if (isFirstWord) {
              System.out.print(wordArray[i].getWord());
              isFirstWord = false;
            }else
              System.out.print(", " + wordArray[i].getWord());
          }
          System.out.print("]\n\n"); 
          
        }else
          System.out.print("No path was found\n\n");
        
      }
      
      //Reset each word's flag to false
      for (WordData data : wordArray)
        data.setVisited(false);
      
    }
    
  }
}