package org.gfg.sort;

public class Sorting{

    /**
     * <p>Sorts the input array using an insertion sort algorithm in O(n^2) time, where n is the length of the array.</p>
     * @param <T> type of array elements, which must implement {@link Comparable}
     * @param array input array
     */
    public static <T extends Comparable<T>> void insertionSort(T[] array){
        int i = 1;
        while(i < array.length){
            int j = i - 1;
            while(j >= 0 && array[j].compareTo(array[j + 1]) > 0){
                swap(array, j, j + 1);
                j--;
            }
            i++;
        }
    }

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