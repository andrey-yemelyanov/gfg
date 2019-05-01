package org.gfg.sort;

import java.util.function.Function;

/**
 * Implements a stable counting sort algorithm.
 */
public class CountingSort{
    
    /**
     * Sorts an array of elements using a stable counting sort algorithm.
     * @param <T> type of elements in the input array
     * @param array input array
     * @param keyFunction function mapping an arbitrary object to an integer key
     * @param k range of keys 0...k-1
     */
    @SuppressWarnings("unchecked")
    public static <T> void countingSort(T[] array, Function<T, Integer> keyFunction, int k){
         int[] pos = new int[k];

         // count occurrences of each element
         for(T item : array){
            pos[keyFunction.apply(item)]++;
         }

         // compute positions
         int sum = 0;
         for(int i = 0; i < pos.length; i++){
            int temp = pos[i];
            pos[i] = sum;
            sum += temp;
         }

         // sort the input array using pos array
         T[] out = (T[])new Object[array.length];
         for(int i = 0; i < array.length; i++){
            out[pos[keyFunction.apply(array[i])]] = array[i];
            pos[keyFunction.apply(array[i])]++;
         }

         // copy out array back into the input array
         for(int i = 0; i < out.length; i++){
             array[i] = out[i];
         }
    }

    /**
     * Sorts an array of elements using a stable radix sort algorithm with 10 as base.
     * @param <T> type of elements in the input array
     * @param array input array
     * @param keyFunction function mapping an arbitrary object to an integer key
     */
    public static <T> void radixSort(T[] array, Function<T, Integer> keyFunction){
        int d = 0;
        for(T item : array){
            d = Math.max(d, (int)Math.log10(keyFunction.apply(item)) + 1);
        }

        for(int i = 0; i < d; i++){
            final int digitPos = i;
            countingSort(array, item -> getDigit(digitPos, keyFunction.apply(item)), 10);
        }
    }

    private static int getDigit(int i, int key){
        return (key / (int)Math.pow(10, i)) % 10;
    }
}