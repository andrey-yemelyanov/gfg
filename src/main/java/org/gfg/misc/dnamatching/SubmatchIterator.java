package org.gfg.misc.dnamatching;

import java.io.IOException;
import java.util.*;
import org.gfg.Dictionary;
import org.gfg.hash.HashDictionary;

/**
 * Iterator that returns {@link Submatch} tuples containing submatches of size k 
 * in the first and second DNA sequence files.
 */
public class SubmatchIterator implements Iterator<Submatch> {

    private DnaSubsequenceHashIterator A;
    private DnaSubsequenceHashIterator B;
    
    // maps subsequence hash to a list of offsets where this subsequence occurs
    private Dictionary<Integer, List<DnaSubsequence>> dictB;

    /**
     * Initializes a new instance of {@link SubmatchIterator}.
     * @param fastaFile1 path to FASTA file containing the first input
     * @param fastaFile2 path to the FASTA file containing the second input
     * @param k length of subsequence to match for
     */
    public SubmatchIterator(String fastaFile1, String fastaFile2, int k) throws IOException {
        A = new DnaSubsequenceHashIterator(fastaFile1, k);
        B = new DnaSubsequenceHashIterator(fastaFile2, k);

        // build subsequence table for B
        dictB = new HashDictionary<>();
        while(B.hasNext()){
            DnaSubsequence s = B.next();
            if(!dictB.containsKey(s.hashCode())){
                dictB.add(s.hashCode(), new ArrayList<>());
            }
            dictB.get(s.hashCode()).add(s);
        }
    }

    private int i;
    private List<DnaSubsequence> subBList;
    private DnaSubsequence subA;

    @Override
    public boolean hasNext() {
        if(subBList != null && i < subBList.size()) return true;

        while(A.hasNext()){
            subA = A.next();
            if(!dictB.containsKey(subA.hashCode())) continue;
            subBList = dictB.get(subA.hashCode());
            i = 0;
            return true;
        }
        
        return false;
    }

    @Override
    public Submatch next() {
        DnaSubsequence subB = subBList.get(i++);
        if(subA.subsequence().equals(subB.subsequence())){
            return new Submatch(subA.offset(), subB.offset());
        }
        return next();
    }
    
}