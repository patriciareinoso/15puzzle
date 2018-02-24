package models;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.Collections;

/**
 * Represents the fitness function by using Manhattan Distance Heuristic.
 * 
 * @author Oscar Guillen
 *
 */

/**
 * 
 * Class that represents the fitness function linked to Manhattan Distance
 * distance. It is calculated adding all the distances between an actual
 * position and its position on the solved puzzle.
 *
 * Max value = 96.0
 * 
 */
public class FunctionManhattan extends FitnessFunction {

    /**
     * Class constructor.
     * Set {@link #max} to 96.0 as maximun in the heuristic.
     */
    public FunctionManhattan () {
        setMax(96.0f);
    }

    /**
     * Calculate fitness function by adding the distance between actual tiles and
     * their real position on the solved board. It divides by max then.
     * @return fitness value between [0, 1)
     */
    public float calcFitness(Board status) {
        int fitness = 0;
        IntTile[] puzzle = status.getTiles();
        IntTile[] solved = getSolved().getTiles();
        for (int i = 0; i < status.SIZE; i++) {
            if (puzzle[i].getIntValue() != solved[i].getIntValue()) {
                fitness += (Math.abs(puzzle[i].getIntValue()/4 - i/4) + Math.abs(puzzle[i].getIntValue()%4 - i%4));
            }
        }
        return (float) fitness / getMax();
    }
}
