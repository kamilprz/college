import java.util.Random;
public class CoinToss {

	public static void main(String[] args) {
		int amountOfHeads = 0;
		int amountOfTails =0;
		int count=0;
		Random toss = new Random();
		for(count=0; (count<10000); count++)
		{
			int tossOutcome= toss.nextInt(2);
			if (tossOutcome==1)
				amountOfHeads++;
			else if (tossOutcome==0)
				amountOfTails++;
		
		if (count==9999 && tossOutcome==1) {
			System.out.println ("The final toss was a head.");}
		else if (count==9999 && tossOutcome==0){
			System.out.println ("The final toss was a tail.");}
		}
		System.out.println("Amount of heads was " + amountOfHeads
				+" and the amount of tails was " +amountOfTails);
		}

	}