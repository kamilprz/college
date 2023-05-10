/* SELF ASSESSMENT 

1. ResolveBet

I have correctly defined ResolveBet which takes the bet type (String) and the Wallet object, and a void return type [Mark out of 7:7 ]. 
Comment: its correctly defined
My program presents the amount of cash in the wallet and asks the user how much he/she would like to bet [Mark out of 8:8 ]. 
Comment: user first enters the amount, program prints wallet amount and asks for a bet amount
My program ensures the bet amount is not greater than the cash in the wallet [Mark out of 5: 5 ]. 
Comment: the program asks you to try again if thats the case
My program creates three Dice objects, rolls them and creates a total variable with a summation of the roll values returned [Mark out of 15: 15 ]
Comment: creates 3 dice objects, rolls and adds each topFace to create diceTotal
My program determines the winnings by comparing the bet type with the total and comparing the bet type with the dice faces for the triple bet [Mark out of 20: 20 ].
Comment: the program determines if you won or lost by comparing to the total and topFaces where necessary
My program outputs the results (win or loss) and adds the winnings to the wallet if user wins or removes the bet amount from the wallet if the user loses [Mark out of 10: 10 ].
Comment: program informs the user if they won or lost and shows them the updated wallet balance

2. Main

I ask the user for the amount of cash he/she has, create a Wallet object and put this cash into it [Mark out of 15: 15 ]
Comment: create wallet object first, then loop until it gets an amount of money greater than 0.
My program loops continuously until the user either enters quit or the cash in the wallet is 0 [Mark out of 5: 5]
Comment: ends if user can enter quit or wallet is empty
I ask the user to enter any of the four bet types or quit [Mark out of 5: 5 ].
Comment: loops continuously asking for bet type and bet amount
My program calls resolveBet for each bet type entered [Mark out of 5:5 ].
Comment: if one of the bet types is entered, resolveBet is called, otherwise the program asks for a valid input
At the end of the game my program presents a summary message regarding winnings and losses [Mark out of 5: 0]
Comment: didn't add this in 

 Total Mark out of 100 (Add all the previous marks): 95
*/
import java.util.InputMismatchException;
import java.util.Scanner;
public class ChuckALuck {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Welcome to the Chuck a Luck game. There are 4 bets you can place;"
				+ "\r\n" + 
				"\nType of Bet:   	Condition:			Payout (Odds):\n" + 
				"Triple		All 3 dice show same number (but not 1s or 6s).		30:1\r\n" + 
				"Field		Total of 3 dice < 8 or total is > 12.			 1:1\r\n" + 
				"High		Total of 3 dice > 10 (but not a Triple).		 1:1\r\n" + 
				"Low		Total of 3 dice < 11 (but not a Triple).		 1:1\r\n");
		
		Wallet wallet = new Wallet();
		boolean stop = false;
		while(wallet.check()==0) {
		 input = new Scanner(System.in);
			try {
				System.out.println("\nHow much cash do you have in your wallet?");	
				double cash=input.nextDouble();
				if(cash>0) {
					wallet.put(cash);
					System.out.println(wallet.toString());
				}
				else {
					System.out.println("Please enter an appropriate sum.");
				}
			}catch(InputMismatchException e) {
				System.out.println("Please retry and enter an appropriate sum.");
			}
		}
	
		while((wallet.check()>0)&&(!stop)) 
		{
			try {
			System.out.println("Enter what type of bet you would like to place, or 'quit' to leave.");
			String betType = input.next();
			if(betType.equals("quit")) {
				stop=true;
			}
			else if(betType.equals("triple")||betType.equals("field")||betType.equals("high")||betType.equals("low")) {
				resolveBet(betType,wallet);	
			}
			else {
				System.out.println("Please place an appropriate bet.");
			}
			}catch(InputMismatchException e) {
				System.out.println("Please place an appropriate bet.");
			}	
		}
		if(wallet.check()<=0) {
			System.out.println("Your wallet is empty. Better luck next time.");
		}
		if(stop) {
			System.out.println("Thanks for playing.");
		}
		input.close();
	}
	
	public static void resolveBet(String betType,Wallet wallet) {		
		double money=0;
		while(!wallet.get(money)) {
			try {
				Scanner input = new Scanner(System.in);
				System.out.println("How much would you like to place on your bet?");
				money =input.nextDouble();
				if(money>wallet.check()) {
					System.out.println("The amount entered does not match what's in your wallet. Try again.");
				}			
			}catch(InputMismatchException e) {
				System.out.println("Please bet an appropriate amount that is within your wallets budget.");
			}				
		}
		Dice dice1 = new Dice();
		Dice dice2 = new Dice();
		Dice dice3 = new Dice();
		dice1.roll();
		dice2.roll();
		dice3.roll();
		int diceTotal= dice1.topFace()+dice2.topFace()+dice3.topFace();
		boolean correctBet = false;
		boolean correctTriple = false;
		System.out.println("The total of the 3 dice is "+diceTotal);
		
		if(betType.equals("triple")) {
				if((dice1.topFace()==dice2.topFace()) && (dice1.topFace()==dice3.topFace() 
						&&dice1.topFace()!=1 && dice1.topFace()!=6)) {
					System.out.println("Lucky, you win the bet.");
					correctTriple = true;
				}
		}
		 if(betType.equals("field")) {
			if(diceTotal<8 || diceTotal>12) {
				System.out.println("Lucky, you win the bet.");
				correctBet = true;
			}
		}
		 if(betType.equals("high")) {
			if((diceTotal>10)&&(!correctTriple)&&(diceTotal!=18)) {	
				System.out.println("Lucky, you win the bet.");
				correctBet = true;
			}
		}
		if(betType.equals("low")) {
			if((diceTotal<11)&&(!correctTriple)&&(diceTotal!=3)) {
				System.out.println("Unlucky, you lost the bet.");
				correctBet = true;
			}
		}
		
		if(correctTriple) {
			wallet.put(31*money);
		}
		else if(correctBet) {
			wallet.put(2*money);
		}
		else if(!correctBet && !correctTriple) {
			System.out.println("Unlucky, you lost the bet.");
		}
		System.out.println(wallet.toString());	

	}
}


































