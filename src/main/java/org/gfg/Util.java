package org.gfg;

/**
 * Provides implementation of numerous utility functions common to all algorithms and data structures.
 */
public class Util{
    
    /**
     * Swaps two elements in the input array.
     * @param <T> type of input array elements
     * @param array input array
     * @param i index of the first element to swap
     * @param j index of the second element to swap
     */
    public static <T> void swap(T[] array, int i, int j){
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Checks for array sortedness.
     * @param <T> type of input array elements, must implement {@link Comparable}
     * @param array input array
     * @return true if the input array is sorted in non-decreasing order.
     */
    public static <T extends Comparable<T>> boolean isSorted(T[] array){
        for(int i = 1; i < array.length; i++){
            if(array[i].compareTo(array[i - 1]) < 0) return false;
        }
        return true;
    }
}