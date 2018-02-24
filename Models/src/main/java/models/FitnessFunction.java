package models;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.Collections;

/**
 * Represents the abstract fitness function for solving the 15-puzzle game.
 * 
 * @author Oscar Guillen
 *
 */

/**
 * 
 * Class that represents an abtract class as basis for creating
 * specific fitness function considering the heuristic. The fitness
 * value will be a number between 0 and 1, it means that it must be
 * normalized by using the max number of the worse case to obtain a 
 * correct number between 0 - 1.
 *
 * Uses a solved puzzle as a comparison.
 *
 * Uses a max value to normalize the fitness value.
 */
public abstract class FitnessFunction {

    /**
     * The max value of the worse case.
     */
    private float max = 0.0f;

    /**
     * The solved board.
     */
    private Board solved = new Board();

    /**
     * Abstract function used to calculate the fitness value.
     */
    public abstract float calcFitness(Board status);

    /**
     * Setter of the max value
     * @param new max value
    */
    public void setMax(float value) {
        max = value;
    }

    /**
     * Getter for max value
     * @return max value as float.
     */
    public float getMax() {
        return max;
    }

    /**
     * Getter for the solved board
     * @return solved board.
     */
    public Board getSolved() {
        return solved;
    }
}
