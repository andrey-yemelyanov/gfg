package org.gfg.bst;

import static org.junit.Assert.assertThat;
import org.gfg.Set;
import org.gfg.SortedSet;
import org.gfg.Util;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import java.util.*;

public class BstTest{

    private SortedSet<Integer> buildBst(){
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
        SortedSet<Integer> set = new Bst<>();
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
    public void bstSuccessor(){
        SortedSet<Integer> set = buildBst();
        assertThat(set.successor(-1), is(1));
        assertThat(set.successor(1), is(3));
        assertThat(set.successor(7), is(8));
        assertThat(set.successor(13), is(14));
        assertThat(set.successor(6), is(7));
        assertThat(set.successor(3), is(4));
        assertThat(set.successor(14), is(nullValue()));
        assertThat(set.successor(20), is(nullValue()));
        assertThat(set.successor(8), is(10));
        assertThat(set.successor(10), is(13));
        assertThat(set.successor(4), is(6));
        assertThat(set.successor(5), is(6));
        assertThat(set.successor(9), is(10));
        assertThat(set.successor(11), is(13));
    }

    @Test
    public void bstCeil(){
        SortedSet<Integer> set = buildBst();
        assertThat(set.ceil(1), is(1));
        assertThat(set.ceil(7), is(7));
        assertThat(set.ceil(13), is(13));
        assertThat(set.ceil(6), is(6));
        assertThat(set.ceil(3), is(3));
        assertThat(set.ceil(14), is(14));
        assertThat(set.ceil(20), is(nullValue()));
        assertThat(set.ceil(8), is(8));
        assertThat(set.ceil(10), is(10));
        assertThat(set.ceil(4), is(4));
        assertThat(set.ceil(0), is(1));
        assertThat(set.ceil(-1), is(1));
        assertThat(set.ceil(2), is(3));
        assertThat(set.ceil(5), is(6));
        assertThat(set.ceil(9), is(10));
        assertThat(set.ceil(12), is(13));
        assertThat(set.ceil(11), is(13));
    }

    @Test
    public void bstPredecessor(){
        SortedSet<Integer> set = buildBst();
        assertThat(set.predecessor(1), is(nullValue()));
        assertThat(set.predecessor(0), is(nullValue()));
        assertThat(set.predecessor(2), is(1));
        assertThat(set.predecessor(3), is(1));
        assertThat(set.predecessor(5), is(4));
        assertThat(set.predecessor(6), is(4));
        assertThat(set.predecessor(8), is(7));
        assertThat(set.predecessor(50), is(14));
        assertThat(set.predecessor(13), is(10));
        assertThat(set.predecessor(14), is(13));
        assertThat(set.predecessor(9), is(8));
    }

    @Test
    public void bstFloor(){
        SortedSet<Integer> set = buildBst();
        assertThat(set.floor(1), is(1));
        assertThat(set.floor(0), is(nullValue()));
        assertThat(set.floor(2), is(1));
        assertThat(set.floor(3), is(3));
        assertThat(set.floor(5), is(4));
        assertThat(set.floor(6), is(6));
        assertThat(set.floor(8), is(8));
        assertThat(set.floor(50), is(14));
        assertThat(set.floor(13), is(13));
        assertThat(set.floor(14), is(14));
        assertThat(set.floor(9), is(8));
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

    @Test
    public void bstHeight(){
        Bst<Integer> bst = new Bst<>();
        assertThat(bst.height(), is(0));
        bst.add(1);
        assertThat(bst.height(), is(1));
        bst.add(2);
        assertThat(bst.height(), is(2));
        bst.add(3);
        assertThat(bst.height(), is(3));
        bst.add(4);
        assertThat(bst.height(), is(4));
        bst.add(5);
        assertThat(bst.height(), is(5));

        assertThat(((Bst<Integer>)buildBst()).height(), is(4));
    }
}