/* SELF ASSESSMENT

Connect4Game class (35 marks) 33
My class creates references to the Connect 4 Grid and two Connect 4 Players. It asks the user whether he/she would like to play/quit inside a loop. If the user decides to play then: 1. Connect4Grid2DArray is created using the Connect4Grid interface, 2. the two players are initialised - must specify the type to be ConnectPlayer, and 3. the game starts. In the game, I ask the user where he/she would like to drop the piece. I perform checks by calling methods in the Connect4Grid interface. Finally a check is performed to determine a win. 
Comment: it asks the user who player 2 should be and starts the game, ending it when appropriate. no option to leave/loop for continous games

Connect4Grid interface (10 marks) 10
I define all 7 methods within this interface.
Comment: all are defined

Connect4Grid2DArray class (25 marks) 25
My class implements the Connect4Grid interface. It creates a grid using a 2D array Implementation of the method to check whether the column to drop the piece is valid. It provides as implementation of the method to check whether the column to drop the piece is full. It provides as implementation of the method to drop the piece.  It provides as implementation of the method to check whether there is a win.
Comment:implements and adds a body to each function in the interface

ConnectPlayer abstract class (10 marks) 7
My class provides at lest one non-abstract method and at least one abstract method. 
Comment: both methods are abstract

C4HumanPlayer class (10 marks) 10
My class extends the ConnectPlayer claas and overrides the abstract method(s). It provides the Human player functionality.
Comment: extends and overrides

C4RandomAIPlayer class (10 marks) 10
My class extends the ConnectPlayer claas and overrides the abstract method(s). It provides AI player functionality. 
Comment: extends and overrides

Total Marks out of 100: 95

*/


import java.util.Scanner;

public class Connect4Game{
	public static final char PLAYER1_PIECE='O';
	public static final char PLAYER2_PIECE='X';
	public static void main(String[]args){
		Connect4Grid2DArray grid = new Connect4Grid2DArray();
		Scanner input = new Scanner(System.in);
		ConnectPlayer player1 = new C4HumanPlayer(PLAYER1_PIECE);
		ConnectPlayer player2 = null;
		boolean gotPlayer2=false;
		boolean gameWon=false;
		boolean player1Won=false;
		boolean player2Won=false;
		String player2String="";
		grid.emptyGrid();
				
		while(!gotPlayer2) {
			System.out.println("Enter what player 2 should be: 'human' or 'ai'");
			player2String=input.next();
			if(player2String.equals("human")) {
				player2 = new C4HumanPlayer(PLAYER2_PIECE);
				gotPlayer2=true;
			}
			else if(player2String.equals("ai")) {
				player2 = new C4RandomAIPlayer(PLAYER2_PIECE);
				gotPlayer2=true;
			}
			else {
				System.out.println("Invalid entry");
			}
		}
		
		System.out.println("\nPlayer one = "+PLAYER1_PIECE+"\nPlayer two = "+PLAYER2_PIECE);
		while(!gameWon && !grid.isGridFull()) {
			System.out.println(grid.toString());
			boolean validColumn=false;
			while(!validColumn && !player2Won)
			{
				int player1Column = player1.columnToPlay();
				if(grid.isValidColumn(player1Column-1))
				{
					validColumn = true;
					grid.dropPiece(player1, player1Column);
					System.out.println(grid.toString());
					if(grid.didLastPieceConnect4()) {
						player1Won=true;
						gameWon=true;
					}
				}
				else
				{
					System.out.print("Invalid column, please try again.\n");
				}
			}
			if(player2String.equals("human") && !player1Won) {
				validColumn=false;
				while(!validColumn)
				{
					int player2Column = player2.columnToPlay();
					if(grid.isValidColumn(player2Column-1))
					{
						validColumn = true;
						grid.dropPiece(player2, player2Column);
						if(grid.didLastPieceConnect4()) {
							player2Won=true;
							gameWon=true;
						}
					}
					else
					{
						System.out.print("Invalid column, please try again.\n");
					}
				}	
			}
			else if(player2String.equals("ai") && !player1Won) {
				grid.dropPiece(player2, player2.columnToPlay());
				if(grid.didLastPieceConnect4()) {
					player2Won=true;
					gameWon=true;
				}
			}
		}
		if(grid.isGridFull()){
			System.out.println(grid.toString());
			System.out.println("\nIt's a draw.");
		}
		if(player1Won) {
			System.out.println(grid.toString());
			System.out.println("Player 1 Won!");
		}
		if(player2Won) {
			System.out.println(grid.toString());
			System.out.println("Player 2 Won!");
		}
		input.close();
	}	
}