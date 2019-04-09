package org.gfg.bst;

import static org.junit.Assert.assertThat;
import org.gfg.Set;
import org.gfg.SortedSet;
import org.gfg.Util;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import java.util.*;

public class BstTest{

    private Set<Integer> buildBst(){
        /*
            Build BST

                        8
                      /   \
                    3      10
                  /   \      \
                1      6      14
                     /   \    /
                    4     7  13
        */
        Set<Integer> set = new Bst<>();
        set.add(8);
        set.add(3);
        set.add(10);
        set.add(1);
        set.add(6);
        set.add(4);
        set.add(7);
        set.add(14);
        set.add(13);
        return set;
    }

    @Test
    public void bstAddAndContains(){
        Set<Integer> set = new Bst<>();
        assertThat(set.contains(5), is(false));
        set.add(5);
        assertThat(set.contains(5), is(true));
        assertThat(set.contains(4), is(false));
    }

    @Test
    public void bstContains(){
        Set<Integer> set = buildBst();
        assertThat(set.size(), is(9));
        assertThat(set.contains(8), is(true));
        assertThat(set.contains(3), is(true));
        assertThat(set.contains(10), is(true));
        assertThat(set.contains(1), is(true));
        assertThat(set.contains(6), is(true));
        assertThat(set.contains(14), is(true));
        assertThat(set.contains(4), is(true));
        assertThat(set.contains(7), is(true));
        assertThat(set.contains(13), is(true));

        assertThat(set.contains(9), is(false));
        assertThat(set.contains(5), is(false));
        assertThat(set.contains(15), is(false));
        assertThat(set.contains(0), is(false));
        assertThat(set.contains(-10), is(false));
    }

    @Test
    public void bstSize(){
        Set<Integer> set = new Bst<>();
        assertThat(set.size(), is(0));
        assertThat(set.isEmpty(), is(true));
        set.add(1);
        set.add(1);
        set.add(2);
        set.add(2);
        assertThat(set.size(), is(2));
        assertThat(set.isEmpty(), is(false));
        set.add(3);
        set.add(3);
        set.add(3);
        set.add(3);
        assertThat(set.size(), is(3));
        assertThat(set.isEmpty(), is(false));
    }

    @Test
    public void bstRemoveAndContains(){
        Set<Integer> set = buildBst();
        
        set.remove(1);
        assertThat(set.size(), is(8));
        assertThat(set.toList(), is(Arrays.asList(3, 4, 6, 7, 8, 10, 13, 14)));
        assertThat(set.contains(1), is(false));
        
        set.remove(0); // no effect
        assertThat(set.size(), is(8));
        assertThat(set.contains(0), is(false));
        
        set.remove(6);
        assertThat(set.size(), is(7));
        assertThat(set.toList(), is(Arrays.asList(3, 4, 7, 8, 10, 13, 14)));
        assertThat(set.contains(6), is(false));

        set.remove(8);
        assertThat(set.size(), is(6));
        assertThat(set.toList(), is(Arrays.asList(3, 4, 7, 10, 13, 14)));
        assertThat(set.contains(8), is(false));

        set.remove(14);
        assertThat(set.size(), is(5));
        assertThat(set.toList(), is(Arrays.asList(3, 4, 7, 10, 13)));
        assertThat(set.contains(14), is(false));

        set.remove(10);
        assertThat(set.size(), is(4));
        assertThat(set.toList(), is(Arrays.asList(3, 4, 7, 13)));
        assertThat(set.contains(10), is(false));
    }

    @Test
    public void bstIterator(){
        assertThat(buildBst().toList(), is(Arrays.asList(1, 3, 4, 6, 7, 8, 10, 13, 14)));
    }

    @Test
    public void bstMax(){
        Bst<Integer> bst = new Bst<>();
        bst.add(1);
        assertThat(bst.max(), is(1));

        bst = (Bst<Integer>) buildBst();
        assertThat(bst.max(), is(14));
    }

    @Test
    public void bstMin(){
        Bst<Integer> bst = new Bst<>();
        bst.add(1);
        assertThat(bst.min(), is(1));

        bst = (Bst<Integer>) buildBst();
        assertThat(bst.min(), is(1));
    }

    @Test
    public void sortUsingBst(){
        SortedSet<Integer> set = new Bst<>();
        new Random().ints(10000, 0, 1000)
                    .boxed()
                    .forEach(set::add);
        assertThat(Util.isSorted(set.toList().toArray(new Integer[0]), 
            (i1, i2) -> Integer.compare(i1, i2)), is(true));
    }
}