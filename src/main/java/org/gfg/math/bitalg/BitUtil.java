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

    /**
     * Increments a given integer by one.
     * @param x input integer
     * @return incremented integer value
     */
    public static int addOne(int x){
        int mask = 1;
        while((x & mask) != 0){
            x ^= mask;
            mask <<= 1;
        }
        x ^= mask;
        return x;
    }

    /**
     * Multiplies a given integer by 3.5.
     * @param x input integer
     * @return integer part of the product
     */
    public static int multiplyBy3Dot5(int x){
        return (x << 1) + x + (x >> 1);
    }

    /**
     * Checks if the input integer is a power of 4.
     * @param x input integer
     * @return true if the input integer is a power of 4.
     */
    public static boolean isPowerOf4(int x){
        if(x == 0) return false;
        boolean isPowerOf2 = (x & (x - 1)) == 0;
        if(isPowerOf2){
            int nOffBits = 0;
            while((x & (1 << nOffBits)) == 0) nOffBits++;
            return (nOffBits & 1) == 0;
        }
        return false;
    }
}