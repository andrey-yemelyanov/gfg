package org.gfg.binarytree;

public class BinaryTreeUtil {

    public static Node toDll(Node root){
        if(root == null) return null;
        
        toDll(root.left);
        toDll(root.right);
        
        Node leftListHead = root.left;
        if(leftListHead != null){
            while(leftListHead.left != null){
                leftListHead = leftListHead.left;
            }
            
            Node leftListTail = leftListHead;
            while(leftListTail.right != null){
                leftListTail = leftListTail.right;
            }

            leftListTail.right = root;
            root.left = leftListTail;
        }

        Node rightListHead = root.right;
        if(rightListHead != null){
            while(rightListHead.left != null){
                rightListHead = rightListHead.left;
            }

            root.right = rightListHead;
            rightListHead.left = root;
        }

        if(leftListHead != null) return leftListHead;
        return root;
    }
}