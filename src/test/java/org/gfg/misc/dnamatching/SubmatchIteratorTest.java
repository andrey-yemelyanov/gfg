package org.gfg.misc.dnamatching;

import static org.junit.Assert.assertThat;
import java.io.IOException;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import java.util.*;

public class SubmatchIteratorTest{
    @Ignore @Test
    public void InitSubmatchIteratorWithSmallerFile() throws IOException {
        String file1 = this.getClass().getResource("/dnamatching/shortmat.fa").getFile();
        String file2 = this.getClass().getResource("/dnamatching/shortpat.fa").getFile();
        SubmatchIterator it = new SubmatchIterator(file1, file2, 3);
        int matchCount = 0;
        while(it.hasNext()){
            it.next();
            matchCount++;
        }
        assertThat(matchCount, is(not(0)));
    }

    @Test
    public void testExactSubmatches() throws IOException {
        String file1 = this.getClass().getResource("/dnamatching/seq1.fa").getFile();
        String file2 = this.getClass().getResource("/dnamatching/seq2.fa").getFile();
        SubmatchIterator it = new SubmatchIterator(file1, file2, 3);
        List<Submatch> matches = new ArrayList<>();
        while(it.hasNext()) matches.add(it.next());

        assertThat(matches, is(Arrays.asList(
            new Submatch(1, 2),
            new Submatch(4, 2),
            new Submatch(7, 2)
        )));
    }
}