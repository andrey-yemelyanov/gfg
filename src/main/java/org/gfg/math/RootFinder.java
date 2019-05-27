package org.gfg.math;

import java.math.BigInteger;

/**
 * Implements root finding algorithm using Newton's method.
 */
public class RootFinder{
    /**
     * Computes a square root of an integer with {@code nPrecisionDigits} precision digits.
     * @param n number whose square root to compute
     * @param nPrecisionDigits number of precision digits in the result
     * @return integer representation of all precision digits of square root of {@code n}
     */
    public static BigInteger sqrt(int n, int nPrecisionDigits){
        BigInteger factor = BigInteger.valueOf(10).pow(nPrecisionDigits);
        BigInteger a = factor.multiply(BigInteger.valueOf(n));

        // using Newton's method compute Xi+1 = (Xi + a/Xi) / 2 until we converge on a value of X
        BigInteger xPrev = a;
        BigInteger x = xPrev;
        do{
            xPrev = x;
            x = xPrev.add(a.multiply(factor).divide(xPrev)).shiftRight(1);
        }while(!x.equals(xPrev));

        return x;
    }
}