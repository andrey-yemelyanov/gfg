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
}