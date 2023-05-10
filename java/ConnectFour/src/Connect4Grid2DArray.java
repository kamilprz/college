public class Connect4Grid2DArray implements Connect4Grid{
	private static final int GRID_HEIGHT= 6;
	private static final int GRID_WIDTH= 7;
	private static final char BLANK=' ';
	char[][]grid;
	
	Connect4Grid2DArray(){
		grid = new char[GRID_HEIGHT][GRID_WIDTH];
		emptyGrid();
	}
	
	@Override
	public String toString() {
		String gridString="";
		for(int row=0;row<GRID_HEIGHT;row++) {
			gridString+="\n|";
			for(int column=0;column<GRID_WIDTH;column++) {
				gridString+=(grid[row][column]);
				gridString+=("|");
			}
		}
		gridString+=("\n---------------");
		gridString+=("\n 1 2 3 4 5 6 7\n");
		return gridString;
	}
	
	@Override
	public void emptyGrid() {
		for(int row=0;row<GRID_HEIGHT;row++) {
			for(int column=0;column<GRID_WIDTH;column++) {
				grid[row][column]=BLANK;
			}
		}
	}
	
	
	@Override
	public boolean isValidColumn(int column) {
		if(column>=0 && column<GRID_WIDTH) {
			if(!isColumnFull(column)) {
				return true;
			}
		}
		else {
			System.out.println("Invalid column.");
		}
		return false;
	}
	
	
	@Override
	public boolean isColumnFull(int column) {
		for(int row=0;row<GRID_HEIGHT;row++) {
			if(grid[row][column]==BLANK) {
				return false;
			}
		}
		return true;
	}
	
	
	@Override
	public void dropPiece(ConnectPlayer player, int column) {
		if(isValidColumn(column-1)) {
			for(int row=GRID_HEIGHT-1;row>=0;row--) {
				if(grid[row][column-1]==BLANK) {
					grid[row][column-1]=player.getPiece();
					return;
				}
			}
		}
		else {
			System.out.println("Column full or invalid.");
		}
	}
	
	
	@Override
	public boolean didLastPieceConnect4() {
		for (int row = 0; row < GRID_HEIGHT; row++) 
		{
			for (int col = 0; col < GRID_WIDTH; col++)
			{
				char connect4SlotToCheck = grid[row][col];
				if (connect4SlotToCheck == BLANK)
				{
					continue;
				}
				if (col + 3 < GRID_WIDTH &&
						connect4SlotToCheck == grid[row][col+1] && 
						connect4SlotToCheck == grid[row][col+2] &&
						connect4SlotToCheck == grid[row][col+3])
				{
					return true;
				}
				if (row + 3 < GRID_HEIGHT) 
				{
					if (connect4SlotToCheck == grid[row+1][col] &&
							connect4SlotToCheck == grid[row+2][col] &&
							connect4SlotToCheck == grid[row+3][col])
					{
						return true;
					}
					if (col + 3 < GRID_WIDTH &&
							connect4SlotToCheck == grid[row+1][col+1] &&
							connect4SlotToCheck == grid[row+2][col+2] &&
							connect4SlotToCheck == grid[row+3][col+3])
					{
						return true;
					}
					if (col - 3 >= 0 &&
							connect4SlotToCheck == grid[row+1][col-1] &&
							connect4SlotToCheck == grid[row+2][col-2] &&
							connect4SlotToCheck == grid[row+3][col-3])
					{
						return true;
					}
				}
			}
		}
		return false;
	}
		
	
	@Override
	public boolean isGridFull() {
		for(int column=1; column<=GRID_WIDTH;column++) {
			if(!isColumnFull(column-1)) {
				return false;
			}
		}
		return true;
	}
	
}