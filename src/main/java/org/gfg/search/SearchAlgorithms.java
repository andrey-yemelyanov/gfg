package org.gfg.search;

import static java.lang.Math.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Contains implementation of a number of searching algorithms on collections and arrays.
 * @author Andrey Yemelyanov
 *
 */
public class SearchAlgorithms{
	
	/**
	 * <p>Searches a sorted array <tt>arr</tt> of {@link Comparable} instances for a specified <tt>key</tt>.</p>
	 * <p>The behavior is not specified if the supplied array is not sorted. If there are 
* several instances of the key present in the array, an index of any of the instances is returned.</p>
	 * 
	 * @param <T> type of the elements in the array and the key, must be of type {@link Comparable}
	 * @param arr Sorted array to be searched
	 * @param key Key to be searched for in the specified array
	 * @return an index at which the key is present, or -1 if the key is not found in the array
	 */
    public static <T extends Comparable<T>> int binarySearch(T[] arr, T key){
        return binarySearch(arr, key, 0, arr.length - 1);
    }

    /**
	 * <p>Searches a sorted subarray array in a range from i (inclusive) to j (inclusive) of {@link Comparable} instances for a specified <tt>key</tt>.</p>
	 * <p>The behavior is not specified if the supplied array is not sorted. If there are 
     * several instances of the key present in the array, an index of any of the instances is returned.</p>
	 * 
	 * @param <T> type of the elements in the array and the key, must be of type {@link Comparable}
	 * @param arr Sorted array to be searched
	 * @param key Key to be searched for in the specified array
     * @param i index where the subarray begins (inclusive)
     * @param j index where the subarray ends (inclusive)
	 * @return an index at which the key is present, or -1 if the key is not found in the array
	 */
    public static <T extends Comparable<T>> int binarySearch(T[] arr, T key, int i, int j){
        int from = i; int to = j;
        while(from <= to){
            int mid = from + (to - from) / 2;
            if(arr[mid].compareTo(key) == 0) return mid;
            if(arr[mid].compareTo(key) < 0) from = mid + 1;
            else to = mid - 1;
        }
        return -1;
    } 

    /**
     * <p>Finds a peak in an array of integers. An array element is a peak if it
     * is not smaller than its immediate neighbors.
     * If an input array is sorted in a strictly increasing order, the last element is always a peak.
     * If an input array is sorted in a strictly decreasing order, the first element is always a peak.
     * If all input array elements are the same, any element is a peak.</p>
     * @param arr Input array
     * @return index of a peak element
     */
    public static int findPeak(int[] arr){
        int from = 0; int to = arr.length - 1;
        while(from <= to){
            int mid = from + (to - from) / 2;
            if((mid == 0 || arr[mid] >= arr[mid - 1]) && (mid == arr.length - 1 || arr[mid] >= arr[mid + 1])) return mid;
            else if(mid > 0 && arr[mid] < arr[mid - 1]) to = mid - 1;
            else from = mid + 1;
        }
        return 0;
    }

    /**
     * Given a sorted array and a value x, returns the smallest element
     * in the array greater than or equal to x.
     * @param <T> type of {@link Comparable} elements
     * @param array input array in sorted order
     * @param x value whose ceiling is to be computed
     * @return index of the ceiling element or -1 if none exists
     */
    public static <T extends Comparable<T>> int ceil(T[] array, T x){
        int from = 0; int to = array.length - 1;
        while(from <= to){
            int mid = from + (to - from) / 2;
            // look for the rightmost occurrence
            if(array[mid].compareTo(x) == 0 && mid < array.length - 1 && array[mid + 1].compareTo(x) == 0){
                from = mid + 1;
            }else{
                if(array[mid].compareTo(x) == 0) return mid;
                if((mid == 0 && array[mid].compareTo(x) > 0) || 
                    (mid > 0 && array[mid - 1].compareTo(x) < 0 && array[mid].compareTo(x) > 0)) return mid;
                if(array[mid].compareTo(x) > 0) to = mid - 1;
                else from = mid + 1;
            }
        }
        return -1;
    }

    /**
     * Given a sorted array and a value x, returns the greatest element
     * in the array less than or equal to x.
     * @param <T> type of {@link Comparable} elements
     * @param array input array in sorted order
     * @param x value whose floor is to be computed
     * @return index of the floor element or -1 if none exists
     */
    public static <T extends Comparable<T>> int floor(T[] array, T x){
        int from = 0; int to = array.length - 1;
        while(from <= to){
            int mid = from + (to - from) / 2;
            // look for the leftmost occurrence
            if(array[mid].compareTo(x) == 0 && mid > 0 && array[mid - 1].compareTo(x) == 0){
                to = mid - 1;
            }else{
                if(array[mid].compareTo(x) == 0) return mid;
                if((mid == array.length - 1 && array[mid].compareTo(x) < 0) || 
                    (mid < array.length - 1 && array[mid + 1].compareTo(x) > 0 && array[mid].compareTo(x) < 0)) return mid;
                if(array[mid].compareTo(x) > 0) to = mid - 1;
                else from = mid + 1;
            }
        }
        return -1;
    }

    /**
     * Returns a median of two sorted arrays of the same size.
     * Since the combined size of the two arrays is 2*n, the median will always be integer floor
     * of the average of the two middle elements in the combined sequence.
     * If one or both of the arrays are not sorted, the result is not specified.
     * @param arr1 first input array in sorted order
     * @param arr2 second input array in sorted order
     * @return median - floor value of the average between the two middle elements 
     */
    public static int median(int[] arr1, int[] arr2){
        if(arr1.length != arr2.length) throw new IllegalArgumentException("Input arrays are of different lengths.");
        if(arr1.length == 0) throw new IllegalArgumentException("Empty arrays are not allowed.");
        if(arr1.length == 1) return (arr1[0] + arr2[0]) / 2;
        return median(arr1, arr2, 0, arr1.length - 1, 0, arr2.length - 1);
    }

    private static int median(int[] arr1, int[] arr2, int i, int j, int k, int l){
        int len1 = j - i + 1;
        int len2 = l - k + 1;
        if(len1 == 2) return (max(arr1[i], arr2[k]) + min(arr1[j], arr2[l])) / 2;
        int mid1 = i + len1 / 2;
        int mid2 = k + len2 / 2;
        int m1 = arr1[mid1];
        int m2 = arr2[mid2];
        if(m1 == m2) return m1;
        if(m1 > m2) return median(arr1, arr2, i, mid1, mid2, l);
        else return median(arr1, arr2, mid1, j, k, mid2);
    }

    /**
     * Returns k smallest elements in the supplied array. The array may be sorted or unsorted.
     * If the size of the array is not greater than k, then all of its elements are returned in sorted order,
     * with the smallest one first.
     * @param <T> type of elements, must implement {@link Comparable}
     * @param arr input array
     * @param k number of smallest elements to return
     * @return k smallest elements in the supplied array, sorted in non-descending order
     */
    public static <T extends Comparable<T>> List<T> findKSmallest(T[] arr, int k){
        PriorityQueue<T> pq = new PriorityQueue<>(10, (T t1, T t2) -> t2.compareTo(t1)); // max-heap
        for(int i = 0; i < arr.length && i < k; i++) pq.add(arr[i]);
        
        for(int i = k; i < arr.length; i++){
            if(arr[i].compareTo(pq.peek()) < 0){
                pq.remove();
                pq.add(arr[i]);
            }
        }

        List<T> list = new ArrayList<T>();
        while(!pq.isEmpty()) list.add(pq.remove());
        Collections.reverse(list);
        return list;
    }

    /**
     * Returns number of occurrences of the specified key in a sorted array.
     * @param <T> type of array elements
     * @param arr input array
     * @param key key
     * @return number of occurrences of the key in the array
     */
    public static <T extends Comparable<T>> int countOccurrences(T[] arr, T key){
        int lowerBound = floor(arr, key);
        if(lowerBound < 0 || arr[lowerBound].compareTo(key) != 0) return 0;
        int upperBound = ceil(arr, key);
        return upperBound - lowerBound + 1;
    }

    /**
     * Returns K most frequently occurring elements in an unordered input array.
     * @param <T> type of {@link Comparable} elements
     * @param array unordered array of elements
     * @param k order statistics - how many most frequent elements are to be computed
     * @return returns K most frequent elements in the input array
     */
    public static <T extends Comparable<T>> List<T> kMostFrequent(T[] array, int k){
        Map<T, Integer> map = new HashMap<>();
        for(T element : array){
            map.put(element, map.getOrDefault(element, 0) + 1);
        }

        class Pair<Type>{
            public Type element;
            public int frequency;
            public Pair(Type element, int frequency){
                this.element = element;
                this.frequency = frequency;
            }
        }

        // min-heap where element frequency is the key
        PriorityQueue<Pair<T>> pq = new PriorityQueue<Pair<T>>(
            10, (Pair<T> p1, Pair<T> p2) -> Integer.compare(p1.frequency, p2.frequency));

        for(T key : map.keySet()){
            Pair<T> pair = new Pair<>(key, map.get(key));
            if(pq.size() < k){
                pq.add(pair);
            }else if(pair.frequency > pq.peek().frequency){
                pq.remove();
                pq.add(pair);
            }
        }

        List<Pair<T>> result = new ArrayList<>();
        while(!pq.isEmpty()) result.add(pq.remove());
        Collections.reverse(result);
        
        return result.stream()
                     .map((Pair<T> p) -> p.element)
                     .collect(Collectors.toList());
    }
}