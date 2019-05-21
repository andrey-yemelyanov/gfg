package org.gfg.misc.dnamatching;

/**
 * Produces hash values for a rolling character sequence.
 */
public class RollingHash{
    private StringBuilder sb;
    private int hash;
    private int powerBase;

    private static final int BASE = 7;

    /**
     * Initializes a new instance of {@link RollingHash}.
     * @param sequence initial string sequence for which hash is computed
     */
    public RollingHash(String sequence){
        sb = new StringBuilder(sequence);
        int windowSize = sb.length();
        powerBase = (int) Math.pow(BASE, windowSize - 1);

        // compute initial hash
        for(int i = 0; i < sequence.length(); i++){
            hash = hash * BASE + (int) sequence.charAt(i);
        }
    }

    /**
     * Returns current hash associated with this rolling hash.
     * @return current hash
     */
    public int currentHash(){
        return hash;
    }

    /**
     * Updates the hash by removing the first character and appending newChar to the character sequence.
     * @param newChar new character to slide into the sequence
     * @return updated hash value
     */
    public int slide(char newChar){
        char firstChar = sb.charAt(0);
        sb.deleteCharAt(0);
        sb.append(newChar);
        hash = (hash - (int) firstChar * powerBase) * BASE + (int) newChar;
        return hash;
    }

    /**
     * Returns current sequence associated with this rolling hash.
     * @return string sequence
     */
    public String sequence(){
        return sb.toString();
    }
}