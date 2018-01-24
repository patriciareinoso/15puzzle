import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.Collections;

/**
 * Represents the solution of the 15-puzzle game.
 * 
 * @author Oscar Guillen
 *
 */

public class Solution {
    
    private BoardTree solution;

    private SolvingSequence sequence;
    
    public Solution () {
        solution = new BoardTree();
        sequence = new SolvingSequence();
    }

    public Solution (BoardTree initial) {
        solution = initial;
        sequence = new SolvingSequence();
    }

    public void setSolution (BoardTree solution) {
        solution = solution;
    }

    public BoardTree getSolution () {
        return solution;
    }

    public SolvingSequence getSequence () {
        return sequence;
    }

    // Desorder Heuristic
    public int getFitness(Board status) {
        int fitness = 0;
        for (int i = 0;i < status.SIZE ;i++) {
            if (status.getTiles()[i].getIntValue() == i + 1) {
                continue;
            }
            else{
                fitness++;
            }
        }
        return fitness;
    }

    // Get possible successors of a board
    public ArrayList<BoardTreeNode> getMovements (Board board) throws CloneNotSupportedException {
        ArrayList<BoardTreeNode> movements = new ArrayList<BoardTreeNode>();
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


    // From goal to root.
    public void findPath(BoardTreeNode goal) {
        if (goal != null) {
            SolvingSequence.Direction dir = goal.getMovement();
            if (dir != null)
                this.getSequence().addMovement(0, dir);
            findPath((BoardTreeNode)goal.getParent());
        }

    }

    // A* implementation.
    public void solve (Board puzzle) throws CloneNotSupportedException, InterruptedException {
        BoardTree routes = new BoardTree();
        BoardTreeNode root = new BoardTreeNode();

        ArrayList<BoardTreeNode> opened = new ArrayList<BoardTreeNode>();
        ArrayList<BoardTreeNode> closed = new ArrayList<BoardTreeNode>();

        root.setValue(puzzle);
        routes.setRoot(root);
        
        opened.add(root);

        System.out.println("Root opened");

        while(opened.size() > 0) {

            BoardTreeNode actual = opened.remove(0);
            System.out.println("Actual: Fitnnes " + Integer.toString(actual.getFitness()) + "\n " + actual.getBoardValue());
            TimeUnit.SECONDS.sleep(2);

            if (actual.getBoardValue().isSolved()) {
                findPath(actual);
                break;
            }

            ArrayList<BoardTreeNode> successors = getMovements(actual.getBoardValue());
            for (BoardTreeNode suc : successors) {

                suc.setFitness(getFitness(suc.getBoardValue()));

                int indexOpened = opened.indexOf(suc);

                if (indexOpened > 0) {
                    BoardTreeNode node = opened.get(indexOpened);
                    if (node.getFitness() >= suc.getFitness())
                        continue;
                }

                int indexClosed = closed.indexOf(suc);

                if (indexClosed > 0) {
                    BoardTreeNode node = closed.get(indexClosed);
                    if (node.getFitness() >= suc.getFitness())
                        continue;
                }


                if (indexOpened != -1)
                  opened.remove(indexOpened);

                if (indexClosed != -1)
                  closed.remove(indexClosed);

                actual.addChild(suc);
                opened.add(suc);
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
        seq.addMovement(SolvingSequence.Direction.UP);
        seq.addMovement(SolvingSequence.Direction.UP);
        seq.addMovement(SolvingSequence.Direction.UP);
        seq.addMovement(SolvingSequence.Direction.RIGHT);
        seq.addMovement(SolvingSequence.Direction.DOWN);
        System.out.println("Seq \n" + seq);
        b1.applySolvingSequence(seq);
        System.out.println("NEW \n" + b1);
        System.out.println("B1 \n" + b1.isSolved());

        Solution s1 = new Solution();
        try {
            s1.solve(b1);
            System.out.println("I found a path! " + s1.getSequence().toString());
        } catch (Exception e) {
          /* ... */
        }
    }
}
