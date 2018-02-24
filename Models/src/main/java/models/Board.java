package models;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import tools.InvalidMovementException;
import tools.InvariantBrokenException;

/**
 * Represents the board in a 15-puzzle Game.
 * 
 * @author Patricia Reinoso & Oscar Guillen
 */

/**
 * 
 * Class that represents a board for the 15-puzzle 
 * The board can hold 15 tiles and an empty space.
 * The empty space is represented as tile with value 0.
 * A board is considered as solved if all the tiles 
 * inside are in the correct position.
 *
 * Implement Cloneable interface.
 */
public class Board implements Cloneable {

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
	 * Store the current position of the empty space.
	 */
	private int spacePosition;
	
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
		spacePosition = i;

		if (!invariant() || !isSolved()){
			throw new InvariantBrokenException("Invariant broken.");
		}
	}

	/**
	 * Class constructor.
	 * Create {@link #SIZE} tiles, numbered from 1 to 15.
	 * From a string of numbers separated by space.
	 * The empty space is represented by the value 0.
	 * The board may not be solved after creation.
	 * @throws InvariantBrokenException if the board state after creation is not valid.
	 */
	public Board(String line) throws InvariantBrokenException{
		String[] lineTiles = line.split(" ");
		int i, val;
		if (tiles.length != SIZE){
			throw new InvariantBrokenException("Invariant broken.");
		}
		for (i = 0 ; i < SIZE ; i++ ){
			val = Integer.parseInt(lineTiles[i]);
			IntTile newTile = new IntTile(val, false);
			if (val == 0 ){
				spacePosition = i;
			}
			tiles[i] = newTile;
			setTileOrdered(i);
		}
		isSolved();
		if (!invariant()){
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
				setSolved(false);
				return false;
			}
		}
		if (tiles[SIZE-1].getIntValue() == 0){
			setSolved(true);
			return true;
		}
		setSolved(false);
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
	 * @throws IllegalArgumentException if other direction than UP, DOWN,
	 * 			LEFT, RIGHT 
	 */
	public void setSolved(boolean solved) {
		this.solved = solved;
	}

	/**
	 * Getter for the spacePosition attribute
	 * @return an integer of the position.
	 */
	public int getSpacePosition() {
		return this.spacePosition;
	}

	/**
	 * Setter value for the spacePosition attribute.
	 * @param position where it will be set.
	 */
	public void setSpacePosition(int pos) {
		this.spacePosition = pos;
	}
	
	/**
	 * Apply a solving sequence to a board.
	 * Catches the InvalidMovementException when an invalid movement is applied.
	 * @param seq sequence of directions to move the empty space.
	 */
	public void applySolvingSequence (SolvingSequence seq){
		for (SolvingSequence.Direction dir : seq.getSeq()){
			try{
				moveEmptyTile(dir);
			} catch (InvalidMovementException e){
				System.out.println("Invalid sequence");
			}
			System.out.println(dir + "\n" + this);
		}
	}
	
	/**
	 * Move the empty space to the specified direction.
	 * @param dir direction the empty space is going to be moved.
	 * @throws IllegalArgumentException if other direction than UP, DOWN,
	 * 			LEFT, RIGHT is passed as parameter.
	 */
	public void moveEmptyTile(SolvingSequence.Direction dir) throws IllegalArgumentException {
		switch (dir) {
			case UP:
				moveUp();
				break;
			case DOWN:
				moveDown();
				break;
			case LEFT: 
				moveLeft();
				break;
			case RIGHT:
				moveRight();
				break;
			default:
				throw new IllegalArgumentException("Unknown direction.");
		}
		isSolved();
		if (!invariant()){
			throw new InvariantBrokenException("Invariant broken when moving.");
		}
	}
	
	/**
	 * Moves the empty space to the right.
	 * @throws InvalidMovementException if the empty space cannot move to the right
	 * 									(it is in the border).
	 * @throws InvariantBrokenException if the invariant if broken when moving the 
	 * 									empty space.
	 */
	private void moveRight() throws InvalidMovementException, InvariantBrokenException {
		if ((spacePosition + 1) % 4 == 0){ 
			throw new InvalidMovementException("Cannot move right.");
		}
		IntTile aux = tiles[spacePosition];
		tiles[spacePosition] = tiles[spacePosition + 1];
		tiles[spacePosition + 1] = aux;
			
		setTileOrdered(spacePosition);
		setTileOrdered(spacePosition + 1);
	
		spacePosition += 1;
		
		//if (!invariant()){
		//	throw new InvariantBrokenException("Invariant broken when moving right.");
		//}
		
	}

	/**
	 * Moves the empty space to the left.
	 * @throws InvalidMovementException if the empty space cannot move to the left
	 * 									(it is in the border).
	 * @throws InvariantBrokenException if the invariant if broken when moving the 
	 * 									empty space.
	 */
	private void moveLeft() throws InvalidMovementException, InvariantBrokenException {
		if (spacePosition % 4 == 0){ 
			throw new InvalidMovementException("Cannot move left.");
		}
		IntTile aux = tiles[spacePosition];
		tiles[spacePosition] = tiles[spacePosition - 1];
		tiles[spacePosition - 1] = aux;
			
		setTileOrdered(spacePosition);
		setTileOrdered(spacePosition - 1);
		
		spacePosition -= 1;
		
		//if (!invariant()){
		//	throw new InvariantBrokenException("Invariant broken when moving left.");
		//}
		
	}

	/**
	 * Moves the empty space down.
	 * @throws InvalidMovementException if the empty space cannot move to the down
	 * 									(it is in the border).
	 * @throws InvariantBrokenException if the invariant if broken when moving the 
	 * 									empty space.
	 */
	private void moveDown() throws InvalidMovementException, InvariantBrokenException {
		if (spacePosition > 11){ 
			throw new InvalidMovementException("Cannot move down.");
		}
		IntTile aux = tiles[spacePosition];
		tiles[spacePosition] = tiles[spacePosition + 4];
		tiles[spacePosition + 4] = aux;
			
		setTileOrdered(spacePosition);
		setTileOrdered(spacePosition +  4);
	
		spacePosition += 4;
		
		//if (!invariant()){
		//	throw new InvariantBrokenException("Invariant broken when moving down.");
		//}
		
	}

	/**
	 * Move the empty space up.
	 * @throws InvalidMovementException if the empty space cannot move to the up
	 * 									(it is in the border).
	 * @throws InvariantBrokenException if the invariant if broken when moving the 
	 * 									empty space.
	 */
	private void moveUp() throws InvalidMovementException, InvariantBrokenException {
		if (spacePosition < 4){ 
			throw new InvalidMovementException("Cannot move up.");
		}
		IntTile aux = tiles[spacePosition];
		tiles[spacePosition] = tiles[spacePosition - 4];
		tiles[spacePosition - 4] = aux;
			
		setTileOrdered(spacePosition);
		setTileOrdered(spacePosition -4);
	
		spacePosition -= 4;
		
		//if (!invariant()){
		//	throw new InvariantBrokenException("Invariant broken when moving up.");
		//}
	}

	/**
	 * Sets a value for the ordered attribute of the tile.
	 * @param pos position where the tile ordered attribute is going to be changed.
	 */
	private void setTileOrdered(int pos) {
		if (tiles[pos].getIntValue() == 0 && pos == 15){
			tiles[pos].setOrdered(true);
		}
		else if (tiles[pos].getIntValue() == pos + 1){
			tiles[pos].setOrdered(true);
		}
		else {
			tiles[pos].setOrdered(false);
			setSolved(false);
		    
		}
	}

	/**
	 * Check whether a puzzle can be solved by calculating the number of inverted
	 * positions and position of blank space.
	 *
	 * For even puzzles, blank on even && even inverions or blank on odd and odd inversions
	 * means that can be solved.
     *
	 * For odd puzzles, even number of inversions means solvable.
	 * @return true if it can be solved, false otherwise.
	 */
	public boolean canBeSolved() {
		int inversions = 0;
		int size = (int) Math.sqrt(SIZE);
		int row = 0;
		int blankRow = 0;

		for (int i = 0; i < SIZE; i++)
		{
		    if (i % size == 0) {
		        row++;
		    }
		    if (tiles[i].getIntValue() == 0) {
		        blankRow = row;
		        continue;
		    }
		    for (int j = i + 1; j < SIZE; j++)
		    {
		        if (tiles[i].getIntValue() > tiles[j].getIntValue() && tiles[j].getIntValue() != 0)
		        {
		            inversions++;
		        }
		    }
		}

		// even puzzle
		if (size % 2 == 0) {
		    if (blankRow % 2 == 0)
		        return inversions % 2 == 0;
		    else
		        return inversions % 2 != 0;
		}
		// odd puzzle
		return inversions % 2 == 0;
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
				System.out.println("1");
				return false;
			}
		}
		if (isSolved()!=getSolved()){
			System.out.println("2");
			return false;
		}
		if (spacePosition < IntTile.MIN || spacePosition > IntTile.MAX){
			System.out.println("3");
			return false;
		}
		return true;
	}
	
	/**
	 * To string method that returns the board in the following format:
	 *   1  2  3  4
	 *   5  6  7  8
	 *   9  10 11 12
	 *   13 14 15 __ 
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

	/**
	 * Override cloning function of the Board object.  
	 * Clone each tile of the array of IntTile.
	 * @throws CloneNotSupportedException if it is not supported cloning
	 * @return Cloned object.
	 */
	@Override
    protected Object clone() throws CloneNotSupportedException {
    	Board cloned = (Board)super.clone();
    	IntTile[] clonedTiles = new IntTile[SIZE];
    	IntTile[] originalTiles = cloned.getTiles();
    	int i;
    	for (i = 0; i < SIZE; i++)
    		clonedTiles[i] = (IntTile)originalTiles[i].clone();
    	cloned.setTiles(clonedTiles);
        return cloned;
    }

    /**
	 * Override equals function for Board objects.  
	 * Compare each tile of the IntTile array.
	 * @param Object with which the comparison will be made
	 * @return If there are equals or not.
	 */
    @Override
	public boolean equals(Object other){
	    if (other == null)
	    	return false;
	    if (other == this) 
	    	return true;
	    if (!(other instanceof Board))
	    	return false;

	    Board otherBoard = (Board)other;
	    for (int i = 0 ; i < SIZE; i++) {
	    	if (!tiles[i].equals(otherBoard.getTiles()[i]))
	    		return false;
	    }
	    return true;
	}
}
