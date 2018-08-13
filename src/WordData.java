
import java.util.*;

/**
 * This class represents each word's data in a file (dictionary)
 * @author Andres Beltran
 * @version 1.0
 */

public class WordData {
  
  /** Stores the index of each line */
  private int index;
  
  /** Stores the word's predecessor in the shortest path */
  private int predecessor;
  
  /** Stores the word of each line */
  private String word;
  
  /** Flag to each if a specific line has been visited */
  private boolean isVisited;
  
  /** Stores the indices of adjacent words */
  private LinkedList<Integer> indices;
  
  /** Creates a new <code>WordData</code> object */
  public WordData (){
  }
  
  /** Returns the index of a line
    * @return The index of a line
    */
  public int getIndex(){
    return index;
  }
  
  /** Changes the index of a line
    * @param index The index of the line
    */
  public void setIndex(int index){
    this.index = index;
  }
  
  /** Returns the current word's predecessor
    * @return The index of the word's predecessor
    */
  public int getPredecessor(){
    return predecessor;
  }
  
  /** Sets the current word's predecessor
    * @param predecessor The current word's predecessor
    */
  public void setPredecessor(int predecessor){
    this.predecessor = predecessor;
  }
  
  /** Returns the word of a line
    * @return The word of a line
    */
  public String getWord(){
    return word;
  }
  
  /** Changes the word of a line
    * @param word The word of the line
    */
  public void setWord(String word){
    this.word = word;
  }
  
  /** Returns true if a line has been visited
    * @return <code>true</code> if visited, or
    *         <code>false</code> if not
    */
  public boolean isVisited(){
    return isVisited;
  }
  
  /** Changes the flag of a line
    * @param isVisited The boolean for a line
    */
  public void setVisited (boolean isVisited){
    this.isVisited = isVisited;
  }
  
  /** Returns the list of indices of adjacent words
    * @return A <code>LinkedList</code> with the indices of adjacent words
    */
  public LinkedList<Integer> getIndices(){
    return indices;
  }
  
  /** Changes the list of indices of a line
    * @param indices The <code>LinkedList</code> of indices of adjacent words
    */
  public void setIndices(LinkedList<Integer> indices){
    this.indices = indices;
  }
  
  /** Creates a <code>WordData</code> object with the data of each line
    * @param line The line to be parsed
    * @return A <code>WordData</code> object with the data of each line
    */
  public static WordData parseWordData (String line){
    
    WordData wordData = new WordData();  //Stores the object to be returned
    Scanner scanner = new Scanner(line);
    LinkedList<Integer> listIndices = new LinkedList<Integer>(); //Stores the list of indices of adjacent words
    
    wordData.setIndex(scanner.nextInt());
    wordData.setWord(scanner.next());
    wordData.setVisited(false);
    
    while (scanner.hasNextInt()){
      listIndices.add(scanner.nextInt());
    }
    
    wordData.setIndices(listIndices);
    scanner.close();
    
    return wordData;
    
  }
}