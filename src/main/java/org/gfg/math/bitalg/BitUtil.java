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

    /**
     * Converts a bit string into its decimal integer representation.
     * @param bitString input bit string
     * @return integer representation of the input bit string
     */
    public static int toInt(String bitString){
        int n = 0;
        for(int i = 0; i < bitString.length(); i++){
            n |= bitString.charAt(i) - '0';
            if(i < bitString.length() - 1) n <<= 1;
        }
        return n;
    }

    /**
     * Converts an integer into its 32-bit binary representation.
     * @param n input integer
     * @return binary string consisting of 32 bits
     */
    public static String toBin(int n){
        StringBuilder sb = new StringBuilder();
        do{
            sb.append(n & 1);
            n >>>= 1;
        }while(n != 0);
        
        // pad with zeroes for 32-bit representation
        int nBits = sb.length();
        for(int i = 0; i < 32 - nBits; i++) sb.append("0");

        return sb.reverse().toString();
    }

    /**
     * Computes a sum of two 32-bit strings. If an overflow occurs, 1 is appended to the string.
     * @param s1 first bit string
     * @param s2 second bit string
     * @return 32-bit string as a sum of the first and the second bit string
     */
    public static String add(String s1, String s2){
        if(s1.length() != 32 || s2.length() != 32){
            throw new IllegalArgumentException("Bit strings must be of length 32");
        } 

        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for(int i = s1.length() - 1; i >= 0; i--){
            int bit1 = s1.charAt(i) - '0';
            int bit2 = s2.charAt(i) - '0';
            sb.append(bit1 ^ bit2 ^ carry);
            carry = (bit1 & bit2) | (bit1 & carry) | (bit2 & carry);
        }
        if(carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }

    /**
     * Converts a binary string to its representation as a byte array.
     * @param bitString binary string
     * @return byte array
     */
    public static byte[] toByteArray(String bitString){
        final int BYTE_LEN = 8;
        byte[] bytes = new byte[(int) Math.ceil((double) bitString.length() / BYTE_LEN)];
        for(int i = 0; i < bytes.length; i++){
            bytes[i] = toByte(bitString.substring(
                i * BYTE_LEN, 
                Math.min(BYTE_LEN * (i  + 1), bitString.length())));
        }
        return bytes;
    }

    private static byte toByte(String byteString){
        byte b = 0;
        for(char c : byteString.toCharArray()){
            b <<= 1;
            b |= (c - '0');
        }
        return b;
    }
}