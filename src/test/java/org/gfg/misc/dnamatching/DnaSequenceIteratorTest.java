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

    @Test
    public void readSubsequenceEqualToFileLen() throws IOException {
        URL url = this.getClass().getResource("/dnamatching/trivial.fa");
        DnaSubsequenceIterator subsequence = new DnaSubsequenceIterator(url.getFile(), 100);
        StringBuilder sb = new StringBuilder();
        while(subsequence.hasNext()) sb.append(subsequence.next());
        assertThat(sb.toString(), is("NNNNNNNNNNNNNNNNNAATTCTGAGAAACTTCTTTGTGAGGGTTGGATTCATTTCACACATTTGAACATTTCTTTGATTGAAGATTTGGAAACAGTCTT"));
    }

    @Test
    public void readSubsequenceGreaterThanFileLen() throws IOException {
        URL url = this.getClass().getResource("/dnamatching/trivial.fa");
        DnaSubsequenceIterator subsequence = new DnaSubsequenceIterator(url.getFile(), 200);
        StringBuilder sb = new StringBuilder();
        while(subsequence.hasNext()) sb.append(subsequence.next());
        assertThat(sb.toString(), is("NNNNNNNNNNNNNNNNNAATTCTGAGAAACTTCTTTGTGAGGGTTGGATTCATTTCACACATTTGAACATTTCTTTGATTGAAGATTTGGAAACAGTCTT"));
    }

    @Test
    public void readAllSubsequences() throws IOException {
        URL url = this.getClass().getResource("/dnamatching/trivial.fa");
        DnaSubsequenceIterator subsequence = new DnaSubsequenceIterator(url.getFile(), 95);
        StringBuilder sb = new StringBuilder();
        while(subsequence.hasNext()) sb.append(subsequence.next());
        assertThat(sb.toString(), is(
            "NNNNNNNNNNNNNNNNNAATTCTGAGAAACTTCTTTGTGAGGGTTGGATTCATTTCACACATTTGAACATTTCTTTGATTGAAGATTTGGAAACA"+
            "NNNNNNNNNNNNNNNNAATTCTGAGAAACTTCTTTGTGAGGGTTGGATTCATTTCACACATTTGAACATTTCTTTGATTGAAGATTTGGAAACAG"+
            "NNNNNNNNNNNNNNNAATTCTGAGAAACTTCTTTGTGAGGGTTGGATTCATTTCACACATTTGAACATTTCTTTGATTGAAGATTTGGAAACAGT"+
            "NNNNNNNNNNNNNNAATTCTGAGAAACTTCTTTGTGAGGGTTGGATTCATTTCACACATTTGAACATTTCTTTGATTGAAGATTTGGAAACAGTC"+
            "NNNNNNNNNNNNNAATTCTGAGAAACTTCTTTGTGAGGGTTGGATTCATTTCACACATTTGAACATTTCTTTGATTGAAGATTTGGAAACAGTCT"+
            "NNNNNNNNNNNNAATTCTGAGAAACTTCTTTGTGAGGGTTGGATTCATTTCACACATTTGAACATTTCTTTGATTGAAGATTTGGAAACAGTCTT"
        ));
    }

    @Test
    public void readOneSubsequence() throws IOException {
        URL url = this.getClass().getResource("/dnamatching/trivial.fa");
        DnaSubsequenceIterator subsequence = new DnaSubsequenceIterator(url.getFile(), 95);
        subsequence.hasNext();
        assertThat(subsequence.next().toString(), is("NNNNNNNNNNNNNNNNNAATTCTGAGAAACTTCTTTGTGAGGGTTGGATTCATTTCACACATTTGAACATTTCTTTGATTGAAGATTTGGAAACA"));
    }

    @Test
    public void readAllSubsequencesFromALargeFile() throws IOException {
        URL url = this.getClass().getResource("/dnamatching/fdog0.fa");
        DnaSubsequenceIterator subsequence = new DnaSubsequenceIterator(url.getFile(), 1000);
        int count = 0;
        while(subsequence.hasNext()){
            count++;
            subsequence.next();
        }
        assertThat(count, is(7498951));
    }
}