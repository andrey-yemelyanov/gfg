package org.gfg.misc.dnamatching;

import static org.junit.Assert.assertThat;
import java.io.IOException;
import java.net.URL;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import java.util.*;

public class RollingHashTest {
    @Test
    public void rollingHashForSubsequences() {
        RollingHash rh1 = new RollingHash("CTAGC");
        RollingHash rh2 = new RollingHash("TAGCG");
        RollingHash rh3 = new RollingHash("AGCGT");
        RollingHash rh4 = new RollingHash("GCGTC");
        assertThat(rh1.currentHash(), is(not(rh2.currentHash())));
        assertThat(rh2.currentHash(), is(not(rh3.currentHash())));
        assertThat(rh1.currentHash(), is(not(rh3.currentHash())));
        assertThat(rh1.currentHash(), is(not(rh4.currentHash())));
        assertThat(rh1.slide('G'), is(rh2.currentHash()));
        assertThat(rh2.slide('T'), is(rh3.currentHash()));
        assertThat(rh3.slide('C'), is(rh4.currentHash()));
    }

    @Test
    public void getSubsequenceHashes() throws IOException {
        URL url = this.getClass().getResource("/dnamatching/short.fa");
        DnaSubsequenceHashIterator hashIterator = new DnaSubsequenceHashIterator(url.getFile(), 3);
        List<DnaSubsequence> list = new ArrayList<>();
        while(hashIterator.hasNext()) list.add(hashIterator.next());

        assertThat(list.size(), is(3));

        assertThat(list.get(0).subsequence(), is("ABC"));
        assertThat(list.get(0).hashCode(), is(3714));
        assertThat(list.get(0).offset(), is(0));

        assertThat(list.get(1).subsequence(), is("BCD"));
        assertThat(list.get(1).hashCode(), is(3771));
        assertThat(list.get(1).offset(), is(1));

        assertThat(list.get(2).subsequence(), is("CDE"));
        assertThat(list.get(2).hashCode(), is(3828));
        assertThat(list.get(2).offset(), is(2));
    }

    @Test
    public void getSubsequenceHashesFromLargeFile() throws IOException {
        URL url = this.getClass().getResource("/dnamatching/fmaternal0.fa");
        DnaSubsequenceHashIterator hashIterator = new DnaSubsequenceHashIterator(url.getFile(), 1000);
        int count = 0;
        while(hashIterator.hasNext()){
            hashIterator.next();
            count++;
        }
        assertThat(count, is(7498951));
    }
}