package models;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.Collections;

/**
 * Represents the solution of the 15-puzzle game.
 * 
 * @author Oscar Guillen
 *
 */

/**
 * 
 * Class that represents the solution of the puzzle.
 * The possible solutions are stored in a Board Tree.
 * The solution stores the sequence of movements. 
 * The solution uses a fitness function to select a feasible way.
 * A board is considered as solved if all the tiles 
 * inside are in the correct position.
 */
public class Solution {
    
    /**
     * Tree of Board. The root is the initial board to be solved.
     */
    private BoardTree solution;

    /**
     * Store the sequence of movements to reach the solution.
     */
    private SolvingSequence sequence;

    /**
     * The fitness function to evaluate how close is to achieve the solution.
     */
    private FitnessFunction funct;

    /**
     * Class constructor.
     * Create an empty Tree of Boards, empty sequence and fitness function
     * with an especific heuristic.
     * @param identification of the heuristic 
     */
    public Solution (String heu) {
        solution = new BoardTree();
        sequence = new SolvingSequence();
        funct = FitnessFunctionFactory.getFunction(heu);
    }

    /**
     * Class constructor.
     * Create an existing tree of boards, empty sequence and fitness function
     * with an especific heuristic.
     * @param existing tree
     * @param identification of the heuristic 
     */
    public Solution (BoardTree initial, String heu) {
        solution = initial;
        sequence = new SolvingSequence();
        funct = FitnessFunctionFactory.getFunction(heu);
    }

    /**
     * Setter of the tree of boards.
     * @param tree of boards
     */
    public void setSolution (BoardTree solution) {
        solution = solution;
    }

    /**
     * Getter of tree of boards
     * @return tree of boards.
     */
    public BoardTree getSolution () {
        return solution;
    }

    /**
     * Getter of the solving sequence.
     * @return list of movements to reach the solution
     */
    public SolvingSequence getSequence () {
        return sequence;
    }

    /**
     * Getter of board children. 
     * Create the possible children from a board node based on valid movements.
     * Create the children by cloning the parent and adding the corresponding
     * movement.
     * @param parent node.
     * @return a list of children nodes
     * @throws CloneNotSupportedException if it is not possible to clone the parent.
     */
    public ArrayList<BoardTreeNode> getMovements (BoardTreeNode parent) throws CloneNotSupportedException {
        ArrayList<BoardTreeNode> movements = new ArrayList<BoardTreeNode>();
        Board board = parent.getBoardValue();
        int space = board.getSpacePosition();
        if (space >= 4) {
            Board newBoard = ((Board)board.clone());
            newBoard.moveEmptyTile(SolvingSequence.Direction.UP);
            //System.out.println("up \n" + up);
            BoardTreeNode up = new BoardTreeNode(newBoard);
            up.setMovement(SolvingSequence.Direction.UP);
            movements.add(up);
        }
        if (space <= 11) {
            Board newBoard = ((Board)board.clone());
            newBoard.moveEmptyTile(SolvingSequence.Direction.DOWN);
            //System.out.println("down\n" + down);
            BoardTreeNode down = new BoardTreeNode(newBoard);
            down.setMovement(SolvingSequence.Direction.DOWN);
            movements.add(down);
        }
        if (space % 4 != 0) {
            Board newBoard = ((Board)board.clone());
            newBoard.moveEmptyTile(SolvingSequence.Direction.LEFT);
            //System.out.println("left\n" + left);
            BoardTreeNode left = new BoardTreeNode(newBoard);
            left.setMovement(SolvingSequence.Direction.LEFT);
            movements.add(left);
        }
        if ((space + 1) % 4 != 0) {
            Board newBoard = ((Board)board.clone());
            newBoard.moveEmptyTile(SolvingSequence.Direction.RIGHT);
            //System.out.println("right\n" + right);
            BoardTreeNode right = new BoardTreeNode(newBoard);
            right.setMovement(SolvingSequence.Direction.RIGHT);
            movements.add(right);
        }

        return movements;
    }


    /**
     * Adding the sequence of moves to reach the solution.
     * Start from the solution node until the root of the tree.
     * The result is stored in the solving sequence attribute.
     * @param goal node
     */
    public void findPath(BoardTreeNode goal) {
        if (goal != null) {
            SolvingSequence.Direction dir = goal.getMovement();
            if (dir != null)
                this.getSequence().addMovement(0, dir);
            findPath((BoardTreeNode)goal.getParent());
        }

    }

    /**
     * Solve the puzzle by using A* algorithm implementation.
     * Put the given puzzle as root and start creating nodes until goal puzzle.
     * Use the fitness function to evaluate a node and decide the next step by 
     * choosing the most suitable value. In this case, the lowest one.
     * Store the opened and closed node to avoid repetition of paths.
     *
     * PD: There is no impossible solution verification!!!!
     * 
     * @param initial puzzle.
     */
    public void solve (Board puzzle) throws CloneNotSupportedException, InterruptedException {
        BoardTree routes = new BoardTree();
        BoardTreeNode root = new BoardTreeNode();

        ArrayList<BoardTreeNode> opened = new ArrayList<BoardTreeNode>();
        ArrayList<BoardTreeNode> closed = new ArrayList<BoardTreeNode>();

        root.setValue(puzzle);
        routes.setRoot(root);
        
        opened.add(root);

        //System.out.println("Root opened");

        while(opened.size() > 0) {

            BoardTreeNode actual = opened.remove(0);
            //System.out.println("Actual: Fitness " + Float.toString(actual.getFitness()) + "\n " + actual.getBoardValue());
            TimeUnit.SECONDS.sleep(2);

            if (actual.getBoardValue().isSolved()) {
                findPath(actual);
                break;
            }

            ArrayList<BoardTreeNode> successors = getMovements(actual);
            for (BoardTreeNode suc : successors) {

                suc.setFitness(funct.calcFitness(suc.getBoardValue()));

                int indexOpened = opened.indexOf(suc);
                //System.out.println("Opened: " + indexOpened + "\n");
                if (indexOpened >= 0) {
                    //System.out.println("I'm opened.\n");
                    BoardTreeNode node = opened.get(indexOpened);
                    if (node.getFitness() <= suc.getFitness())
                        continue;
                }

                int indexClosed = closed.indexOf(suc);
                //System.out.println("Closed: " + indexClosed + "\n");
                if (indexClosed >= 0) {
                    //System.out.println("I'm closed.\n");
                    BoardTreeNode node = closed.get(indexClosed);
                    if (node.getFitness() <= suc.getFitness())
                        continue;
                }


                if (indexOpened != -1)
                  opened.remove(indexOpened);

                if (indexClosed != -1)
                  closed.remove(indexClosed);

                actual.addChild(suc);
                opened.add(suc);
                //System.out.println("I add. " + suc.getFitness() +  "\n");

            }

            closed.add(actual);
            Collections.sort(opened, BoardTreeNode.NodeFitness);
        }

    }
    
    public static void main(String[] args) {
        Board b1 = new Board();
        System.out.println("B1 \n" + b1);
        SolvingSequence seq = new SolvingSequence();
        seq.addMovement(SolvingSequence.Direction.LEFT);
        // seq.addMovement(SolvingSequence.Direction.UP);
        // seq.addMovement(SolvingSequence.Direction.UP);
        // seq.addMovement(SolvingSequence.Direction.UP);
        // seq.addMovement(SolvingSequence.Direction.RIGHT);
        // seq.addMovement(SolvingSequence.Direction.DOWN);
        //System.out.println("Seq \n" + seq);
        b1.applySolvingSequence(seq);
        System.out.println("INITIAL \n" + b1);
        //System.out.println("B1 \n" + b1.isSolved());

        Solution s1 = new Solution("Disorder");
        try {
            s1.solve(b1);
            System.out.println("I found a path! " + s1.getSequence().toString());
        } catch (Exception e) {
          /* ... */
        }
    }
}
