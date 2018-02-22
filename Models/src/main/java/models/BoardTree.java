package models;

import tools.InvariantBrokenException;

/**
 * Represents a puzzle board tree.
 * 
 * @author Oscar Guillen.
 */

/**
 * Puzzle Board Tree. Represents a tree of puzzle boards.
 * Destined to be used in a 15-puzzle game solution.
 * Extends {@link GenericTree}.
 * 
 */
public class BoardTree extends GenericTree {

    
    /**
     * Class constructor.
     * Constructs a BoardTree.
     */
    public BoardTree(){
        super();
    }

    /**
     * Class constructor.
     * Constructs a BoardTree. 
     * @param value assigned to the root.
     */
    public BoardTree(BoardTreeNode value){
        super();
        setRoot(value);
    }
    
    /**
     * Setter for the root.
     * Verifies whether obj is an BoardTreeNode.
     * @param obj the board tree node to assign.
     * @throws IllegalArgumentException
     */
    public void setRoot(Object obj) throws IllegalArgumentException{
        if (obj == null || !(obj instanceof BoardTreeNode)){
            throw new IllegalArgumentException("Illegal arguments.");
        }
        BoardTreeNode val = (BoardTreeNode)obj;
        this.root = val;
    }
    
    /**
     * Return the value of the puzzle board as an Board.
     * @return the Board value of the puzzle board.
     */
    public BoardTreeNode getBoardRoot(){
        BoardTreeNode val = (BoardTreeNode)getRoot();
        return val;
    }
    
    /**
     * To string method that returns the tree.
     * @return a string with the desired format.
     */
    public String toString(){
        return getBoardRoot().toString();
    }

}
