package org.gfg.misc.dnamatching;

import static org.junit.Assert.assertThat;
import java.io.IOException;
import java.net.URL;
import static org.hamcrest.Matchers.*;
import org.junit.Ignore;
import org.junit.Test;
import java.util.*;

public class SubmatchIteratorTest{
    @Test
    public void InitSubmatchIteratorWithLargeFile() throws IOException {
        String file1 = this.getClass().getResource("/dnamatching/fmaternal0.fa").getFile();
        String file2 = this.getClass().getResource("/dnamatching/fpaternal0.fa").getFile();
        SubmatchIterator it = new SubmatchIterator(file1, file2, 5);
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