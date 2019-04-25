package org.gfg.bst;

/**
 * Extends a binary search tree by adding tree balance guarantees.
 * More specifically, this implementation guarantees that for each tree node 
 * the heights of its left and right subtrees do not differ by more than 1.
 * @param <T> type of elements stored in this binary search tree
 */
public class AvlTree<T extends Comparable<T>> extends Bst<T> {

    protected class AvlNode extends BstNode {
        public int height;
        public AvlNode(T value) {
            super(value);
        }
    }

    private int nodeHeight(AvlNode node){
        if(node == null) return -1;
        return node.height;
    }

    @SuppressWarnings("unchecked")
    private void updateHeight(AvlNode node){
        node.height = Math.max(
            nodeHeight((AvlTree<T>.AvlNode) node.left), 
            nodeHeight((AvlTree<T>.AvlNode) node.right)) + 1;
    }

    /*
            y                   x
           / \                 / \
          x   T3  ---->       T1  y
         / \                     / \
        T1  T2                  T2 T3
    */
    @SuppressWarnings("unchecked") 
    private AvlNode rotateRight(AvlNode y){
        AvlNode x = (AvlTree<T>.AvlNode) y.left;
        AvlNode T2 = (AvlTree<T>.AvlNode) x.right;

        y.left = T2;
        x.right = y;

        updateHeight(x);
        updateHeight(y);

        return x;
    }

    /*
            x                   y
           / \                 / \
         T1   y    ------>    x   T3
             / \             / \
            T2 T3           T1  T2
    */
    @SuppressWarnings("unchecked") 
    private AvlNode rotateLeft(AvlNode x){
        AvlNode y = (AvlTree<T>.AvlNode) x.right;
        AvlNode T2 = (AvlTree<T>.AvlNode) y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    @SuppressWarnings("unchecked")
    private int balanceFactor(AvlNode node){
        if(node == null) return 0;
        return nodeHeight((AvlTree<T>.AvlNode) node.left) - nodeHeight((AvlTree<T>.AvlNode) node.right);
    }

    @Override
    public void add(T item) {
        add(new BstNode(item));
        // rebalance
        
    }

    
}