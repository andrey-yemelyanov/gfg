package org.gfg.heap;

import java.util.*;
import org.gfg.Util;

/**
 * Implements binary heap data structure. Whether this is a max or min heap
 * is determined by a custom {@link Comparator} supplied when constructing this heap.
 * @param <T> type of elements stored in the heap
 */
public class Heap<T>{
    private Comparator<T> comparator;
    private int size;
    private T[] heapArray;

    private static final int INITIAL_CAPACITY = 100;

    /**
     * Returns number of elements stored in this heap.
     */
    public int size(){
        return size;
    }

    /**
     * Returns true if this heap is empty.
     */
    public boolean isEmpty(){
        return size() > 0;
    }

    /**
     * Creates a new instance of heap.
     * @param comparator custom comparator for ordering heap elements
     */
    @SuppressWarnings("unchecked")
    public Heap(Comparator<T> comparator){
        this.comparator = Objects.requireNonNull(comparator, "Comparator must not be null");
        heapArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    private void resize(){
        // double the size of heap array
        heapArray = Arrays.copyOf(heapArray, heapArray.length * 2);
    }

    /**
     * Inserts a new element into this heap.
     */
    public void insert(T element){
        if(size == heapArray.length){
            resize();
        }
        heapArray[size] = element;
        bubbleUp(size);
        size++;
    }

    /**
     * Removes and returns the value at the top of the heap.
     */
    public T remove(){
        T top = heapArray[0];
        Util.swap(heapArray, 0, size - 1);
        bubbleDown(heapArray, size - 1, comparator, 0);
        size--;
        return top;
    }

    private static <T> void bubbleDown(T[] heapArray, int size, Comparator<T> comparator, int i){
        int left = leftChild(i);
        int right = rightChild(i);
        int smallest = i;
        if(left < size && comparator.compare(heapArray[left], heapArray[i]) < 0){
            smallest = left;
        }
        if(right < size && comparator.compare(heapArray[right], heapArray[smallest]) < 0){
            smallest = right;
        }
        if(smallest != i){
            Util.swap(heapArray, i, smallest);
            bubbleDown(heapArray, size, comparator, smallest);
        }
    }

    /**
     * Returns but does not remove the value at the top of the heap.
     */
    public T peek(){
        return heapArray[0];
    }

    private void bubbleUp(int i){
        if(i == 0) return;
        int parent = parent(i);
        if(comparator.compare(heapArray[i], heapArray[parent]) < 0){
            Util.swap(heapArray, i, parent);
            bubbleUp(parent);
        }
    }

    private static int parent(int i){
        return (i - 1) / 2;
    }

    private static int leftChild(int i){
        return i * 2 + 1;
    }

    private static int rightChild(int i){
        return i * 2 + 2;
    }

    /**
     * Sorts an input array using heap-sort algorithm.
     * @param <T> type of input elements
     * @param array input array
     * @param comparator comparator that determines element ordering
     */
    public static <T> void sort(T[] array, Comparator<T> comparator){
        // build heap
        for(int i = array.length / 2; i >= 0; i--){
            bubbleDown(array, array.length, comparator, i);
        }

        int size = array.length;
        while(size > 0){
            Util.swap(array, 0, size - 1);
            size--;
            bubbleDown(array, size, comparator, 0);
        }
    }
}