package org.gfg.misc.dnamatching;

import java.util.*;
import java.io.IOException;

/**
 * An iterator that returns DNA subsequences of length k along with their corresponding hash codes.
 */
public class DnaSubsequenceHashIterator implements Iterator<DnaSubsequence> {

    private DnaSubsequenceIterator subsequenceIterator;
    private DnaSubsequence currentSubsequence;
    private RollingHash rollingHash;

    /**
     * Initializes a new instance of {@code DnaSubsequenceHashIterator}.
     * @param fastaFile DNA sequence file in FASTA format
     * @param k length of subsequence
     * @throws IOException if I/O error occurs when opening the file
     */
    public DnaSubsequenceHashIterator(String fastaFile, int k) throws IOException {
        subsequenceIterator = new DnaSubsequenceIterator(fastaFile, k);
    }

    @Override
    public boolean hasNext() {
        if(!subsequenceIterator.hasNext()) return false;
        String subsequence = subsequenceIterator.next();
        
        if(currentSubsequence == null){
            rollingHash = new RollingHash(subsequence);
        }else{
            rollingHash.slide(subsequence.charAt(subsequence.length() - 1));
        }

        currentSubsequence = new DnaSubsequence(subsequence, rollingHash.currentHash());
        return true;
    }

    @Override
    public DnaSubsequence next() {
        return currentSubsequence;
    }

}