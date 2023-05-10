import java.util.Scanner;
public class Fibonacci {
	public static void main(String[] args) {
		boolean exit=false;
		boolean loop=false;
		int last=0;
		int fibonacci=1; 
		int secondLast=1;
		do {
			loop=false;
			Scanner input = new Scanner(System.in);
			System.out.println("Enter the number you want the closest Fibonacci number for. Or 'quit'.");
			if (input.hasNextInt()) {
				int inputNo = input.nextInt();
				while(!loop) {
					fibonacci= last + secondLast;	
					if(fibonacci==inputNo) {
						System.out.println("The number entered is a fibonacci.");
						last=0;
						secondLast=1;
						loop=true;
					} 
					else if ((fibonacci>inputNo)&&(fibonacci-inputNo==inputNo-secondLast)) {
						System.out.println("The fibonacci closest to "+inputNo+" are "+fibonacci
								+" and "+secondLast);
						last=0;
						secondLast=1;
						loop=true;}
					else if((fibonacci-inputNo<inputNo-secondLast)&&(fibonacci>inputNo)) {
						System.out.println("The closest fibonacci to "+inputNo+" is "+fibonacci);
						last=0;
						secondLast=1;
						loop=true;}
					else if((fibonacci-inputNo>inputNo-secondLast)&&(fibonacci>inputNo)) {
						System.out.println("The closest fibonacci to "+inputNo+" is "+secondLast);
						last=0;
						secondLast=1;
						loop=true;}
					else {
						last=secondLast;
						secondLast=fibonacci;
					}
				}
			}
			else if (input.hasNext("quit")){
				System.out.println("Thank you, goodbye.");
				exit = true;
				loop=true;
			}
			else if(!input.hasNextInt()) {
				System.out.println("Not a valid entry. Please try again.");
			}
		}while(!exit);
	}
}
