package org.gfg.backtrack;

import java.util.*;

/**
 * Given a set of n integers, divide the set in two subsets of n/2 sizes each such that the difference of the sum of two subsets is as minimum as possible. 
 * If n is even, then sizes of two subsets must be strictly n/2 and if n is odd, then size of one subset must be (n-1)/2 and size of other subset must be (n+1)/2.
 */
public class TugOfWar{
    
    /**
     * Solves Tug of War problem and returns a boolean array indicating to which subset each element belongs.
     */
    public static boolean[] solve(int[] set){
        boolean[] solution = new boolean[set.length];
        int totalSum = Arrays.stream(set).sum();
        minDiff = Integer.MAX_VALUE;
        solve(set, 0, totalSum / 2, 0, 0, new boolean[set.length], solution);
        return solution;
    }

    private static int minDiff;
    private static void solve(
        int[] set, 
        int i, 
        int targetSum, 
        int subsetSum, 
        int subsetSize, 
        boolean[] partition,
        boolean[] solution){
        if(subsetSize == set.length / 2){
            int diff = Math.abs(subsetSum - targetSum);
            if(diff < minDiff){
                minDiff = diff;
                System.arraycopy(partition, 0, solution, 0, partition.length);
            }
        }else if (i < set.length){
            // take i'th element
            partition[i] = true;
            solve(set, i + 1, targetSum, subsetSum + set[i], subsetSize + 1, partition, solution);
            partition[i] = false;

            // do not take i'th element
            solve(set, i + 1, targetSum, subsetSum, subsetSize, partition, solution);
        }
    }
}