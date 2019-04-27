package org.gfg.bst;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;
import org.gfg.Util;
import org.junit.Test;

public class AvlTreeTest{
    private final int nNodes = 1000;

    @Test
    public void avlTreeHeight(){
        Bst<Integer> bst = new AvlTree<>();
        for(int i = 0; i < nNodes; i++) bst.add(i);
        assertThat(bst.height(), is((int)Math.ceil(
            Math.log(nNodes) / Math.log(2))));
    }

    @Test
    public void avlTreeSort(){
        Bst<Integer> bst = new AvlTree<>();
        for(int i = 0; i < nNodes; i++) bst.add(i);
        assertThat(Util.isSorted(Util.toArray(bst.toList()), 
            (i1, i2) -> Integer.compare(i1, i2)), 
            is(true));
    }
}