import java.io.*;
import java.util.Scanner;	

public class HarnessUserInterface {
	public static void main(String[]args) throws IOException {
		FileInputStream inputStream = new FileInputStream("harness.txt");
		HarnessRecords harnessRecords = new HarnessRecords(inputStream);
		Scanner input = new Scanner(System.in);
		boolean finished = false;
		while(!finished) {
			System.out.println("\nPlease enter if you would like to:"
					+ "\n-'Add' a record for a newly purchased harness."									//
					+ "\n-'Remove' a climbing harness from the club."										//
					+"\n-'Check', record a harness being checked by an instructor to ensure it is safe."
					+"\n-'Loan' a harness to a club member if there is any available harnesses."
					+"\n-'Return' a harness is no longer in use by a club member."
					+"\n-'List' the currently available harnesses."											//
					+"\n-'Quit' the program.");																//
			
			input = new Scanner(System.in);
			if((input.hasNext("add"))||(input.hasNext("Add")))
			{
				add(harnessRecords);
			}
			else if((input.hasNext("remove"))||(input.hasNext("Remove")))
			{
				remove(harnessRecords);
			}
			else if((input.hasNext("check"))||(input.hasNext("Check")))
			{
				check(harnessRecords);
			}
			else if((input.hasNext("list"))||(input.hasNext("List")))
			{
				list(harnessRecords);
			}
			else if((input.hasNext("quit"))||(input.hasNext("Quit")))
			{
				System.out.println("Thanks for using our service.");
				finished=true;
			}
		}
	}
	
	
	private static void add(HarnessRecords harnessRecords) {
		boolean end=false;
		while(!end) {
			System.out.println("Please enter the following details about the new harness:\n"
					+"1. Make  2. Model number  3. Name of the inspector who checked the harness; "
					+"In the format 'make, modelNumber, nameOfInspector'");
			Scanner input = new Scanner(System.in);
			String[] harnessDetails = input.nextLine().split(", ",-1);
			if(harnessDetails.length==3) {	
				Harness newHarness = new Harness(harnessDetails[0],Integer.parseInt(harnessDetails[1]),harnessDetails[2]);
				harnessRecords.addHarness(newHarness);
				end=true;
			}
			else {
				System.out.println("Error, please follow the format.");
			}
		}
	}
	
	
	private static void remove(HarnessRecords harnessRecords) {
		boolean end=false;
		while(!end) {
			System.out.println("Please enter the following details of the harness you would like to remove:\n"
					+"1. Make  2. Model number; In the format 'make, modelNumber'");
			Scanner input = new Scanner(System.in);
			String[] harnessDetails = input.nextLine().split(", ",-1);
			if(harnessDetails.length==2) {	
				if(harnessRecords.removeHarness(harnessDetails[0], Integer.parseInt(harnessDetails[1]))!=null) {
					harnessRecords.removeHarness(harnessDetails[0], Integer.parseInt(harnessDetails[1]));
					end=true;
				}
				else {
					System.out.println("Harness was not found.");
				}
			}
			else {
				System.out.println("Error, please follow the format.");
			}
		}
	}
	
	
	private static void check(HarnessRecords harnessRecords) {
		boolean end=false;
		while(!end) {
			System.out.println("Please enter the following details of the harness you would like to remove:\n"
					+"1. Make  2.Model number  3. Name of instructor; In the format 'make, modelNumber'");
			Scanner input = new Scanner(System.in);
			String[] harnessDetails = input.nextLine().split(", ",-1);

			if(harnessDetails.length==3) {
				Harness harness = harnessRecords.findHarness(harnessDetails[0],Integer.parseInt(harnessDetails[1]));
				if(harness!=null){
					harness.checkHarness(harnessDetails[2]);
					end=true;
				}
			}
			else {
				System.out.println("Error, please follow the format.");
			}
		}
	}
	
	
	private static void list(HarnessRecords harnessRecords) {
		for(int i=0; i<harnessRecords.getList().size();i++) {
			System.out.println(harnessRecords.getList().get(i).toString());
		}
	}
	
}