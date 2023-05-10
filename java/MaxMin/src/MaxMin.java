import java.util.Scanner;
public class MaxMin {

	public static void main(String[] args) {
		 Scanner input = new Scanner(System.in);
	        System.out.print("Enter a series of numbers.");
	        if (input.hasNextDouble())
	        {
	            double minimumNumber = input.nextDouble();
	            double maximumNumber = minimumNumber;
	            while (input.hasNextDouble())
	            {
	                double currentNumber = input.nextDouble();
	                if (currentNumber < minimumNumber)
	                    minimumNumber = currentNumber;
	                if (currentNumber > maximumNumber)
	                    maximumNumber = currentNumber;
	            }
	            System.out.println("The max is "+maximumNumber + " and the min is "+minimumNumber);
	        }
	        else
	            System.out.println("ERROR");

	}

}
