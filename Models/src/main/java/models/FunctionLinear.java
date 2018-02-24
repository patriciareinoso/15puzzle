package models;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.Collections;

/**
 * Represents the fitness function by using Linear Distance Heuristic.
 * 
 * @author Oscar Guillen
 *
 */

/**
 * 
 * Class that represents the fitness function linked to Linear Distance
 * heuristic. It calculates the distance between the actual
 * position and its goal position and sums for each tile.
 *
 * Max value = 120.0
 * 
 */
public class FunctionLinear extends FitnessFunction {

    /**
     * Class constructor.
     * Set {@link #max} to 120.0 as maximun in the heuristic.
     */
    public FunctionLinear() {
        setMax(120.0f);
    }

    /**
     * Calculate fitness function by calculating 
     * @return fitness value between [0, 1)
     */
    public float calcFitness(Board status) {
        float sum=0;
		IntTile[] puzzle = status.getTiles();
		for (int i = 0; i < status.SIZE; i++)
		{
			sum += (Math.abs(i - (puzzle[i].getIntValue()-1)));
		}
		return (float) sum/getMax();
    }
}
