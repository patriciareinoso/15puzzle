import tools.InvariantBrokenException;

/**
 * Represents a tile in the 15-puzzle game.
 * 
 * @author Patricia Reinoso.
 */

/**
 * Integer Tile. Represents a tile which values are integer.
 * Destined to be used in a 15-puzzle game.
 * Extends {@link GenericTile}.
 * 
 * The tiles are numbered from 1 to 15.
 * The empty tile is represented as a IntTale with value 0.
 * 
 */
public class IntTile extends GenericTile {

	/**
	 * Minimum value of a IntTile.
	 */
	public static final int MIN = 0;
	/**
	 * Maximum value a IntTile.
	 */
	public static final int MAX = 15;
	/**
	 * Total amount of tiles in the game.
	 */
	public static final int TOTAL = MAX - MIN + 1;
	
	/**
	 * Class constructor.
	 * Constructs a IntTile. 
	 * @param value assigned to the tile.
	 * @param ordered indicates whether the tile is in the right position.
	 * @throws InvariantBrokenException if the value is smaller than {@link #MIN}
	 * 			or greater that {@link #MAX}.
	 */
	public IntTile(Integer value, boolean ordered) throws InvariantBrokenException{
		super(MIN,false);
		setValue(value);
		setOrdered(ordered);
		if(!invariant()){
			throw new InvariantBrokenException("Invariant broken.");
		}
	}
	
	/**
	 * Setter for the value.
	 * Verifies whether obj is an Integer.
	 * @param obj the integer to assign.
	 * @throws IllegalArgumentException if the value is smaller than {@link #MIN}
	 * 			or greater that {@link #MAX}.
	 */
	@Override 
	public void setValue(Object obj) throws IllegalArgumentException{
		if (obj == null || !(obj instanceof Integer)){
			throw new IllegalArgumentException("Illegal arguments.");
		}
		Integer val = (Integer)obj;
		if (val < MIN || val > MAX){
			throw new IllegalArgumentException("Illegal arguments.");
		}
		this.value = val;
	}
	
	/**
	 * Return the value of the tile as an Integer.
	 * @return the integer value of the tile.
	 */
	public int getIntValue(){
		int val = (Integer)getValue();
		return val;
	}
	
	/**
	 * Check thath the value of the tile is in the range {@link #MIN} .. {@link #MAX}
	 * @return True is the condition is reached. False otherwise.
	 */
	public boolean invariant(){
		if (getIntValue() <= MAX && getIntValue() >=  MIN){
			return true;
		}
		return false;
	}
	
	public String toString(){
		return Integer.toString(getIntValue()) + " " + isOrdered();
	}
	
	public static void main(String[] args){
		IntTile t1 = new IntTile(5,true);
		IntTile t2 = new IntTile(10,true);
		System.out.println("T1 " + t1);
		System.out.println("T2 " + t2);
		System.out.println("T1 equals T2 " + t1.equals(t2));
		t1.setValue(10);
		t1.setOrdered(false);
		System.out.println("T1 " + t1);
		System.out.println("T1 equals T2 " + t1.equals(t2));
		IntTile t3 = new IntTile(16,true);
	}

}
