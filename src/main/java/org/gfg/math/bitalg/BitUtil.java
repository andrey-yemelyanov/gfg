package org.gfg.math.bitalg;

/**
 * Contains bit logic based utility functions.
 */
public class BitUtil{
    /**
     * Checks if two integers have opposite signs.
     * @param x first input integer
     * @param y second input integer
     * @return true if x and y have opposite signs
     */
    public static boolean oppositeSigns(int x, int y){
        // two ints have opposite signs if the sign bit is 1 after XOR-ing x and y
        return ((x ^ y) < 0);
    }
}