import java.util.Scanner;

public class C4HumanPlayer extends ConnectPlayer{
	private static final char GRID_WIDTH=7;
	private char playerPiece;
	
	public C4HumanPlayer(char piece) {
		this.playerPiece=piece;
	}

	@Override
	public char getPiece() {
		return this.playerPiece;
	}

	@Override
	public int columnToPlay() {
		Scanner input = new Scanner(System.in);
		int column=0;
		boolean validColumn=false;
		while(!validColumn) {
			System.out.println("Enter the column for "+this.playerPiece+": ");
			input = new Scanner(System.in);
			if(input.hasNextInt()) {	
				column=input.nextInt();
				if(column<=GRID_WIDTH && column>0) {
					validColumn=true;
				}
			}
			else {
				System.out.println("Invalid entry. Enter column between 1 and 7.");
			}
		}
		return column;
	}
}