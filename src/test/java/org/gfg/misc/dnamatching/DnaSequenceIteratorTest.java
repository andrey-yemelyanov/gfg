package org.gfg.misc.dnamatching;

import static org.junit.Assert.assertThat;
import java.io.IOException;
import java.net.URL;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

public class DnaSequenceIteratorTest {
    @Test
    public void readTrivialFileCharByChar() throws IOException {
        URL url = this.getClass().getResource("/dnamatching/trivial.fa");
        DnaSequenceIterator sequence = new DnaSequenceIterator(url.getFile());
        StringBuilder sb = new StringBuilder();
        while(sequence.hasNext()) sb.append(sequence.next());
        assertThat(sb.toString(), is("NNNNNNNNNNNNNNNNNAATTCTGAGAAACTTCTTTGTGAGGGTTGGATTCATTTCACACATTTGAACATTTCTTTGATTGAAGATTTGGAAACAGTCTT"));
    }

    @Test
    public void readShortFileCharByChar() throws IOException {
        URL url = this.getClass().getResource("/dnamatching/shortmat.fa");
        DnaSequenceIterator sequence = new DnaSequenceIterator(url.getFile());
        int length = 0;
        while(sequence.hasNext()){
            length++;
            sequence.next();
        }
        assertThat(length, is(2499950));
    }

    @Test
    public void readLargeFileCharByChar() throws IOException {
        URL url = this.getClass().getResource("/dnamatching/fdog0.fa");
        DnaSequenceIterator sequence = new DnaSequenceIterator(url.getFile());
        int length = 0;
        while(sequence.hasNext()){
            length++;
            sequence.next();
        }
        assertThat(length, is(7499950));
    }
}