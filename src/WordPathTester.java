import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;
import java.lang.reflect.*;

/**
 * Project's tests
 * @author Andres Beltran
 */

public class WordPathTester {
  
  /**
   * Test of WordData object
   */
  @Test
  public void testWordData() {  
    
    WordData line = WordData.parseWordData("21 cat 5 20 36 38");
    
    //Test of getIndex
    assertEquals(21, line.getIndex(), 0.0);
    
    //Test of getWord
    assertEquals("Not right word", "cat", line.getWord());
    
    LinkedList<Integer> list = line.getIndices();
    
    //Test of getIndices(), first element
    assertEquals("Testing first index", new Integer(5), list.getFirst());
    
    //Test of getIndices(), many, middle
    assertEquals("Testing second index", new Integer(20), list.get(1));
    
    //Test of getIndices(), many, middle
    assertEquals("Testing third index", new Integer(36), list.get(2));
    
    //Test of getIndices(), last element
    assertEquals("Testing last index", new Integer(38), list.getLast());
    
    
    WordData line2 = WordData.parseWordData("3 eat 2");
    
    //Test of getIndices(), one element
    assertEquals("Testing last index", new Integer(2), line2.getIndices().getFirst());
    
    //Test of getIndices(), one element
    assertEquals("Testing last index", new Integer(2), line2.getIndices().getLast());
    
    
    WordData line3 = WordData.parseWordData("3 eat");
    
    //Test of getIndices(), no elements
    try{
      assertEquals("Testing last index", null, line3.getIndices().getFirst());
      fail ("Expected NoSuchElementException");
    }catch(Exception e){}
     
  }
  
  
  /**
   * Test of numLines method in WordPath
   */
  @Test
  public void testNumLines(){
    
    try{
      
      //Test 0
      assertEquals(0, WordPath.numLines(new StringReader("")), 0);
      
      //Test 1, first, last
      assertEquals(1, WordPath.numLines(new StringReader("\n")), 0);
      
      //Test many
      assertEquals(3, WordPath.numLines(new StringReader("\n\n\n")), 0);
      
    }catch(Exception e){
      fail("Does not throw any Exception");
    }
     
  }
  
  
  /**
   * Test of getPath method in WordPath
   */
  @Test
  public void testGetPath(){
    
    //Creates a WordData array simulating the SmallWordIndex file
    WordData[] array = new WordData[]{
        WordData.parseWordData("0 bat 11 10 7 3"),
        WordData.parseWordData("1 cab 4 3 2"),
        WordData.parseWordData("2 car 8 6 3 1"),
        WordData.parseWordData("3 cat 11 10 7 2 1 0"),
        WordData.parseWordData("4 cub 5 1"),
        WordData.parseWordData("5 cup 14 4"),
        WordData.parseWordData("6 ear 8 7 2"),
        WordData.parseWordData("7 eat 11 10 6 3 0"),
        WordData.parseWordData("8 far 9 6 2"),
        WordData.parseWordData("9 fur 8"),
        WordData.parseWordData("10 hat 11 7 3 0"),
        WordData.parseWordData("11 sat 13 10 7 3 0"),
        WordData.parseWordData("12 sip 14 13"),
        WordData.parseWordData("13 sit 12 11"),
        WordData.parseWordData("14 sup 12 5")
    };
    
    //Creates the Hashtable 
    Hashtable<String, WordData> table = new Hashtable<>();
    for (WordData data : array){
      table.put(data.getWord(), data);
    }
    
    
    try{
      
      WordPath path = new WordPath();
      
      //Set the wordArray Field in a WordPath instance using Reflection
      Field wordArrayField = WordPath.class.getDeclaredField("wordArray");
      wordArrayField.setAccessible(true);
      wordArrayField.set(path, array);
      
      //Set the table Field in a WordPath instance using Reflection
      Field tableField = WordPath.class.getDeclaredField("table");
      tableField.setAccessible(true);
      tableField.set(path, table);
      
      //Test 1: start is the same as destination
      LinkedList<Integer> list = new LinkedList<Integer>();
      list.add(2);
      assertEquals("Not right path", list, path.getPath(2,2));
      
      //Test 2: destination not in the file
      for (WordData data : array)
        data.setVisited(false);
      assertEquals("No path", null, path.getPath(0,15));
      
      //Test 3: start not in the file
      for (WordData data : array)
        data.setVisited(false);
      try{
        assertEquals("No path", null, path.getPath(16,2));
        fail ("Exception expected");
      }catch(Exception e){}
      
      //Test 4: start 0 and destination 14
      for (WordData data : array)
        data.setVisited(false);
      LinkedList<Integer> list4 = new LinkedList<Integer>();
      list4.add(0);
      list4.add(11);
      list4.add(13);
      list4.add(12);
      list4.add(14);
      assertEquals("Not right path", list4, path.getPath(0, 14));
      
      //Test many
      for (WordData data : array)
        data.setVisited(false);
      LinkedList<Integer> list5 = new LinkedList<Integer>();
      list5.add(12);
      list5.add(13);
      list5.add(11);
      list5.add(7);
      list5.add(6);
      list5.add(8);
      list5.add(9);
      assertEquals("Not right path", list5, path.getPath(12, 9));
      
      
    }catch(Exception e){
      fail("Invalid field");
    }
    
  }
  
}