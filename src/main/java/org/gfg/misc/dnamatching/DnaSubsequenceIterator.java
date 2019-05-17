package org.gfg.misc.dnamatching;

import java.io.IOException;
import java.util.*;

/**
 * An iterator that returns all nucleotide subsequences of length k stored in the given FASTA
 * file.
 */
public class DnaSubsequenceIterator implements Iterator<String> {

    private DnaSequenceIterator sequence;
    private int k;
    private StringBuilder currentSubSeq;

    /**
     * Initializes a new instance of {@link DnaSubsequenceIterator}.
     * @param fastaFile path to DNA sequence file in FASTA format
     * @param k length of the subsequence returned by this iterator
     * @throws IOException
     */
    public DnaSubsequenceIterator(String fastaFile, int k) throws IOException {
        sequence = new DnaSequenceIterator(fastaFile);
        currentSubSeq = new StringBuilder();
        this.k = k;
    }

    @Override
    public boolean hasNext(){
        if(!sequence.hasNext()) return false;
        if(currentSubSeq.length() == 0){
            int i = 0;
            do{
                currentSubSeq.append(sequence.next());
                i++;
            }
            while(i < k && sequence.hasNext());
            return true;
        }
        currentSubSeq.deleteCharAt(0);
        currentSubSeq.append(sequence.next());
        return true;
    }

    @Override
    public String next() {
        return currentSubSeq.toString();
    }
}