/**
 * Generic class for a Tile representation.
 * 
 * @author Patricia Reinoso
 */

/** 
 * Generic Tile class that represents a tile.
 * It has an attribute corresponding to a value, and an attribute that
 * indicates whether the tile is in the correct position.
 * 
 */
public class GenericTile<T> {

	/**
	 * Generic value of the tile.
	 */
	protected T value;
	/**
	 * Indicates whether the tile is in the right position.
	 */
	protected boolean isOrdered;

	/**
	 * Constructor of a generic Tile.
	 * @param value of the Tile
	 * @param ordered indicated whether the tile is in the right position.
	 */
	public GenericTile(T value, boolean ordered) {
		this.value = value;
		this.isOrdered = ordered;
	}
	
	/**
	 * Getter of the value of a Tile.
	 * @return the value of the tile.
	 */
	public T getValue() {
		return value;
	}
	
	/**
	 * Setter of the value of the Tile.
	 * @param value of the tile.
	 */
	public void setValue(T value) {
		this.value = value;
	}
	
	/**
	 * Getter of the ordered attribute of a tile.
	 * @return true is the tile is in the right position, false otherwise.
	 */
	public boolean isOrdered() {
		return isOrdered;
	}
	
	/**
	 * Setter of the ordered attribute of that tile.
	 * @param isOrdered true indicates that the tile is in the right position.
	 */
	public void setOrdered(boolean isOrdered) {
		this.isOrdered = isOrdered;
	}
	
	/**
	 * Override equal method that indicate whether the tile is equal to other
	 * object. 2 tiles are equal if their values are equal.
	 * 
	 * @param obj the object the tile is compared to.
	 * @return true is the object is considered equal. False otherwise.
	 */
	@Override
	public boolean equals(Object obj){
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!this.getClass().equals(obj.getClass()))
			return false;
		
		GenericTile other = (GenericTile) obj;
		
		return other.getValue().equals(this.getValue());
	}

}
