package org.gfg.math;

import java.util.*;

/**
 * Contains implementation of math-related utility functions.
 */
public class MathUtils{
    
    /**
     * Computes factors of an integer number.
     * @param n input integer
     * @return a list of factors of n in sorted order
     */
    public static List<Integer> factorize(int n){
        List<Integer> factors = new ArrayList<>();
        List<Integer> larger = new ArrayList<>();
        for(int i = 1; i <= Math.sqrt(n); i++){
            if(n % i == 0){
                factors.add(i);
                if(n / i != i) larger.add(n / i);
            }
        }
        Collections.reverse(larger);
        factors.addAll(larger);
        return factors;
    }

    public static double estimatePI(int nIterations){
        Random rand = new Random();
        int nPoints = 0; int nPointsInCircle = 0;
        for(int i = 0; i < nIterations; i++){
            // generate random x in interval [-0.5, 0.5]
            double x = rand.nextDouble() - 0.5;
            // generate random y in interval [-0.5, 0.5]
            double y = rand.nextDouble() - 0.5;
            // check if the point is inside the circle
            if(x * x + y * y <= 0.5 * 0.5) nPointsInCircle++;
            nPoints++;
        }
        return 4 * ((double) nPointsInCircle / nPoints);
    }
}