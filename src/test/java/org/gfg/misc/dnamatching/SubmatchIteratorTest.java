package org.gfg.misc.dnamatching;

import static org.junit.Assert.assertThat;
import java.io.IOException;
import java.net.URL;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import java.util.*;

public class SubmatchIteratorTest{
    @Test
    public void InitSubmatchIteratorWithLargeFile() throws IOException {
        String file1 = this.getClass().getResource("/dnamatching/shortmat.fa").getFile();
        String file2 = this.getClass().getResource("/dnamatching/shortpat.fa").getFile();
        SubmatchIterator it = new SubmatchIterator(file1, file2, 15);
    }
}