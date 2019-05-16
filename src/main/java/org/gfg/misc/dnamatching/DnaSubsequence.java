package org.gfg.misc.dnamatching;

/**
 * Contains DNA subsequence along with its corresponding hash code.
 */
public class DnaSubsequence{
    private String subsequence;
    private int hashCode;

    /**
     * Initializes a new instance of {@code DnaSubsequence}.
     * @param subsequence DNA subsequence
     * @param hashCode hash code
     */
    public DnaSubsequence(String subsequence, int hashCode){
        this.subsequence = subsequence;
        this.hashCode = hashCode;
    }

    /**
     * Returns subsequence.
     * @return subsequence
     */
    public String getSubsequence(){
        return subsequence;
    }

    /**
     * Returns hash code associated with this subsequence.
     * @return hash code
     */
    public int hashCode(){
        return hashCode;
    }
}