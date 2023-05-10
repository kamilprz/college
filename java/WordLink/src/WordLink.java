/* SELF ASSESSMENT 

1. readDictionary
- I have the correct method definition [Mark out of 5:]	5
- Comment: return type List<String>, parameter ArrayList
- My method reads the words from the "words.txt" file. [Mark out of 5:]	5
- Comment: reads and stores in order into arrayList
- It returns the contents from "words.txt" in a String array or an ArrayList. [Mark out of 5:]	5
- Comment: returns an arrayList of words

2. readWordList
- I have the correct method definition [Mark out of 5:]	5
- Comment: return type String[], parameter inputed list of Strings
- My method reads the words provided (which are separated by commas, saves them to an array or ArrayList of String references and returns it. [Mark out of 5:]	5
- Comment: saves the comma separated list into an array of Strings

3. isUniqueList
- I have the correct method definition [Mark out of 5:]	5
- Comment: return type boolean, parameter; list of words entered converted to array
- My method compares each word in the array with the rest of the words in the list. [Mark out of 5:]	5
- Comment: yes, compares each word with every word after it (improving efficiency over comparing with every word each time, removes duplicate comparisons)
- Exits the loop when a non-unique word is found. [Mark out of 5:]	5
- Comment: if a non-unique value is found, the loop breaks
- Returns true is all the words are unique and false otherwise. [Mark out of 5:]	5
- Comment: returns the correct boolean

4. isEnglishWord
- I have the correct method definition [Mark out of 5:]	5
- Comment: return type boolean, parameters; arrayList of dictionary words, array of inputed Strings
- My method uses the binarySearch method in Arrays library class. [Mark out of 3:]0	
- Comment: Used Collections.binarySearch
- Returns true if the binarySearch method return a value >= 0, otherwise false is returned. [Mark out of 2:]	2
- Comment: if index >=0 true, else false. Also tells you which word in your list isn't an English word. (only mentions the first if there are multiple)

5. isDifferentByOne
- I have the correct method definition [Mark out of 5:]	5
- Comment: return type boolean, parameters; array of inputed Strings
- My method loops through the length of a words comparing characters at the same position in both words searching for one difference. [Mark out of 10:]	8
- Comment: if comparing Strings a & b, if a.length=b.length, loops through length of a and counts how many letters differ, then if differentLetters==1, 
		   true is returned

6. isWordChain
- I have the correct method definition [Mark out of 5:]	5
- Comment: return type boolean, parameters; arrayList of dictionary words, array of inputed Strings
- My method calls isUniqueList, isEnglishWord and isDifferentByOne methods and prints the appropriate message [Mark out of 10:]	10
- Comment: it calls the methods and prints the right message

7. main
- Reads all the words from file words.txt into an array or an ArrayList using the any of teh Java.IO classes covered in lectures [Mark out of 10:] 10
- Comment: uses the readDictionary method at the start of the program to read in the text file and load the contents into an arrayList
- Asks the user for input and calls isWordChain [Mark out of 5:]	5	
- Comment: while loops this until user enters a blank

Program takes a while to load sometimes, don't know if that's because of my computer or inefficency in code. 
 Total Mark out of 100 (Add all the previous marks):	95
*/
import java.io.*;
import java.util.*;
public class WordLink{
	public static void main (String[]args) {
		List<String> wordList =new ArrayList<String>();	
		readDictionary(wordList);
		boolean quit=false;
		Scanner input = new Scanner(System.in);
		while(!quit) {
			System.out.println("Insert a comma seperated list of words.(or empty to leave)");
			input = new Scanner(System.in);
			String inputList=input.nextLine();
			if(inputList.equals("")) {
				quit=true;
				break;
			}
			else {
			isWordChain(readDictionary(wordList),(readWordList(inputList)));
			}
		}
		input.close();
	}


	public static List<String> readDictionary(List<String>wordList) {
		try {
			FileReader reader = new FileReader("words.txt");
			BufferedReader bReader = new BufferedReader(reader);			
			String word;
			while((word=bReader.readLine())!= null) {
				wordList.add(word);
			}	
			reader.close();
			bReader.close();

		}catch(Exception e) {
			System.out.println("Error.");
		}
		return wordList;
	}


	public static String[] readWordList (String inputList) {
		String[] wordsInput = inputList.split(", ",-1);
		return wordsInput;
	}


	public static boolean isUniqueList(String[]wordsInput) {
		boolean isUnique=true;
		for(int i=0;i<wordsInput.length-1;i++) {	
				if(wordsInput[i].equals(wordsInput[i+1])) {
					isUnique=false;
					break;
				}
		}
		return isUnique;
	}


	public static boolean isEnglishWord(List<String>wordList,String[]wordsInput) {
		boolean isEnglishWord=false;
		int counter=0;
		for(int i=0;i<wordsInput.length;i++) {
			counter++;
			int index=-1;
			index=Collections.binarySearch(wordList,wordsInput[i]);
			if(index>0) {
				isEnglishWord=true;
				//				System.out.println("All are english words.");
			}
			else if(index<0) {
				isEnglishWord=false;
				System.out.println("The word no."+counter+" is not an english word.");
				return isEnglishWord;
			}
		}
		return isEnglishWord;
	}


	public static boolean isDifferentByOne(String[]wordsInput) {
		boolean isDifferentByOne=false;
		int differentLetters=0;
		int stringIndex=0;
		for(int i=0;i<wordsInput.length-1;i++) {
			differentLetters=0;
			if(wordsInput[i].length()!=wordsInput[i+1].length()) {
				isDifferentByOne=false;
				return isDifferentByOne;
			}
			for(stringIndex=0;stringIndex<wordsInput[i].length();stringIndex++) {
				if(wordsInput[i].charAt(stringIndex)!=wordsInput[i+1].charAt(stringIndex))
				{
					differentLetters++;
				}				
			}
			if(differentLetters==1) 
			{
				isDifferentByOne=true;
			}
			else {
				isDifferentByOne=false;
			}
		}

		//		System.out.println("different by one "+isDifferentByOne);
		return isDifferentByOne;
	}


	public static boolean isWordChain(List<String>wordList,String[]wordsInput) {
		boolean isWordChain=false;
		if(isUniqueList(wordsInput)&&(isEnglishWord(wordList,wordsInput))&&(isDifferentByOne(wordsInput))){
			isWordChain=true;
			System.out.println("Valid chain of words from Lewis Carroll's word-links game.");
		}
		else {
			System.out.println("Not a valid chain of words from Lewis Carroll's word-links game.");
		}
		return isWordChain;
	}
}

