package org.gfg.misc.dnamatching;

/**
 * A tuple (x, y) that indicates that the k-length 
 * subsequence at position x in the first input matches the 
 * subsequence at position y in the second input.
 */
public class Submatch{
    private int x;
    private int y;

    /**
     * Initializes a new instance of {@link Submatch}.
     * @param x offset in the first input
     * @param y offset in the second input
     */
    public Submatch(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Returns offset in the first input.
     * @return offset
     */
    public int x(){
        return x;
    }

    /**
     * Returns offset in the second input.
     * @return offset
     */
    public int y(){
        return y;
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Submatch)) return false;
        Submatch other = (Submatch) obj;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public String toString(){
        return String.format("(%d, %d)", x, y);
    }
}