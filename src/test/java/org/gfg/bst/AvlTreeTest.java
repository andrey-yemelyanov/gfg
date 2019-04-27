package org.gfg.bst;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;
import org.gfg.Util;
import org.junit.Test;

public class AvlTreeTest{
    private final int nNodes = 1000;

    @Test
    public void avlTreeAdd(){
        Bst<Integer> bst = new AvlTree<>();
        final int nNodes = 1000;
        int expectedHeight = (int) (Math.log(nNodes) / Math.log(2));

        for(int i = 0; i < nNodes; i++) bst.add(i);
        assertThat(bst.size(), is(nNodes));
        assertThat(bst.height(), is(expectedHeight));
        
        for(int i = 0; i < nNodes; i++) bst.add(i);
        assertThat(bst.size(), is(nNodes));
        assertThat(bst.height(), is(expectedHeight));
    }

    @Test
    public void avlTreeRemove(){
        Bst<Integer> bst = new AvlTree<>();
        final int nNodes = 1000;
        final int nNodesToRemove = 100;
        
        for(int i = 0; i < nNodes; i++) bst.add(i);
        assertThat(bst.size(), is(nNodes));
        
        for(int i = 0; i < nNodesToRemove; i++) bst.remove(i);
        assertThat(bst.size(), is(nNodes - nNodesToRemove));
        assertThat(bst.height(), is((int) (Math.log(nNodes - nNodesToRemove) / Math.log(2))));
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