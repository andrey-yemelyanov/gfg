package org.gfg.search;

public class SearchAlgorithms{
    public static <T extends Comparable<T>> int binarySearch(T[] arr, T key){
        int from = 0; int to = arr.length - 1;
        while(from <= to){
            int mid = from + (to - from) / 2;
            if(arr[mid].compareTo(key) == 0) return mid;
            if(arr[mid].compareTo(key) < 0) from = mid + 1;
            else to = mid - 1;
        }
        return -1;
    }
}