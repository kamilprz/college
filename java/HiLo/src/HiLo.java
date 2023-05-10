/* SELF ASSESSMENT 
   1. Did I use appropriate CONSTANTS instead of numbers within the code? 
       Mark out of 5:5
       Comment: Used constants for cards without numerical value
   2. Did I use easy-to-understand, meaningful CONSTANT names formatted correctly in UPPERCASE? 
       Mark out of 5:5
       Comment: Used the name of the card as the name of the constant
   3. Did I use easy-to-understand meaningful variable names? 
       Mark out of 10:10
       Comment: newCard and oldCard, pretty straight forward
   4. Did I format the variable names properly (in lowerCamelCase)? 
       Mark out of 5:5
       Comment:  yes
   5. Did I indent the code appropriately? 
       Mark out of 10:10
       Comment:  yes
   6. Did I use an appropriate loop to allow the user to enter their guesses until they win or lose? 
       Mark out of 20: 20
       Comment:  yes, it informs you whether you won or lost
   7. Did I check the input to ensure that invalid input was handled appropriately? 
       Mark out of 10: 10
       Comment:  invalid input prints a message asking the user to try again
   8. Did I generate the cards properly using random number generation (assuming all cards are equally likely each time)? 
       Mark out of 10: 10
       Comment:  yes
   9. Did I output the cards correctly as 2, 3, 4, ... 9, 10, Jack, Queen, King? 
       Mark out of 10: 10
       Comment:  yes
   10. Did I report whether the user won or lost the game before the program finished? 
       Mark out of 10:10
       Comment:  yes
   11. How well did I complete this self-assessment? 
       Mark out of 5:5
       Comment:  
   Total Mark out of 100 (Add all the previous marks): 100
 */
import java.util.Scanner;
import java.util.Random;
public class HiLo {
	public static final int ACE=1;
	public static final int JACK=11;
	public static final int QUEEN=12;
	public static final int KING=13;
	public static void main(String[] args) {
		boolean stop=false;
		int score=0;
		do{
			Scanner input=new Scanner(System.in);
			Random cardGenerator = new Random();
			int newCard=cardGenerator.nextInt(13)+1;
			if(newCard==ACE){
				System.out.println("The new card is an Ace, do you think the next card "
						+" will be higher,\nlower, or equal? Or type 'quit' to quit.");}
			else if(newCard==JACK){
				System.out.println("The new card is a Jack, do you think the next card "
						+" will be higher,\nlower, or equal? Or type 'quit' to quit.");}
			else if(newCard==QUEEN){
				System.out.println("The new card is a Queen, do you think the next card "
						+" will be higher,\nlower, or equal? Or type 'quit' to quit.");}
			else if(newCard==KING){
				System.out.println("The new card is a King, do you think the next card "
						+" will be higher,\nlower, or equal? Or type 'quit' to quit.");}
			else
				System.out.println("The new card is a "+newCard+" ,do you think the next card"
					+ " will be higher,\nlower, or equal? Or type 'quit' to quit.");
			int oldCard=newCard;
			newCard=cardGenerator.nextInt(13)+1;
			if((input.hasNext("higher"))||(input.hasNext("lower"))||(input.hasNext("equal"))){
				if(input.hasNext("higher")&&(newCard>oldCard)){
					System.out.println("You're right, the next card was higher.");
					score++;
				}
				else if(input.hasNext("higher")&&(newCard<=oldCard)) {
					System.out.println("Unlucky, you lost. Try again.");
					score=0;
					stop=true;
				}
				if(input.hasNext("lower")&&(newCard<oldCard)) {
					System.out.println("You're right, the next card was lower.");
					score++;
				}
				else if(input.hasNext("lower")&&(newCard>=oldCard)) {
					System.out.println("Unlucky, you lost. Try again.");
					score=0;
					stop=true;
				}
				if(input.hasNext("equal")&&(newCard==oldCard)) {
					System.out.println("You're right, the cards were equal.");
					score++;
				}
				else if(input.hasNext("equal")&&(newCard!=oldCard)) {
					System.out.println("Unlucky, you lost. Try again.");
					score=0;
					stop=true;
				}
				if(score==4) {
					System.out.println("Congratulations! You won the game.");
					stop=true;
				}

			}
			else if(input.hasNext("quit")) {
				System.out.println("Thanks for playing.");
				stop=true;
			}
			else System.out.println("Not a valid entry. Please try again.");
		}while(!stop);

	}

}
