package org.gfg.misc.dnamatching;

/**
 * Contains DNA subsequence along with its corresponding hash code and offset.
 */
public class DnaSubsequence{
    private int hashCode;
    private int offset;

    /**
     * Initializes a new instance of {@code DnaSubsequence}.
     * @param hashCode hash code
     * @param offset position in the original sequence where the subsequence occurs
     */
    public DnaSubsequence(int hashCode, int offset){
        this.hashCode = hashCode;
        this.offset = offset;
    }

    /**
     * Returns hash code associated with this subsequence.
     * @return hash code
     */
    public int hashCode(){
        return hashCode;
    }

    /**
     * Returns position in the input sequence where this subsequence occurs.
     * @return subsequence offset
     */
    public int offset(){
        return offset;
    }
}