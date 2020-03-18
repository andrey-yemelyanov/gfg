package org.gfg.greedy;

import static org.junit.Assert.assertThat;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

public class HuffmanTest {
    @Test
    public void encodeAndDecodeText() {
        Arrays.asList(new Object[] { "geeksforgeeks", 35 }, 
                      new Object[] { "deeds", 8 }, 
                      new Object[] { "muck", 8 },
                      new Object[] { "go go gophers", 37 }, 
                      new Object[] { "streets are stone stars are not", 92 },
                      new Object[] {
                        "In this section we'll see the basic programming steps in implementing Huffman coding. There are two parts to an implementation: a compression program and a decompression program. We'll assume these are separate programs, but they actually have many common functions. For this set of programming exercises/assignment, you will deal mainly with decompression. However, to do decompression, we have to understand compression.",
                        1822 })
              .forEach(o -> runTest((String) o[0], (int) o[1]));
    }

    private void runTest(String text, int bitLen) {
        Huffman.Result result = Huffman.encode(text);
        assertThat(result.bitLength, is(bitLen));
        assertThat(Huffman.decode(result.encodedText, result.tree, result.bitLength), is(text));
    }

    @Test
    public void compressFile() throws FileNotFoundException {
        URL url = this.getClass().getResource("/alice_in_wonderland.txt");
        Scanner s = new Scanner(new File(url.getFile()));
        StringBuilder sb = new StringBuilder();
        while(s.hasNext()){
            sb.append(s.nextLine());
            if(s.hasNext()) sb.append("\n");
        }
        String text = sb.toString();
        Huffman.Result result = Huffman.encode(text);
        System.out.printf("Compressed file size is %.2f%% of the original\n", (double) result.bitLength / text.length() * 8);
        assertThat(result.bitLength, is(676799));
        assertThat(Huffman.decode(result.encodedText, result.tree, result.bitLength), is(text));
    }
}