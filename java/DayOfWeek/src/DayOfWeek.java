/* SELF ASSESSMENT
 1. Did I use appropriate, easy-to-understand, meaningful CONSTANT names formatted correctly in UPPERCASE?
        Mark out of 5: 5 constant names are easy to understand, taken from ValidDate tutorial.
 2. Did I use easy-to-understand meaningful variable names formatted properly (in lowerCamelCase)?
        Mark out of 5: 5 straight forward names, in my opinion easy to understand 
 3. Did I indent the code appropriately?
        Mark out of 5: 5 code is indented properly
 4. Did I define the required function correctly (names, parameters & return type) and invoke them correctly?
       Mark out of 20: 20 functions work as required 
 5. Did I implement the dayOfTheWeek function correctly and in a manner that can be understood?
       Mark out of 20: 20 does the calculation correctly and is easy to follow
 6. Did I implement the other functions correctly, giving credit for any code that you take from elsewhere?
       Mark out of 20: 20 other functions are used and implemented correctly as far as im aware, credit given to the ValidDate tutorial
 7. Did I obtain (and process) the input from the user in the correct format (dd/mm/yyyy), and deal with any invalid input properly?       
 	Mark out of 10: 10 input taken in correct form and invalid entries are dealt with an error message
 8. Does the program produce the output in the correct format (e.g. Monday, 25th December 2017)?
       Mark out of 10: 10 program outputs correct answer in required format
 9. How well did I complete this self-assessment?
        Mark out of 5: 5 i think everything is fine
 Total Mark out of 100 (Add all the previous marks): 100
 
 >>> I incorporated the validDate, daysInMonth and isLeapYear functions provided in the ValidDate tutorial as part of my code, instead of rewriting 
 	 on my own.
 */
import java.util.InputMismatchException;
import java.util.Scanner;
public class DayOfWeek {
	public static final int DAYS_IN_APRIL_JUNE_SEPT_NOV = 30;
	public static final int DAYS_IN_FEBRUARY_NORMALLY = 28;
	public static final int DAYS_IN_FEBRUARY_IN_LEAP_YEAR = 29;
	public static final int DAYS_IN_MOST_MONTHS = 31;
	public static final int NUMBER_OF_MONTHS = 12;
	public static void main(String[] args) {
		try {
			Scanner input = new Scanner(System.in);
			input.useDelimiter("/|\r\n");
			System.out.println("Enter a date in the following format dd/mm/yyyy");
			int day=input.nextInt();
			int month=input.nextInt();
			int year=input.nextInt();
			if(validDate(day, month, year)) {
				String printDate = dayOfTheWeek(day,month,year)+ " "+ day+ numberEnding(day)+ " "+monthName(month)+" "+year ;
				System.out.println(printDate);
			}
			else if(!validDate(day, month, year))
				System.out.println("Invalid date. Please enter another date in the format dd/mm/yyyy");
		}
		catch (InputMismatchException e){
			System.out.println("Invalid input. Please enter a date in the format dd/mm/yyyy.");
		}
	}

	public static String monthName (int monthNumber){
		String monthName="";
		switch(monthNumber){
		case 1:
			monthName="January";
			break;
		case 2:
			monthName= "February";
			break;
		case 3:
			monthName= "March";
			break;
		case 4:
			monthName= "April";
			break;
		case 5:
			monthName= "May";
			break;
		case 6:
			monthName= "June";
			break;
		case 7:
			monthName= "July";
			break; 
		case 8:
			monthName="August";
			break;
		case 9:
			monthName= "September";
			break;
		case 10:
			monthName= "October";
			break;
		case 11:
			monthName= "November";
			break;
		case 12:
			monthName= "December";
			break;
		}
		return monthName;
	}

	public static String numberEnding (int day){
		String dayEnding ="";
		switch(day%10){
		case 1:
			dayEnding="st";
			break;
		case 2:
			dayEnding="nd";
			break;
		case 3:
			dayEnding="rd";
			break;
		default:
			dayEnding="th";
			break;
		}
		return dayEnding;
	}

	public static String dayOfTheWeek (int day, int month, int year){
		if(month==1 || month==2)
			year= year-1;
		int firstTwoDigits=year;     
		while(firstTwoDigits>99) {
			firstTwoDigits = firstTwoDigits/10; 
		}
		int lastTwoDigits=year%100;
		double numberOfDay=Math.abs((day+Math.floor(2.6*(((month+9)%12)+1)-0.2)+lastTwoDigits
				+Math.floor(lastTwoDigits/4)+Math.floor(firstTwoDigits/4)-2*(firstTwoDigits))%7);
		int dayOfWeek = (int)numberOfDay;
		String dayOfTheWeek="";
		if(dayOfWeek==0)
			dayOfTheWeek="Sunday";
		else if(dayOfWeek==1)
			dayOfTheWeek="Monday";
		else if(dayOfWeek==2)
			dayOfTheWeek="Tuesday";
		else if(dayOfWeek==3)
			dayOfTheWeek="Wednesday";
		else if(dayOfWeek==4)
			dayOfTheWeek="Thursday";
		else if(dayOfWeek==5)
			dayOfTheWeek="Friday";
		else if(dayOfWeek==6)
			dayOfTheWeek="Saturday";
		else
			dayOfTheWeek="no value";
		return dayOfTheWeek;
	}

	public static boolean validDate( int day, int month, int year)
	{
		return ((year >= 0) && (month >= 1) && (month <= NUMBER_OF_MONTHS) &&
				(day >= 1) && (day <= daysInMonth( month, year )));
	}

	public static int daysInMonth( int month, int year )
	{
		int numberOfDaysInMonth = DAYS_IN_MOST_MONTHS;
		switch (month)
		{
		case 2:
			numberOfDaysInMonth = isLeapYear( year ) ? DAYS_IN_FEBRUARY_IN_LEAP_YEAR :
				DAYS_IN_FEBRUARY_NORMALLY;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			numberOfDaysInMonth = DAYS_IN_APRIL_JUNE_SEPT_NOV;
			break;
		default:
			numberOfDaysInMonth = DAYS_IN_MOST_MONTHS;
			break;
		}
		return numberOfDaysInMonth;
	}

	public static boolean isLeapYear( int year )
	{
		return (((year%4 == 0) && (year%100 != 0)) || (year%400 == 0));
	}

}
