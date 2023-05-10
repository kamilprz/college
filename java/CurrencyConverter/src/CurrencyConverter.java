
		/*  SELF ASSESSMENT
		   1. Did I use appropriate CONSTANTS instead of numbers within the code?
		       Mark out of 10: 8  
		   2. Did I use easy-to-understand, meaningful CONSTANT names?
		       Mark out of 5:  4
		    3. Did I format the CONSTANT names properly (in UPPERCASE)?
		       Mark out of 5:  5
		   4. Did I use easy-to-understand meaningful variable names?
		       Mark out of 10:  8
		   5. Did I format the variable names properly (in lowerCamelCase)?
		       Mark out of 10:  9
		   6. Did I indent the code appropriately?
		       Mark out of 10:  10
		   7. Did I read the input correctly from the user using (an) appropriate question(s)?
		       Mark out of 10:  7
		   8. Did I compute the answer correctly for all cases?
		       Mark out of 20:  19
		   9. Did I output the correct answer in the correct format (as shown in the examples)?
		       Mark out of 10:  10
		   10. How well did I complete this self-assessment?
		       Mark out of 10:  8
		   Total Mark out of 100 (Add all the previous marks): 88
		*/ 

import java.util.Scanner;
public class CurrencyConverter {

	public static final double ONE_OLD_POUND = 160.8;
	public static final double ONE_OLD_SHILLING = 8.04;
	public static final double ONE_OLD_PENCE = 0.67;

	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);
		System.out.print ("Enter amounts of old Sterling pounds, shillings, and pennies.");
		double oldPound = input.nextDouble();
		double oldShilling = input.nextDouble();
		double oldPenny = input.nextDouble();
		double conversionPound = oldPound*ONE_OLD_POUND;
		double conversionShilling = oldShilling*ONE_OLD_SHILLING;
		double conversionPenny = oldPenny*ONE_OLD_PENCE;		
		System.out.println(oldPound + " old Pounds," + oldShilling + " old shillings and " + oldPenny +
						" old pennies, is equal to £" + (conversionPound + conversionShilling + conversionPenny));
			}

		}

	
