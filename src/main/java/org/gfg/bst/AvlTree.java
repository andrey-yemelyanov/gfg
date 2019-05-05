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
            nodeHeight((AvlNode) node.left), 
            nodeHeight((AvlNode) node.right)) + 1;
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
        AvlNode x = (AvlNode) y.left;
        AvlNode T2 = (AvlNode) x.right;

        y.left = T2;
        x.right = y;

        updateHeight(y);
        updateHeight(x);

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
        AvlNode y = (AvlNode) x.right;
        AvlNode T2 = (AvlNode) y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    @SuppressWarnings("unchecked")
    private int balanceFactor(AvlNode node){
        if(node == null) return 0;
        return nodeHeight((AvlNode) node.left) - nodeHeight((AvlNode) node.right);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected AvlNode add(T key, BstNode root){
        if(root == null) {
            size++;
            return new AvlNode(key);
        }
        if(root.value.compareTo(key) > 0){
            root.left = add(key, (AvlNode) root.left);
        }else if(root.value.compareTo(key) < 0){
            root.right = add(key, (AvlNode) root.right);
        }else if(root.value.compareTo(key) == 0) {
            // overwrite key value
            root.value = key;
            return (AvlNode) root;
        }

        updateHeight((AvlNode) root);
        int bf = balanceFactor((AvlNode) root);

        if(bf > 1){ // left/left or left/right case
            if(root.left.value.compareTo(key) > 0){ // left/left case
                return rotateRight((AvlNode) root);
            }else{ // left/right case
                root.left = rotateLeft((AvlNode) root.left);
                return rotateRight((AvlNode) root);
            }
        }else if(bf < -1){ // right/right or right/left case
            if(root.right.value.compareTo(key) < 0){ // right/right case
                return rotateLeft((AvlNode) root);
            }else{ // right/left case
                root.right = rotateRight((AvlNode) root.right);
                return rotateLeft((AvlNode) root);
            }
        }

        return (AvlNode) root;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected AvlNode delete(BstNode root, T key){
        if(root == null) return null; // key not found
        if(root.value.compareTo(key) > 0) root.left = delete(root.left, key);
        else if(root.value.compareTo(key) < 0) root.right = delete(root.right, key);
        else{ // key to be deleted found
            nodeDeleted = true;
            if(root.left == null) {
                root = root.right;
            }else if(root.right == null) {
                root = root.left;
            }else{
                // replace node value with the value of inorder successor
                root.value = minNode(root.right).value;
                // delete inorder successor in the right subtree
                root.right = delete(root.right, root.value);
            }
        }

        if(root == null) return null;

        updateHeight((AvlNode) root);
        int bf = balanceFactor((AvlNode) root);
        
        if(bf > 1){ // left/left or left/right case
            if(balanceFactor((AvlNode) root.left) >= 0){ // left/left case
                return rotateRight((AvlNode) root);
            }else{ // left/right case
                root.left = rotateLeft((AvlNode) root.left);
                return rotateRight((AvlNode) root);
            }
        }else if(bf < -1){ // right/right or right/left case
            if(balanceFactor((AvlNode) root.right) <= 0){ // right/right case
                return rotateLeft((AvlNode) root);
            }else{ // right/left case
                root.right = rotateRight((AvlNode) root.right);
                return rotateLeft((AvlNode) root);
            }
        }

        return (AvlNode) root;
    }
}