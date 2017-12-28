import tools.InvariantBrokenException;

/**
 * Represents the board in a 15-puzzle Game.
 * 
 * @author Patricia Reinoso.
 */

/**
 * 
 * Class that represents a board for the 15-puzzle 
 * The board can hold 15 tiles and an empty space.
 * The empty space is represented as tile with value 0.
 * A board is considered as solved if all the tiles 
 * inside are in the correct position.
 */
public class Board {

	/**
	 * The size of the board (equal to the number of tiles).
	 */
	public static final int SIZE = IntTile.TOTAL;
	/**
	 * List of tiles.
	 */
	private IntTile[] tiles = new IntTile[SIZE];
	/**
	 * Indicates whether the board is solved.
	 */
	private boolean solved;
	
	/**
	 * Class constructor.
	 * Create {@link #SIZE} tiles, numbered from 1 to 15.
	 * The empty space is represented by the value 0.
	 * The board is created and all the tiles are put in the correct order.
	 * The board is solved after creation.
	 * @throws InvariantBrokenException if the board state after creation is not valid.
	 */
	public Board() throws InvariantBrokenException{
		int i;
		for (i = 0 ; i < SIZE - 1 ; i++){
			IntTile newTile = new IntTile(i+1, true);
			tiles[i] = newTile;
		}
		IntTile newEmpty = new IntTile(0,true);
		tiles[i] = newEmpty;

		if (!invariant() || !isSolved()){
			throw new InvariantBrokenException("Invariant broken.");
		}
	}
	
	/**
	 * Getter of the list of tiles in the board.
	 * @return the list of tiles.
	 */
	public IntTile[] getTiles() throws InvariantBrokenException{
		return tiles;
	}
	
	/**
	 * Setter of the list of tiles from a list.
	 * @param tiles list of tiles.
	 * @throws InvariantBrokenException if the board state is invalid after
	 * assigning a list of tiles.
	 */
	public void setTiles(IntTile[] tiles) {
		this.tiles = tiles;
		if (!invariant()){
			throw new InvariantBrokenException("Invariant broken.");
		}
	}

	/**
	 * For each tile in tiles, the tile is in the right position.
	 * @return true if all the tiles are in the right position. False otherwise.
	 */
	public boolean isSolved() {
		for (int i = 0 ; i < SIZE - 1 ; i++){
			if (tiles[i].getIntValue() != i+1){
				return false;
			}
		}
		if (tiles[SIZE-1].getIntValue() == 0){
			setSolved(true);
			return true;
		}
		return false;
	}
	
	/**
	 * Getter for the solved attribute.
	 * @return true if the puzzle is solved. False otherwise.
	 */
	public boolean getSolved(){
		return this.solved;
	}
	
	/**
	 * Setter value for the solved attribute.
	 * @param solved true if the puzzle is solved. False otherwise.
	 */
	public void setSolved(boolean solved) {
		this.solved = solved;
	}
	
	/**
	 * Check if the amount of tiles in the tile list {@link #tiles} is equal
	 * to {@link #SIZE}. 
	 * Check that each tile is not null and is a valid tile.
	 * @return true if the conditions are met. False otherwise.
	 */
	public boolean invariant(){
		for (int i = 0 ; i < SIZE; i++){
			if (tiles[i] == null || !tiles[i].invariant()){
				return false;
			}
		}
		if (isSolved()!=getSolved()){
			return false;
		}
		return true;
	}
	
	/**
	 * To string method that returns the board in the following format:
	 * 1  2  3  4
	 * 5  6  7  8
	 * 9  10 11 12
	 * 13 14 15 __ 
	 * @return a string with the desired format.
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("");
		int val;
		for (int i = 0 ; i < SIZE ; i++){
			val = tiles[i].getIntValue();
			if (val == 0){
				sb.append("__");
			}
			else {
				sb.append(val);
			}
			sb.append("   ");
			if ((i+1)%4 == 0){
				sb.append("\n");
			}
		}
		return sb.toString();
	}

	public static void main(String[] args){
		Board b1 = new Board();
		System.out.println("B1 \n" + b1);
		System.out.println("B1 \n" + b1.isSolved());
		System.out.println("B1 \n" + b1.getTiles());

	}
}
