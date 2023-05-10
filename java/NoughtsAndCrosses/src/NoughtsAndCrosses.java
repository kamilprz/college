/* SELF ASSESSMENT 
   1. clearBoard:
Did I use the correct method definition?
Mark out of 5:5
Comment:i did
Did I use loops to set each position to the BLANK character?
Mark out of 5:5
Comment:i did
  
   2. printBoard
Did I use the correct method definition?
Mark out of 5:5
Comment:i did
Did I loop through the array and prints out the board in a way that it looked like a board?
Mark out of 5:5
Comment:yes
  
   3. canMakeMove
Did I have the correct function definition and returned the correct item?
Mark out of 5:5
Comment:yes returns a boolean
Did I check if a specified location was BLANK?
Mark out of 5:5
Comment:yes
   
   4. makeMove
Did I have the correct function definition?
Mark out of 5:5
Comment:i did
Did I set the  currentPlayerPiece in the specified location?
Mark out of 5:5
Comment:yes it sets the player in the inputed board position    
  
   5. isBoardFull
Did I have the correct function definition and returned the correct item?
Mark out of 5:5
Comment:yep, returns a boolean        
Did I loop through the board to check if there are any BLANK characters?
Mark out of 5:5
Comment:yes i did
  
   6. winner
Did I have the correct function definition and returned the winning character
Mark out of 5:5
Comment:returns winning character or BLANK if draw     
Did I identify all possible horizontal, vertical and diagonal winners  
Mark out of 15:15
Comment:i think so yeah
   
   7.main
Did I create a board of size 3 by 3 and use the clearBoard method to set all the positions to the BLANK character ('  ')?
Mark out of 3:3
Comments:yes 
Did I loop asking the user for a location until wither the board was full or there was a winner?
Mark out of 5:5
Comments:i did
Did I call all of the methods above?
Mark out of 5:5
Comments:yep
Did I handle incorrect locations provided by the user (either occupied or invalid locations)?
Mark out of 3:3
Comments: if invalid input, an exception is thrown and the loop restarts asking the user to enter a valid position
Did I switch the current player piece from cross to nought and vice versa after every valid move?
Mark out of 3:3
Comments:yes, switches the player piece
Did I display the winning player piece or a draw at the end of the game?
Mark out of 3:3
Comments:yes

   8. Overall
Is my code indented correctly?
Mark out of 3:3
Comments:it is
Do my variable names and Constants (at least four of them) make sense?
Mark out of 3:3
Comments:make sense in my opinion
Do my variable names, method names and class name follow the Java coding standard
Mark out of 2:2
Comments:they do
      Total Mark out of 100 (Add all the previous marks): 100
*/
import java.util.InputMismatchException;
import java.util.Scanner;

public class NoughtsAndCrosses {
	public static final char BLANK=' ';
	public static final char NOUGHT = 'O';
	public static final char CROSS = 'X'; 
	public static final int BOARD_SIZE = 3; 

	public static void main(String[] args)
	{
		char[][]board = new char[BOARD_SIZE][BOARD_SIZE];
		char currentPlayerPiece=CROSS;	
		clearBoard(board);
		printBoard(board);

		while(winner(board)==BLANK && !isBoardFull(board)) {
			try {
				Scanner inputScanner = new Scanner(System.in);
				System.out.print("Enter a location on the board for "+currentPlayerPiece+". ");
				int row = inputScanner.nextInt();
				int column = inputScanner.nextInt();
				if(canMakeMove(board,row,column)==false) {
					row=-1;
				}
				makeMove(board,currentPlayerPiece,row,column);
				printBoard(board);
				currentPlayerPiece = (currentPlayerPiece==CROSS)?NOUGHT:CROSS; 
			}catch(ArrayIndexOutOfBoundsException | NegativeArraySizeException |InputMismatchException e) 
			{
				System.out.println("Please input a valid, empty position on the board.");
			}
		}
		if(winner(board)==BLANK) {
			System.out.println("It's a draw!");
		}
		else 
			System.out.println(winner(board)+" is the winner!");


	}

	public static void printBoard (char[][]board) 
	{
		System.out.println("   -------------");
		for (int i = 0; i < BOARD_SIZE; i++) 
		{
			if(i==0) 
			{
				System.out.print("1  | ");
			}
			if(i==1) 
			{
				System.out.print("2  | ");
			}
			if(i==2) 
			{
				System.out.print("3  | ");
			}
			for (int j = 0; j < BOARD_SIZE; j++) 
			{
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
			System.out.println("   -------------");
		}
		System.out.println("     1   2   3   ");
	}


	public static void clearBoard (char[][] board)
	{
		for (int i = 0; i < BOARD_SIZE; i++) 
		{
			for (int j = 0; j < BOARD_SIZE; j++) 
			{
				board[i][j]=BLANK;
			}
		}
	}

	public static boolean canMakeMove (char[][] board, int row, int column) {
		boolean canMakeMove=false;
		if(board[row-1][column-1]==BLANK) {
			canMakeMove=true;
		}
		else if(board[row-1][column-1]!=BLANK) {
			canMakeMove=false;
		}
		return canMakeMove;
	}

	public static void makeMove(char[][] board, char currentPlayerPiece , int row, int column){
		board[row-1][column-1]=currentPlayerPiece;
	}

	public static boolean isBoardFull(char[][] board) {
		boolean isFull=false;
		if(board[0][0]!=BLANK && board[0][1]!=BLANK && board[0][2]!=BLANK && board[1][0]!=BLANK && board[1][1]!=BLANK&& board[1][2]!=BLANK 
				&& board[2][0]!=BLANK && board[2][1]!=BLANK && board[2][2]!=BLANK) 
		{
			isFull=true;
		}
		return isFull;
	}

	public static char winner (char[][] board) {
		int j=0;
		int i=0;
		for(i=0;i<BOARD_SIZE;i++)
		{
			if(board[i][j]==board[i][j+1] && board[i][j]==board[i][j+2]) 
			{
				return board[i][j];
			}
		}
		i=0;
		for(j=0;j<BOARD_SIZE;j++) 
		{
			if(board[i][j]==board[i+1][j] && board[i][j]==board[i+2][j]) 
			{
				return board[i][j];
			}
		}
		if(board[0][0]== board[1][1] && board[0][0]==board[2][2]  || board[0][2]==board[1][1] && board[0][2]==board[2][0]) 
		{
			return board[1][1];
		}		
		return BLANK;
	}

}



