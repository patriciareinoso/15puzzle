import tools.InvariantBrokenException;
import java.util.Comparator;

/**
 * Represents a node of the board tree in the 15-puzzle solution.
 * 
 * @author Oscar Guillen.
 */

/**
 * Board Node. Represents node which values are boards.
 * Destined to be used in a 15-puzzle solution.
 * Extends {@link GenericNode}.
 * 
 */
public class BoardTreeNode extends GenericNode {

    /**
     * Fitness.
     */
    private int fitness;

    private SolvingSequence.Direction movement;

    /**
     * Class constructor.
     * Constructs a BoardTreeNode.
     */
    public BoardTreeNode(){
        super();
    }

    /**
     * Class constructor.
     * Constructs a BoardTreeNode. 
     * @param value assigned to the board.
     */
    public BoardTreeNode(Board value){
        super();
        setValue(value);
    }
    
    /**
     * Setter for the value.
     * Verifies whether obj is an Integer.
     * @param obj the board to assign.
     * @throws IllegalArgumentException if the class is incorrect.
     */
    @Override 
    public void setValue(Object obj) throws IllegalArgumentException{
        if (obj == null || !(obj instanceof Board)){
            throw new IllegalArgumentException("Illegal arguments.");
        }
        Board val = (Board)obj;
        this.value = val;
    }
    
    /**
     * Return the value as an Board.
     * @return the value of the board.
     */
    public Board getBoardValue(){
        Board val = (Board)getValue();
        return val;
    }

    public int getFitness() {
        return this.fitness;
    }

    public void setFitness(int value) {
        this.fitness = value;
    }

    public SolvingSequence.Direction getMovement() {
        return this.movement;
    }

    public void setMovement(SolvingSequence.Direction dir) {
        this.movement = dir;
    }
    
    public String toString(){
        return getBoardValue().toString();
    }

    public static Comparator<BoardTreeNode> NodeFitness = new Comparator<BoardTreeNode>() {

        public int compare(BoardTreeNode n1, BoardTreeNode n2) {

           int fitness1 = n1.getFitness();
           int fitness2 = n2.getFitness();

           /*For ascending order*/
           return fitness1-fitness2;
        }
    };

}
