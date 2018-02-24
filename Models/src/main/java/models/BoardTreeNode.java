package models;

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
 * A fitness value of the board inside is added. Furthemore, a movement
 * indicates the move into the puzzle to reach the actual node from the parent.
 * 
 */
public class BoardTreeNode extends GenericNode {

    /**
     * Fitness value of tha actual board inside the node.
     */
    private float fitness;

    /**
     * Move applied to move from parent to actual node.
     */
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

    /**
     * Return the fitness value.
     * @return the fitness value
     */
    public float getFitness() {
        return this.fitness;
    }

    /**
     * Setter for the fitness value.
     * @param value the new fitness value.
     */
    public void setFitness(float value) {
        this.fitness = value;
    }

    public SolvingSequence.Direction getMovement() {
        return this.movement;
    }

    /**
     * Setter for move to get this node.
     * @param value of the move.
     */
    public void setMovement(SolvingSequence.Direction dir) {
        this.movement = dir;
    }
    
    /**
     * To string method that returns the node.
     * @return a string with the desired format.
     */
    public String toString(){
        return getBoardValue().toString();
    }

    /**
     * Comparator to compare two nodes.
     */
    public static Comparator<BoardTreeNode> NodeFitness = new Comparator<BoardTreeNode>() {

        public int compare(BoardTreeNode n1, BoardTreeNode n2) {
            float fitness1 = n1.getFitness();
            float fitness2 = n2.getFitness();

            if (fitness1 > fitness2) return 1;
            if (fitness1 < fitness2) return -1;
            return 0;
        }
    };

}
