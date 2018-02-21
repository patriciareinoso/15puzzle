import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.Collections;

/**
 * Represents the fitness function by using Disorder Heuristic.
 * 
 * @author Oscar Guillen
 *
 */

/**
 * 
 * Class that represents the fitness function linked to Disorder
 * distance. It is calculated adding 1 when a tiles is not in a solved
 * position.
 *
 * Max value = 15.0
 * 
 */
public class FunctionDisorder extends FitnessFunction {

    /**
     * Class constructor.
     * Set {@link #max} to 15.0 as maximun in the heuristic.
     */
    public FunctionDisorder() {
        setMax(15.0f);
    }

    /**
     * Calculate fitness function by adding how many tiles are not in a solved
     * position. It divides by max then.
     * @return fitness value between [0, 1)
     */
    public float calcFitness(Board status) {
        int fitness = 0;
        IntTile[] solved = getSolved().getTiles();
        for (int i = 0;i < status.SIZE ;i++) {
            if (status.getTiles()[i].getIntValue() != solved[i].getIntValue())
                fitness++;
        }
        return (float)fitness / getMax();
    }
}