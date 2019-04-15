package org.gfg.sort;

import java.util.*;
import java.util.stream.Collectors;

import org.gfg.*;

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
                Util.swap(array, j, j + 1);
                j--;
            }
            i++;
        }
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

    @SuppressWarnings("unchecked")
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

    /**
     * Sorts an input array by the frequency of its elements.
     * Elements that have highest frequency appear first.
     * Elements with the same frequency appear in the same order 
     * they appeared in the input array.
     * @param <T> type of elements in the input array
     * @param arr input array
     */
    public static <T extends Comparable<T>> void sortByFrequency(T[] arr){
        class Item{
            public T value;
            public int freq;
            public int index;

            public Item(T value, int freq, int index){
                this.value = value;
                this.index = index;
                this.freq = freq;
            }
        }

        // build frequency table
        Map<T, Item> map = new HashMap<>();
        for(int i = 0; i < arr.length; i++){
            if(!map.containsKey(arr[i])){
                map.put(arr[i], new Item(arr[i], 0, i));
            }
            map.put(arr[i], new Item(arr[i], map.get(arr[i]).freq + 1, map.get(arr[i]).index));
        }

        List<Item> list = map.values()
                             .stream()
                             .sorted((i1, i2) -> {
                                if(Integer.compare(i2.freq, i1.freq) != 0) return Integer.compare(i2.freq, i1.freq);
                                return Integer.compare(i1.index, i2.index);
                             })
                             .collect(Collectors.toList());

        int i = 0;
        for(Item item : list){
            for(int j = 0; j < item.freq; j++){
                arr[i++] = item.value;
            }
        }
    }
}