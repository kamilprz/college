/* SELF ASSESSMENT 
   1. Did I use appropriate CONSTANTS instead of numbers within the code?  
       Mark out of 5: 3
   2. Did I use easy-to-understand, meaningful CONSTANT names? 
       Mark out of 5: 5
   3. Did I format the CONSTANT names properly (in UPPERCASE)? 
       Mark out of 5: 5
   4. Did I use easy-to-understand meaningful variable names? 
       Mark out of 10: 7
   5. Did I format the variable names properly (in lowerCamelCase)? 
       Mark out of 10: 10
   6. Did I indent the code appropriately? 
       Mark out of 10: 9
   7. Did I use an appropriate for loop to test all possibilities?  There should be only one. 
       Mark out of 20: 18
   8. Did I correctly check if people alive today were or could be alive in a year that is the square of their age in that year. 
       Mark out of 30: 28
   9. How well did I complete this self-assessment? 
       Mark out of 5: 4
   Total Mark out of 100 (Add all the previous marks): 89
*/
public class SquareAges {

	public static final int MAX_BIRTH_YEAR = 2017; //If born in current year
	public static final int MIN_BIRTH_YEAR = 1894; //If born 123 years before current year
	public static void main(String[] args) {
			
			boolean hasSquareAge=false;
			for(int age=0; age<124; age++) {
				int birthYear=(age*age)-age;
					if (((age*age)-age)==birthYear && birthYear>=MIN_BIRTH_YEAR && birthYear<=MAX_BIRTH_YEAR) {
						hasSquareAge=true;	
						System.out.println ("A person born in the year " +birthYear +", will be "+age
								+" in the year "+age*age+" which is the square of "+age+".");}
				}
			if (hasSquareAge==false)	
				System.out.println ("There is noone alive today who will, has, or ever will live in "
						+ "a year that is the square of their age.");
							
			}

		}
