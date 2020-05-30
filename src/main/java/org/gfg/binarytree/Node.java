package org.gfg.binarytree;

public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node(int val, Node left, Node right){
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public boolean isLeave(){
        return left == null && right == null;
    }

    @Override
    public String toString(){
        return Integer.toString(val);
    }
}