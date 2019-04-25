package org.gfg.bst;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

public class AvlTreeTest{
    @Test
    public void avlTreeAdd(){
        Bst<Integer> bst = new AvlTree<>();
        final int nNodes = 100;
        for(int i = 0; i < nNodes; i++) bst.add(i);
        assertThat(bst.size(), is(nNodes));
        assertThat(bst.height(), is((int)Math.ceil(
            Math.log(nNodes) / Math.log(2))));
    }

    @Test
    public void avlTreeRemove(){
        
    }
}