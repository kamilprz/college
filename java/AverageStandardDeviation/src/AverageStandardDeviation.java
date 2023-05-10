import java.util.Scanner;
public class AverageStandardDeviation {

	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);
		System.out.print ("Insert 3 numbers.");
		double firstNumber = input.nextDouble();
		double secondNumber = input.nextDouble();
		double thirdNumber = input.nextDouble();
		double average = (firstNumber + secondNumber + thirdNumber)/3;
		System.out.println ("The average of the numbers is " + average);

		double standardDeviation = Math.sqrt(((Math.pow((firstNumber-average),2))+
				(Math.pow((secondNumber-average),2))+
				(Math.pow((thirdNumber-average),2)))/2);
		System.out.println ("The standard deviation is " + standardDeviation);
	}

}
