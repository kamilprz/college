import java.util.Random;

public class C4RandomAIPlayer extends ConnectPlayer{
	private char playerPiece;
	
	public C4RandomAIPlayer(char piece) {
		this.playerPiece=piece;
	}
	
	@Override
	public char getPiece() {
		return this.playerPiece;
	}

	@Override
	public int columnToPlay() {
		Random generator = new Random();
		int column = generator.nextInt(6);
		return column;
	}
}