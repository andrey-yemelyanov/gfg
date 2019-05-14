package org.gfg.misc.dnamatching;

import java.io.*;
import java.util.*;

/**
 * An iterator that returns the nucleotide sequence stored in the given FASTA
 * file.
 */
public class DnaSequenceIterator implements Iterator<Character> {

    private BufferedReader reader;
    private char currentChar;

    /**
     * Initializes a new instance of {@link DnaSequenceIterator}.
     * @param fastaFile path to DNA sequence file in FASTA format
     * @throws IOException if I/O error occurs while opening the FASTA file
     */
    public DnaSequenceIterator(String fastaFile) throws IOException {
        reader = new BufferedReader(new FileReader(fastaFile));
        reader.readLine(); // consume the first line with non-essential characters
    }

    @Override
    public boolean hasNext() {
        try {
            int value = 0;
            while((value = reader.read()) != -1 && !Character.isLetter((char)value)){}
            if(value == -1) return false;
            currentChar = (char) value;
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public Character next() {
        return currentChar;
    }

}