import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.Collections;

/**
 * Represents the fitness function for solving the 15-puzzle game.
 * 
 * @author Oscar Guillen
 *
 */

public class FitnessFunction {

    public static final float maxDisorder = 15.0f;

    public static final float maxManhattan = 96.0f;

    public static final float maxSpace = 6.0f;

    private String heuristic;

    private Board solved = new Board();
    
    public FitnessFunction (String heu) {
        heuristic = heu;
    }

    public FitnessFunction () {
        heuristic = "";
    }

    public void setHeuristic (String heu) {
        heuristic = heu;
    }

    public String getHeuristic () {
        return heuristic;
    }

    // Disorder Heuristic
    public float disorder(Board status) {
        int fitness = 0;
        IntTile[] solved = solved.getTiles();
        for (int i = 0;i < status.SIZE ;i++) {
            if (status.getTiles()[i].getIntValue() != solved[i].getIntValue())
                fitness++;
        }
        return (float)fitness / maxDisorder;
    }

    // Manhattan Distance Heuristic
    // PD: Check maximum sum
    public float manhattanDistance(Board status) {
        int fitness = 0;
        IntTile[] puzzle = status.getTiles();
        IntTile[] solved = solved.getTiles();
        for (int i = 0; i < status.SIZE; i++) {
            if (puzzle[i].getIntValue() != solved[i].getIntValue()) {
                fitness += (Math.abs(puzzle[i].getIntValue()/4 - i/4) + Math.abs(puzzle[i].getIntValue()%4 - i%4));
            }
        }
        return (float) fitness / maxManhattan; 
    }

    // Space Position Heuristic
    public float spacePosition(Board status) {
        int space = status.getSpacePosition();
        return (float) (Math.abs(space/4 - 3) + Math.abs(space%4 - 3)) / maxSpace;
    }

    public float calcFitness(Board status) {
        if (heuristic.equals("space"))
            return spacePosition(status);
        else if (heuristic.equals("manhattan"))
            return manhattanDistance(status);
        else if (heuristic.equals("disorder"))
            return disorder(status);
        return 0.0f;
    }
}
