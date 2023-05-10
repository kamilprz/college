import java.util.Scanner;
public class Roots {

	public static void main(String[] args) {
		boolean stop = false;	
		do {
			Scanner input =new Scanner (System.in);
			System.out.println("Enter the coefficents of your second order polynomial"
					+ ", \nseperated by spaces; or enter 'quit' to quit.");
			if(input.hasNextDouble()) {
				double a = input.nextDouble();
				double b = input.nextDouble();
				double c = input.nextDouble();
				double root1 = (-b+ (Math.sqrt(b*b-4*a*c)))/(2*a);
				double root2 = ((-b- (Math.sqrt(b*b-4*a*c)))/(2*a));
			/*	if ((a==0)&&(b==0))
					System.out.println("Invalid equation"); 
				if((a==0)&&(b!=0))
					System.out.println("The root is " + -c/b);
				if (root1==root2)
					System.out.println("There is only one root, it is " + root1);
				if ((root1!=root2)&&(a!=0))
					System.out.println("The two roots are " +root1 + " and "+root2);
			*/
				double discriminant = b*b -(4*a*c);
				if (discriminant>0)
					System.out.println("The roots are "+root1 +" and "+root2);
				if (discriminant==0)
					System.out.println("The root is "+root1);
				if (discriminant<0)
				{
                    double complexPart = (Math.sqrt(-discriminant)) / (2*a);
                    double realPart = (-b) / (2*a);
                    System.out.println("The roots are x = " + realPart + " + " + complexPart + "i" +
                    ", and x = " + realPart + " - " + complexPart + "i");
                }
			}
			else if (input.hasNext("quit"))
			{
				System.out.println("Goodbye");
				stop=true;
			}
			else System.out.println("Not a valid entry.");

		}while(!stop);
	}

}
