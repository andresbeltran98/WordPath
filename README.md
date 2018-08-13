# Word Path
The purpose of the program is to find the shortest path from a source to a destination through a collection of words. Two words are adjacent if you can change one word to the other by changing a single letter. For example, "cat" and "hat" are adjacent but "cat" and "cup" are not (two letter changes are required). The program reads in a given file of words, queries the user for two words, and then finds the shortest path of adjacent words from the start to the end word, if such a path exists. 

## Getting Started

### Prerequisites
Java 7 or above.

There are three text files provided:
Length3WordIndex
SmallWordIndex
LargeWordIndex

The first contains all words 3 characters in length. The second contains a small subset of words of three letters. The LargeWordIndex file contains all words between 3 and 7 characters in length. The lists of words were generated from [Grady Ward's Mody Word List](http://www.gutenberg.org/ebooks/3201) of valid crossword words.
The WordIndex files are organized as follows. The first column is a number that is the index of a word. The second column is an English word. The rest of the line are the numbers that correspond to the indices of words that differ from the second column word by at most one character. 

0 aah 875 649 589 324 150 70 50 2 1 <br>
1 aal 697 590 283 71 61 28 19 2 0 <br>
2 aas 845 819 760 654 595 477 436 408 328 288 237 74 65 53 48 23 10 1 0 <br>
3 aba 59 56 35 31 25 17 14 5 4

## Installing

The program takes one argument: the name of the file that contains the list of words (Length3WordIndex, SmallWordIndex, or LargeWordIndex). Run the jar file in the command line:

```bash
java -jar WordPath.jar [SmallWordIndex, Length3WordIndex, or LargeWordIndex]
```
Example:
```bash
java -jar WordPath.jar SmallWordIndex
```

## Running the tests
The program includes JUnit testing. The test files are located under src/WordPathTester.java
The tested classes and methods are:
* Getters and setters from WordData class (file parser): testing the correct data extraction from the lists of words, and exception handling when the file does not exit or is in the wrong format.
* Method that counts the number of lines in the file. 
* getPath method in WordPath class: testing different combinations of words, and special cases (a word is not in the file, a path does not exist, etc).


## Author
* Andres Beltran - B.S. in Computer Science candidate. CWRU 2021

## License
This project is licensed under the MIT License - see the [LICENSE](https://github.com/andresbeltran98/WordPath/blob/master/LICENSE) file for details
