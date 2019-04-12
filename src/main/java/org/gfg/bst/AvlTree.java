package org.gfg.bst;

/**
 * Extends a binary search tree by adding tree balance guarantees.
 * More specifically, this implementation guarantees that for each tree node 
 * the heights of its left and right subtrees do not differ by more than 1.
 * @param <T> type of elements stored in this binary search tree
 */
public class AvlTree<T extends Comparable<T>> extends Bst<T> {

    @Override
    public void add(T item) {
        add(new BstNode(item));
        // rebalance
    }

}