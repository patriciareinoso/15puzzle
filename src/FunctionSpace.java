import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.Collections;

/**
 * Represents the fitness function by using Space Distance Heuristic.
 * 
 * @author Oscar Guillen
 *
 */

/**
 * 
 * Class that represents the fitness function linked to Space Distance
 * distance. It calculates the distance between the actual
 * position of the space and the position in the solved board.
 *
 * Max value = 6.0
 * 
 */
public class FunctionSpace extends FitnessFunction {

    /**
     * Class constructor.
     * Set {@link #max} to 6.0 as maximun in the heuristic.
     */
    public FunctionSpace () {
        setMax(6.0f);
    }

    /**
     * Calculate fitness function by calculating the distance between the actual
     * position of the space and the position in the solved board. It divides by max then.
     * @return fitness value between [0, 1)
     */
    public float calcFitness(Board status) {
        int space = status.getSpacePosition();
        return (float) (Math.abs(space/4 - 3) + Math.abs(space%4 - 3)) / getMax();
    }
}