package models;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.Collections;

/**
 * Represents the factory function to obtain a certain specific fitness fuction
 * by considering the selected heuristic.
 * 
 * @author Oscar Guillen
 *
 */

/**
 * 
 * Class that represents the factory to build fitness function by
 * considering the heuristic to be used.
 *
 * Uses a enum that represent the name of the heuristic.
 */
public class FitnessFunctionFactory {

    /**
     * enum which represents the heuristic name.
     */
    public enum Heuristic {Linear, Disorder, Manhattan, Space};

    /**
     * Return a build fitness function depending on the heuristic received.
     * @return FitnessFunction linked to the heuristic.
     * @throws IllegalArgumentException if the name is not valid.
     */
    public static FitnessFunction getFunction(String name) {
        switch (Heuristic.valueOf(name)) {
            case Linear:
                return new FunctionLinear();
            case Disorder:
                return new FunctionDisorder();
            case Manhattan:
                return new FunctionManhattan();
	    case Space:
                return new FunctionSpace();
        }
        throw new IllegalArgumentException("The name function " + name + " is not recognized.");
    }
}
