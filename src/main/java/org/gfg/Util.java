package org.gfg;

import java.util.Comparator;
import java.util.List;

/**
 * Provides implementation of numerous utility functions common to all
 * algorithms and data structures.
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
     * Checks for array sortedness according to a custom comparator.
     * @param <T> type of input array elements
     * @param array input array
     * @param comparator comparator that determines element ordering
     * @return true if the input array is sorted according to the supplied comparator.
     */
    public static <T> boolean isSorted(T[] array, Comparator<T> comparator){
        for(int i = 1; i < array.length; i++){
            if(comparator.compare(array[i], array[i - 1]) < 0) return false;
        }
        return true;
    }

    /**
     * Converts a list into an array of the same type.
     * @param <T> types of elements in the list and resulting array
     * @param list input list
     * @return an array consisting of list elements in the same order
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] toArray(List<T> list){
        return list.toArray((T[]) new Object[0]);
    }
}