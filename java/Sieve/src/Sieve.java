/* SELF ASSESSMENT 
   1. createSequence:
Did I use the correct method definition?
Mark out of 5:5
Comment:i did
Did I create an array of size n (passed as the parameter) and initialise it?
Mark out of 5:5
Comment:the created array is of the correct size
Did I return the correct item?
Mark out of 5:5
Comment:an array is returned

   2. crossOutMultiples
Did I use the correct method definition?
Mark out of 5:5
Comment:i did
Did I ensure the parameters are not null and one of them is a valid index into the array
Mark out of 2:2
Comment:yes
Did I loop through the array using the correct multiple?
Mark out of 5:5
Comment:loops through until a number is reached in the sequence that has not been crossed out and whose value is greater than the square root of N
Did I cross out correct items in the array that were not already crossed out?
Mark out of 3:3 
Comment:i used a 2d array to mark whether the number was crossed out (0= not crossed out...1=crossed out)

   3. sieve   
Did I have the correct function definition?
Mark out of 5:5
Comment:i did
Did I make calls to other methods?
Mark out of 5:5
Comment: i had calls to other functions
Did I return an array with all non-prime numbers are crossed out?
Mark out of 2:2
Comment:yes i did

   4. sequenceTostring  
Did I have the correct function definition?
Mark out of 5:5
Comment:yes
Did I ensure the parameter to be used is not null?
Mark out of 3:3
Comment: yes
Did I Loop through the array updating the String variable with the non-crossed out numbers and the crossed numbers in brackets? 
Mark out of 10:10
Comment: i did 

   5. nonCrossedOutSubseqToString  
Did I have the correct function definition
Mark out of 5:5
Comment:yes
Did I ensure the parameter to be used is not null?  
Mark out of 3:3
Comment:yes
Did I loop through the array updating the String variable with just the non-crossed out numbers? 
Mark out of 5:5
Comment:yes, pretty much copy of the sequenceTostring 

   6. main  
Did I ask  the user for input n and handles input errors?  
Mark out of 5:5
Comments:yes
Did I make calls to other methods (at least one)?
Mark out of 5:5
Comment:calls to every function required
Did I print the output as shown in the question?  
Mark out of 5:2
Comment: i didn't print the output as the multiples were being crossed, only after they were all crossed out 

   7. Overall
Is my code indented correctly?
Mark out of 4:4
Comments:yes
Do my variable names make sense?
Mark out of 4:4
Comments:they do
Do my variable names, method names and class name follow the Java coding standard
Mark out of 4:4
Comments:yes 
      Total Mark out of 100 (Add all the previous marks): 97
 */

import java.util.Scanner;
public class Sieve {

	public static void main(String[] args) 
	{
		boolean stop=false;
		while(!stop) {
			Scanner input = new Scanner(System.in);
			try {
				System.out.println("Insert an int >=2 : ");
				if(input.hasNextInt()) {
					int maxNumber = input.nextInt();
					int numberArray[][]= new int [2][maxNumber-1];
					sieve(numberArray,maxNumber);
					sequenceToString(numberArray);
					nonCrossedOutSubseqToString(numberArray);	
				}
				else if(input.hasNext("quit")) {
					stop=true;					
				}
				else
					System.out.println("Invalid input.");
			}catch(ArrayIndexOutOfBoundsException | NegativeArraySizeException e) 
			{
				System.out.println("Invalid input.");
			}
		}System.out.println("Goodbye.");


	}

	public static int[][]createSequence (int[][]numberArray,int maxNumber)
	{
		int index=0;
		for(int number=2;number<=maxNumber;number++)
		{
			numberArray[0][index]=number;
			index++;
		}
		return numberArray;
	}

	public static int[][]crossOutMultiples (int[][]numberArray, int maxNumber)
	{
		for(int index=0; index<numberArray[0].length/2;index++)
		{
			if(numberArray[0][index]>Math.sqrt(maxNumber) && numberArray[1][index]==0)
				return numberArray;
			for(int number=2;number<=maxNumber;number++)
			{
				if(number%numberArray[0][index]==0 && number>numberArray[0][index])
				{
					numberArray[1][number-2]=1;
				}			
			}
		}
		return numberArray;
	}

	public static int[][]sieve (int[][]numberArray, int maxNumber)
	{
		createSequence(numberArray, maxNumber);
		crossOutMultiples(numberArray, maxNumber);
		return numberArray;
	}


	public static String sequenceToString (int[][]numberArray)
	{
		String sequence="";
		for(int index=0;index<numberArray[0].length;index++) 
		{
			if(numberArray[1][index]==0) 
			{
				sequence= sequence + Integer.toString(numberArray[0][index]);
				if(index==numberArray[0].length-1)
					sequence+=".";
				else
					sequence+=",";
			}
			else if(numberArray[1][index]==1) 
			{
				sequence= sequence+"["+ Integer.toString(numberArray[0][index])+"]";
				if(index==numberArray[0].length-1)
					sequence+=".";
				else
					sequence+=",";
			}
		}
		System.out.println(sequence);
		return sequence;
	}

	public static String nonCrossedOutSubseqToString (int[][]numberArray) 
	{
		String nonCrossedOut="";
		for(int index=0;index<numberArray[0].length;index++) 
		{
			if(numberArray[1][index]==0) 
			{
				nonCrossedOut= nonCrossedOut + Integer.toString(numberArray[0][index]);
				if(index==numberArray[0].length-1)
					nonCrossedOut+=".";
				else
					nonCrossedOut+=",";
			}
		}
		System.out.println(nonCrossedOut);
		return nonCrossedOut;
	}

}


