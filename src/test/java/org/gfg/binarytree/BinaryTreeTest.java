package org.gfg.binarytree;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import java.util.*;

public class BinaryTreeTest {
    @Test
    public void sumOfRightLeaves(){
        Node root = new Node(10, 
            new Node(12, 
                new Node(25, null, null), 
                new Node(30, null, null)), 
            new Node(15, 
                new Node(36, null, null), 
                null));
        assertThat(BinaryTreeUtil.sumOfRightLeaves(root), is(30));
    }

    @Test
    public void coonvertToDll(){
        Node root = new Node(1, null, null);
        Node list = BinaryTreeUtil.toDll(root);
        assertThat(dllToList(list), is(Arrays.asList(1)));
        assertThat(dllToListReversed(list), is(Arrays.asList(1)));

        root = new Node(1, new Node(2, null, null), null);
        list = BinaryTreeUtil.toDll(root);
        assertThat(dllToList(list), is(Arrays.asList(2, 1)));
        assertThat(dllToListReversed(list), is(Arrays.asList(1, 2)));

        root = new Node(1, new Node(0, null, null), new Node(2, null, new Node(3, null, null)));
        list = BinaryTreeUtil.toDll(root);
        assertThat(dllToList(list), is(Arrays.asList(0, 1, 2, 3)));
        assertThat(dllToListReversed(list), is(Arrays.asList(3, 2, 1, 0)));

        root = new Node(1, new Node(2, null, null), new Node(3, null, null));
        list = BinaryTreeUtil.toDll(root);
        assertThat(dllToList(list), is(Arrays.asList(2, 1, 3)));
        assertThat(dllToListReversed(list), is(Arrays.asList(3, 1, 2)));

        root = new Node(10, 
            new Node(12, 
                new Node(25, null, null), 
                new Node(30, null, null)), 
            new Node(15, 
                new Node(36, null, null), 
                null));
        list = BinaryTreeUtil.toDll(root);
        assertThat(dllToList(list), is(Arrays.asList(25, 12, 30, 10, 36, 15)));
        assertThat(dllToListReversed(list), is(Arrays.asList(15, 36, 10, 30, 12, 25)));
    }

    private List<Integer> dllToList(Node head){
        List<Integer> list = new ArrayList<>();
        while(head != null){
            list.add(head.val);
            head = head.right;
        }
        return list;
    }

    private List<Integer> dllToListReversed(Node head){
        List<Integer> list = new ArrayList<>();
        Node tail = head;
        while(tail.right != null){
            tail = tail.right;
        }
        while(tail != null){
            list.add(tail.val);
            tail = tail.left;
        }
        return list;
    }
}