package org.gfg.bst;

import static org.junit.Assert.assertThat;
import java.util.*;
import java.util.stream.*;
import static org.hamcrest.Matchers.*;
import org.gfg.Util;
import org.junit.Test;

public class AvlTreeTest{
    @Test
    public void avlTreeAdd(){
        Bst<Integer> bst = new AvlTree<>();
        final int nNodes = 1000000;
        int expectedHeight = (int) (Math.log(nNodes) / Math.log(2));

        for(int i = 0; i < nNodes; i++) bst.add(i);
        assertThat(bst.size(), is(nNodes));
        assertThat(bst.height(), is(expectedHeight));
        
        for(int i = 0; i < nNodes; i++) bst.add(i);
        assertThat(bst.size(), is(nNodes));
        assertThat(bst.height(), is(expectedHeight));

        assertThat(bst.isBalanced(), is(true));
    }

    @Test
    public void avlTreeOrderingTest(){
        Bst<Integer> bst = new AvlTree<>();
        final int nNodes = 1000000;
        for(int i = 0; i < nNodes; i++) bst.add(i);
        assertThat(bst.isBalanced(), is(true));

        assertThat(bst.min(), is(0));
        assertThat(bst.max(), is(nNodes - 1));
        
        assertThat(bst.floor(nNodes) , is(nNodes - 1));
        assertThat(bst.floor(100), is(100));
        assertThat(bst.floor(-1), is(nullValue()));
        
        assertThat(bst.predecessor(100), is(99));
        assertThat(bst.predecessor(nNodes), is(nNodes - 1));
        assertThat(bst.predecessor(1), is(0));
        assertThat(bst.predecessor(0), is(nullValue()));

        assertThat(bst.ceil(100), is(100));
        assertThat(bst.ceil(nNodes), is(nullValue()));
        assertThat(bst.ceil(-1), is(0));

        assertThat(bst.successor(100), is(101));
        assertThat(bst.successor(nNodes), is(nullValue()));
        assertThat(bst.successor(1), is(2));
        assertThat(bst.successor(-100), is(0));
    }

    @Test
    public void avlTreeRemoveRandom(){
        Bst<Integer> bst = new AvlTree<>();
        
        final int nNodes = 100;
        int expectedHeight = (int)(Math.log(nNodes) / Math.log(2));
        IntStream.range(0, nNodes).forEach(bst::add);
        assertThat(bst.size(), is(nNodes));
        assertThat(bst.height(), is(expectedHeight));
        assertThat(bst.isBalanced(), is(true));

        Set<Integer> nodesToRemove = new Random().ints(30, 0, nNodes)
                                                 .boxed()
                                                 .collect(Collectors.toSet());
        assertThat(nodesToRemove.stream().allMatch(bst::contains), is(true));
        nodesToRemove.stream().forEach(bst::remove);
        
        assertThat("All nodes after removal must not be present in bst",
            nodesToRemove.stream().allMatch(i -> !bst.contains(i)), is(true));
        assertThat(bst.size(), is(nNodes - nodesToRemove.size()));
        assertThat("Tree is not balanced after removing nodes", bst.isBalanced(), is(true));
        expectedHeight = (int)(Math.log(nNodes - nodesToRemove.size()) / Math.log(2));
        assertThat(bst.height(), is(expectedHeight));
    }

    @Test
    public void avlTreeRemoveLeafNodeSingleRotation(){
        Bst<Integer> bst = new AvlTree<>();
        bst.add(25);
        bst.add(22);
        bst.add(30);
        bst.add(23);
        bst.add(28);
        bst.add(40);
        bst.add(35);
        bst.add(50);
        assertThat(bst.isBalanced(), is(true));
        assertThat(bst.size(), is(8));

        bst.remove(23);
        assertThat(bst.isBalanced(), is(true));
        assertThat(bst.root.value, is(30));
        assertThat(bst.size(), is(7));

        bst.remove(28);
        assertThat(bst.isBalanced(), is(true));
        assertThat(bst.root.value, is(30));
        assertThat(bst.size(), is(6));

        bst.remove(22);
        assertThat(bst.isBalanced(), is(true));
        assertThat(bst.root.value, is(30));
        assertThat(bst.size(), is(5));

        bst.remove(25);
        assertThat(bst.isBalanced(), is(true));
        assertThat(bst.root.value, is(40));
        assertThat(bst.size(), is(4));
    }

    @Test
    public void avlTreeRemoveMultipleRotations(){
        Bst<Integer> bst = new AvlTree<>();
        bst.add(30);
        bst.add(25);
        bst.add(40);
        bst.add(22);
        bst.add(26);
        bst.add(35);
        bst.add(50);
        bst.add(20);
        bst.add(34);
        bst.add(45);
        bst.add(60);
        bst.add(70);
        assertThat(bst.size(), is(12));

        bst.remove(26);
        assertThat(bst.size(), is(11));
        assertThat(bst.isBalanced(), is(true));
        assertThat("New root must be 40", bst.root.value, is(40));
    }

    @Test
    public void avlTreeRemoveNodeWithOneChild(){
        Bst<Integer> bst = new AvlTree<>();
        bst.add(25);
        bst.add(22);
        bst.add(30);
        bst.add(20);
        bst.add(28);
        bst.add(40);
        bst.add(27);
        bst.add(35);
        assertThat(bst.isBalanced(), is(true));

        bst.remove(22);
        assertThat(bst.isBalanced(), is(true));
        assertThat(bst.size(), is(7));
        assertThat(bst.root.value, is(30));

        bst.remove(40);
        assertThat(bst.isBalanced(), is(true));
        assertThat(bst.size(), is(6));
        assertThat(bst.root.value, is(28));
    }

    @Test
    public void avlTreeRemoveNodeWithTwoChildren(){
        Bst<Integer> bst = new AvlTree<>();
        bst.add(45);
        bst.add(28);
        bst.add(68);
        bst.add(20);
        bst.add(30);
        bst.add(50);
        bst.add(80);
        bst.add(15);
        bst.add(25);
        bst.add(35);
        bst.add(55);
        bst.add(18);

        bst.remove(68);
        assertThat(bst.isBalanced(), is(true));
        assertThat(bst.size(), is(11));
        assertThat(bst.root.value, is(28));
    }

    @Test
    public void avlTreeRemoveAllCases(){
        Bst<Integer> bst = new AvlTree<>();
        bst.add(65);
        bst.add(50);
        bst.add(70);
        bst.add(40);
        bst.add(60);
        bst.add(68);
        bst.add(80);
        bst.add(55);
        bst.add(67);
        bst.add(75);

        bst.remove(40);
        assertThat(bst.isBalanced(), is(true));
        assertThat(bst.root.value, is(65));
        assertThat(bst.size(), is(9));

        bst.remove(60);
        assertThat(bst.isBalanced(), is(true));
        assertThat(bst.root.value, is(65));
        assertThat(bst.size(), is(8));

        bst.remove(55);
        assertThat(bst.isBalanced(), is(true));
        assertThat(bst.root.value, is(70));
        assertThat(bst.size(), is(7));

        bst.remove(70);
        assertThat(bst.isBalanced(), is(true));
        assertThat(bst.root.value, is(68));
        assertThat(bst.size(), is(6));
    }

    @Test
    public void avlTreeSort(){
        Bst<Integer> bst = new AvlTree<>();
        final int nNodes = 1000000;
        for(int i = 0; i < nNodes; i++) bst.add(i);
        assertThat(bst.isBalanced(), is(true));
        boolean actual = Util.isSorted(bst.toList(), Comparator.naturalOrder());
        assertThat(actual, is(true));
    }
}