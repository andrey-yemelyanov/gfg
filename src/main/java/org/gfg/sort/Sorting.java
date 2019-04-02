package org.gfg.sort;

public class Sorting{

    /**
     * <p>Sorts the input array in place using an insertion sort algorithm in O(n^2) time, where n is the length of the array.
     * Sorting is in non-decreasing order according to natural ordering.</p>
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

    /**
     * <p>Sorts an input array using a merge sort algorithm in O(nlogn) time.
     * Sorting is in non-decreasing order according to natural ordering.</p>
     * @param <T> type of input array elements, must be {@link Comparable}
     * @param array input array
     */
    public static <T extends Comparable<T>> void mergeSort(T[] array){
        mergeSort(array, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> void mergeSort(T[] array, int i, int j){
        if(i >= j) return;
        int mid = i + (j - i) / 2;
        mergeSort(array, i, mid);
        mergeSort(array, mid + 1, j);
        merge(array, i, mid, j);
    }

    private static <T extends Comparable<T>> void merge(T[] arr, int i, int mid, int j){
        Object[] leftArr = new Object[mid - i + 1];
        Object[] rightArr = new Object[j - mid];
        for(int k = 0; k < leftArr.length; k++) leftArr[k] = arr[i + k];
        for(int k = 0; k < rightArr.length; k++) rightArr[k] = arr[mid + k + 1];
        int l = 0; int r = 0; int k = i;
        while(l < leftArr.length && r < rightArr.length){
            if(((T) leftArr[l]).compareTo((T) rightArr[r]) <= 0){
                arr[k++] = (T) leftArr[l++];
            }else{
                arr[k++] = (T) rightArr[r++];
            }
        }
        while(l < leftArr.length) arr[k++] = (T) leftArr[l++];
        while(r < rightArr.length) arr[k++] = (T) rightArr[r++];
    }
}