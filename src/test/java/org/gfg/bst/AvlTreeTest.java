package org.gfg.bst;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

public class AvlTreeTest{
    @Test
    public void avlTreeHeight(){
        Bst<Integer> bst = new AvlTree<>();
        final int nNodes = 10000;
        for(int i = 0; i < nNodes; i++) bst.add(i);
        assertThat(bst.height(), is((int)Math.ceil(
            Math.log(nNodes) / Math.log(2))));
    }
}