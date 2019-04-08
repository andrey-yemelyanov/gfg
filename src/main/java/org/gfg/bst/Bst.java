package org.gfg.bst;

import java.util.Iterator;
import org.gfg.Set;
import org.gfg.SortedSet;

/**
 * Implements {@link Set} interface using a binary search tree. Note that this
 * implementation makes no guarantees about the balance of the tree. E.g.
 * inserting elements in sorted order will result in a degenerate binary search
 * tree - a linked list essentially. Use {@link AvlTree} instead if you need
 * balance guarantees.
 * 
 * @param <T> type of elements stored in binary search tree
 */
public class Bst<T extends Comparable<T>> implements SortedSet<T> {

    private class BstNode {
        public T value;
        public BstNode left;
        public BstNode right;

        public BstNode(T value) {
            this.value = value;
        }
    }

    private BstNode root;
    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void add(T item) {
        root = add(root, new BstNode(item));
        size++;
    }

    private BstNode add(BstNode root, BstNode newNode) {
        if (root == null){
            return newNode;
        } 

        if(root.value.compareTo(newNode.value) == 0){
            root.value = newNode.value;
            return root;
        }

        if (root.value.compareTo(newNode.value) > 0) {
            root.left = add(root.left, newNode);
        } else {
            root.right = add(root.right, newNode);
        }
        
        return root;
    }

    @Override
    public boolean contains(T item) {
        return contains(root, item);
    }

    private boolean contains(BstNode root, T item) {
        if (root == null) return false;
        if (root.value.compareTo(item) == 0) return true;
        if (root.value.compareTo(item) > 0) return contains(root.left, item);
        return contains(root.right, item);
    }

    /**
     * Returns the height of this binary search tree - the longest distance from the
     * root to some leaf.
     */
    public int height() {
        return 0;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public void remove(T item) {

    }

    @Override
    public T successor(T key) {
        return null;
    }

    @Override
    public T predecessor(T key) {
        return null;
    }

    @Override
    public T ceil(T key) {
        return null;
    }

    @Override
    public T floor(T key) {
        return null;
    }

    @Override
    public T min() {
        return null;
    }

    @Override
    public T max() {
        return null;
    }

}