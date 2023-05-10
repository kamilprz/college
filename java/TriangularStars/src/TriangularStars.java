	/* SELF ASSESSMENT
 1. Did I use easy-to-understand meaningful variable names formatted properly (in lowerCamelCase)?
        Mark out of 5: 5        Comment: I think they're pretty straight forward
 2. Did I indent the code appropriately?
        Mark out of 5:  5       Comment: yes
 3. Did I write the determineStarNumber or determineTriangleNumber function correctly (parameters, return type and function body) and invoke it correctly?
       Mark out of 20: 20?         Comment: Decided to use Gauss's formula instead of doing it like triangleNumber=oldValue+newValue...oldValue=triangleNumber in 
       										a loop, and as such I had to use long instead of int. Is that bad? 
 4. Did I write the isStarNumber function correctly (parameters, return type and function body) and invoke it correctly?
       Mark out of 20: 20        Comment: yeah, it checks whether the number is a star number correctly
 5. Did I calculate and/or check triangle numbers correctly?
       Mark out of 15:   15       Comment: yes
 6. Did I loop through all possibilities in the program using system defined constants to determine when to stop?
       Mark out of 10:   10       Comment: checks each value below or equal to the constant Long.MAX_VALUE
 7. Does my program compute and print all the correct triangular star numbers?
       Mark out of 20:  20      Comment: as far as I'm aware, yes it does
 8. How well did I complete this self-assessment?
        Mark out of 5:  5      Comment: pretty good
 Total Mark out of 100 (Add all the previous marks): 100?
*/
 
public class TriangularStars {
   
    public static void main(String[] args) {
    	int index=1; 
       	long number=determineTriangleNumber(index);
        System.out.println("The following values are both triangle numbers and star numbers: ");
        while(number<=Long.MAX_VALUE){
            if(isStarNumber(number)) 
            {
            	System.out.print(determineTriangleNumber(index)+", ");
            }
            index++;
            number=determineTriangleNumber(index);
        }
    }
   
    
    //Checks whether the passed value is a star number or not
    public static boolean isStarNumber (long number){
        boolean isStarNumber=false;
        int starNumber=1;
        int index=1;
        while ((starNumber<=number) && (isStarNumber==false)) {
            starNumber=6*index*(index-1)+1;
            index++;
            if(number==starNumber)
                isStarNumber=true;
        }  
        return isStarNumber;   
    }
   
    
    //Calculates the triangle number using a Gaussian formula ((n*n+1)/2 where n is the max number) for the sum of numbers until a certain value n
    //Use long instead of int to allow the use of this formula without overflow
    public static long determineTriangleNumber (long index){
        long triangleNumber = ((index)*(index+1))/2;
        return triangleNumber;
    }
   
}