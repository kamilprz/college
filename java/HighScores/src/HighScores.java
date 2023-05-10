/* SELF ASSESSMENT
 1. Did I use easy-to-understand meaningful variable names formatted properly (in lowerCamelCase)?
        Mark out of 5: 3 don't think count2 and count3 are very clear 
 2. Did I indent the code appropriately?
        Mark out of 5: 5 code is indented
 3. Did I write the initialiseHighScores function correctly (parameters, return type and function body) and invoke it correctly?
       Mark out of 15:  15	sets all the necessary values in the array to 0
 4. Did I write the printHighScores function correctly (parameters, return type and function body) and invoke it correctly?
       Mark out of 15:  15	prints the necessary values without extra 0s
 5. Did I write the higherThan function correctly (parameters, return type and function body) and invoke it correctly?
       Mark out of 15:  15  successfully checks if the nextHighScore is higher than all currently stored
 6. Did I write the insertScore function correctly (parameters, return type and function body) and invoke it correctly?
       Mark out of 20:  18  a little bit messy with nested loops and multiple counters, but works as it should
 7. Did I write the main function body correctly (first asking for the number of scores to be maintained and then repeatedly asking for scores)?
       Mark out of 20:  20	works, asks user to start over if invalid input is obtained.
 8. How well did I complete this self-assessment?
        Mark out of 5: 5   okay 
 Total Mark out of 100 (Add all the previous marks): 96
 */ 
import java.util.InputMismatchException;
import java.util.Scanner;
public class HighScores {

	public static void main(String[] args) {
		boolean stop=false;
		while(!stop) {
			Scanner input = new Scanner(System.in);
			try {
				System.out.println("How many scores do you want to maintain?\n(in the range -2147483648< score <2147483647)");
				int amountOfScores = input.nextInt();
				int[]highScores= new int[amountOfScores];
				initialiseHighScores(highScores);
				int index=0;
				while( index<highScores.length)
				{
					input = new Scanner(System.in);
					try 
					{
						System.out.println("\nInsert your next score.");
						int nextHighScore=input.nextInt();
						if(nextHighScore<0)
						{
							nextHighScore=0;
						}
						insertScore(highScores, nextHighScore, index);
						System.out.print("The high scores are: ");
						printHighScores(highScores,index);
						index++;
						if(index+1==highScores.length) 
						{
							stop=true;
						}	
					}catch(InputMismatchException e) {
						System.out.print("Invalid input. Please enter a whole number that fits the range.");
					}	
				}
			} catch (InputMismatchException | NegativeArraySizeException ex) {
				System.out.println("Invalid input. Please retry using only positive numbers.");
			}
		}

	}


	//RESETS THE ARRAY TO ALL ZEROS
	public static int[] initialiseHighScores(int[]highScores){
		for(int index=0; index<highScores.length; index++){
			highScores[index]=0;
		}
		return highScores;
	}

	//CHECKS WHICH POSITION THE NEXT INPUTTED HIGH SCORE SHOULD BE PLACED IN AND PLACES IT THERE, SHIFTING OTHER VALUES TO THE RIGHT IF NECESSARY
	public static int[] insertScore(int[]highScores, int nextHighScore, int index){
		if(index==0)
			highScores[index]=nextHighScore;
		else if(index>0 && higherThan(highScores,nextHighScore,index))
		{
			for(int count=index;count>=1;count--) 
			{
				highScores[count]=highScores[(count-1)];
			}			
			highScores[0]=nextHighScore;
		}
		else if(index>0 &&(!higherThan(highScores,nextHighScore,index)) )
		{
			boolean endLoop=false;
			for(int count2=0; (count2<highScores.length && endLoop==false);count2++){
				if(nextHighScore>=0) {
					if(nextHighScore>highScores[count2]) 
					{
						for(int count3=index;count3>=count2;count3--)
						{
							highScores[count3]=highScores[(count3-1)];
						}
						highScores[count2]=nextHighScore;
						endLoop=true;
					}
				}

			}
		}
		return highScores;
	}

	//CHECKS WHETHER THE NEXT INPUTTED HIGH SCORE IS HIGHER THAN ALL CURRENT HIGH SCORES IN THE ARRAY
	public static boolean higherThan(int[]highScores,int nextHighScore, int index){
		boolean higherThan=true;
		int count=0;
		while((higherThan)&&((index-count)>=0))
		{
			if(nextHighScore<highScores[index-count]) 
			{
				higherThan=false;
			}
			count++;
		}

		return higherThan;
	}


	//PRINTS ALL THE INPUTTED HIGH SCORES SO FAR
	public static int[] printHighScores(int[]highScores, int index) {
		for(int count4=0;count4<=index;count4++) {
			System.out.print(highScores[count4] +" ");
		}
		return highScores;
	}
}
