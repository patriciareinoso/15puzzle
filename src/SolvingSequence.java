import java.util.ArrayList;

/**
 * Represents a sequence of movements to apply to the 15-puzzle board.
 * The movement are represented by the direction the empty space is going to
 * move.
 * 
 * @author Patricia
 *
 */

public class SolvingSequence {

	/**
	 * Represents the possible directions the empty space can move.
	 */
	public enum Direction {
		UP, DOWN, LEFT, RIGHT;
	}
	
	/**
	 * Sequence of movements to apply to the board.
	 */
	private ArrayList<Direction> seq; 
	
	public SolvingSequence() {
		seq = new ArrayList<Direction>();
		
	}
	
	/**
	 * Retrieve the sequence.
	 * @return the sequence of direction.
	 */
	public ArrayList<Direction> getSeq (){
		return seq;
	}
	
	/**
	 * Set a sequence of directions from a list.
	 * @param list of directions to assign to the sequence.
	 */
	public void setSeq(ArrayList<Direction> list){
		seq = list;
	}
	
	/**
	 * Add a movement to the sequence.
	 * @param dir the direction to add to the sequence.
	 */
	public void addMovement(Direction dir){
		seq.add(dir);
	}
	
	/**
	 * Retrieves the length of the sequence.
	 * @return integer length of the solving sequence.
	 */
	public int getLenght (){
		return seq.size();
	}

	/**
	 * Returns a string with the directions separated by space.
	 * @return a string with the directions.
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("");
		for(Direction dir : seq){
			sb.append(dir);
			sb.append("  ");
		}
		return sb.toString();
	}
	
	public static void main(String[] args){
		SolvingSequence seq = new SolvingSequence();
		seq.addMovement(Direction.UP);
		seq.addMovement(Direction.DOWN);
		seq.addMovement(Direction.LEFT);
		seq.addMovement(Direction.RIGHT);
		seq.addMovement(Direction.DOWN);
		seq.addMovement(Direction.LEFT);
		seq.addMovement(Direction.DOWN);
		seq.addMovement(Direction.RIGHT);
		System.out.println("Seq \n" + seq);
		System.out.println("Length " + seq.getLenght() );
	}
}
