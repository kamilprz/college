/* SELF ASSESSMENT 

Class Rational 
I declared two member variables: numerator and denominator (marks out of 4: 4).
Comment: i declared them 

Constructor 1 
My program takes take two integers as parameters (for numerator and denominator) and initialises the member variables with the corresponding values . If the denominator is equal to 0 I throw an exception (marks out of 5:5 ).
Comment: contructs as appropriate, throws if 0

Constructor 2 
My program takes only one integer as parameter (numerator), and set the numerator to this value . I set the denominator to 1 in this case, as the resulting rational number in this case is an integer (marks out of 3:1 ).
Comment: the constructor is there, but its not used in the program

Add Method 
My program takes only a rational number as a parameter and returns a new rational number which has a numerator and denominator which the addition of the two objects - this and the parameter. My program does not overwrite any of the other two rational numbers (marks out of 8: 8).
Comment: adds the rationals and returns in a new object, not overwriting the original values

Subtract Method 
I have implemented this the same as add method, except it implements subtraction (marks out of 8: 8 ).
Comment: subtracts the rationals and returns in a new object, not overwriting the original values

Multiply Method 
I have implemented this the same as add method, except it implements multiplication (marks out of 8: 8 ).
Comment: mutliplies the rationals and returns in a new object, not overwriting the original values

Divide Method 
I have implemented this the same as add method, except it implements divide (marks out of 8: 8 ).
Comment: divides the rationals and returns in a new object, not overwriting the original values

Equals Method 
My program takes a rational number as a parameter and compares it to the reference object. I only use multiplication between numerators/denominators for the purpose of comparison, as integer division will lead to incorrect results. I return a boolean value ((marks out of 8: 5 ).
Comment: i use the simplify method here, as equal rationals simplify to the same thing, else theyre not equal

isLessThan 
My program takes a rational number as a parameter and compares it to the reference object. I only use multiplication as integer division will lead to incorrect results. I return a boolean value (marks out of 8: 4).
Comment: i subtracted one from another and compared the result to 0 to see which is greater

Simplify Method 
My program returns a rational number but not a new rational number, instead it returns the current reference which is this. It doesn't take any parameters as it works only with the reference object. I first find the greatest common divisor (GCD) between the numerator and denominator, and then obtain the new numerator and denominator by dividing to the GCD (marks out of 8: 8).
Comment: simplifies as should

gcd function 
My program returns the greatest common divider of two integers: the numerator and the denominator (marks out of 6: 0).
Comment: didnt make it a seperate function, just used a loop inside the simplify function

toString Method 
My program returns a string showing the fraction representation of the number, eg. "1/2". It takes no parameters (marks out of 4: 4).
Comment: converts to a string

Test Client Class 
My program asks the user for two rational numbers, creates two rational objects using the constructor and passing in the provided values, calls addition, subtraction, multiplication, division, comparison and simplification and prints out the results (marks out of 22: 20).
Comment: asks user for 2 rationals, accepts them if theyre in the right format, else it throws an exception. doesnt loop, have to restart each time
*/

import java.util.InputMismatchException;
import java.util.Scanner;

public class RationalTest {
	public static final int NUMERATOR_INDEX=0;
	public static final int DENOMINATOR_INDEX=1;
	public static void main (String[]args){
		Scanner input = new Scanner(System.in);
		input.useDelimiter("/|\r");
		try {
			
				System.out.println("Enter a numerator and a denominator, in the format a/b where b!=0.");
				String fractionInput = input.nextLine();	
				String[] fractionArray =fractionInput.split("/|\r");
				int numerator =Integer.parseInt(fractionArray[NUMERATOR_INDEX]);
				int denominator = Integer.parseInt(fractionArray[DENOMINATOR_INDEX]);	
				if(denominator==0) {
					throw new Exception();
				}
				if(denominator<=-1) {
					denominator=denominator*-1;
					numerator=numerator*-1;
				}
				System.out.println("Enter another numerator and a denominator, in the same format.");
				input = new Scanner(System.in);
				String fractionInput2 = input.nextLine();	
				String[] fractionArray2 =fractionInput2.split("/|\r");
				int numerator2 =Integer.parseInt(fractionArray2[NUMERATOR_INDEX]);
				int denominator2 = Integer.parseInt(fractionArray2[DENOMINATOR_INDEX]);
				if(denominator2==0) {
					throw new Exception();
				}
				if(denominator2<=-1) {
					denominator2=denominator2*-1;
					numerator2=numerator2*-1;
				}
				Rational rationalNum = new Rational(numerator,denominator);
				Rational rationalNum2 = new Rational(numerator2,denominator2);
				Rational sum = rationalNum.addRationals(rationalNum2);
				Rational difference = rationalNum.subtractRationals(rationalNum2);
				Rational product = rationalNum.multiplyRationals(rationalNum2);
				Rational quotient = rationalNum.divideRationals(rationalNum2);
				System.out.println("The sum is: "+ sum.rationalToString());
				System.out.println("The difference is: "+ difference.rationalToString());
				System.out.println("The product is: "+ product.rationalToString());
				System.out.println("The quotient is: "+	quotient.rationalToString());
				rationalNum.areEqual(rationalNum2);
				rationalNum.isLessThan(rationalNum2);
				
		}catch(InputMismatchException e) {
			System.out.println("Please retry and enter valid rational numbers, in the format a/b where b!=0.");
		} catch (Exception e) {
			System.out.println("Please retry and enter valid rational numbers, in the format a/b where b!=0.");
		}


	}

}
