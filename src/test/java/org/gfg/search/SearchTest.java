package org.gfg.search;

import static org.hamcrest.Matchers.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;
import static org.hamcrest.MatcherAssert.*;
import static org.gfg.search.SearchAlgorithms.*;
import org.junit.Test;

public class SearchTest {
    @Test
    public void binarySearchLargerArray() {
        Integer[] arr = new Integer[] { 1, 2, 4, 6, 7, 8, 8, 9, 10 };
        assertThat(binarySearch(arr, 3), is(-1));
        assertThat(binarySearch(arr, 1), is(0));
        assertThat(binarySearch(arr, 0), is(-1));
        assertThat(binarySearch(arr, 10), is(8));
        assertThat(binarySearch(arr, 7), is(4));
    }

    @Test
    public void binarySearchEmptyArray() {
        Integer[] arr = new Integer[] {};
        assertThat(binarySearch(arr, 3), is(-1));
    }

    @Test
    public void binarySearchArrayWithOneElement() {
        Integer[] arr = new Integer[] { 1 };
        assertThat(binarySearch(arr, 3), is(-1));
        assertThat(binarySearch(arr, 1), is(0));
        assertThat(binarySearch(arr, -1), is(-1));
    }

    @Test
    public void findPeakInArrayWithOneElement() {
        assertThat(findPeak(new int[] { 1 }), is(0));
    }

    @Test
    public void findPeakInStrictlyIncreasingArray() {
        assertThat(findPeak(new int[] { 1, 2, 3, 4, 5 }), is(4));
    }

    @Test
    public void findPeakInStrictlyDecreasingArray() {
        assertThat(findPeak(new int[] { 5, 4 }), is(0));
        assertThat(findPeak(new int[] { 5, 4, 3, 2, 1 }), is(0));
    }

    @Test
    public void findPeakInArrayWithSeveralLocalPeaks() {
        assertThat(findPeak(new int[] { 1, 2, 1, 4, 6, 9, 2 }), is(5));
    }

    @Test
    public void findPeakInArrayWithAllElementsEqual() {
        assertThat(findPeak(new int[] { 1, 1, 1, 1 }), is(1));
    }

    @Test
    public void ceilEmptyArray() {
        Integer[] array = new Integer[] {};
        assertThat(ceil(array, -2), is(-1));
    }

    @Test
    public void ceilArrayWithOneElement() {
        Integer[] array = new Integer[] { 5 };
        assertThat(ceil(array, -2), is(0));
        assertThat(ceil(array, 4), is(0));
        assertThat(ceil(array, 5), is(0));
        assertThat(ceil(array, 6), is(-1));
    }

    @Test
    public void ceilXIsLessThanAllElements() {
        Integer[] array = new Integer[] { 2, 5, 7, 8, 8, 9, 11, 19 };
        assertThat(ceil(array, -2), is(0));
    }

    @Test
    public void ceilXIsGreaterThanAllElements() {
        Integer[] array = new Integer[] { 2, 5, 7, 8, 8, 10, 11, 19 };
        assertThat(ceil(array, 20), is(-1));
    }

    @Test
    public void ceilXExistsInArray() {
        Integer[] array = new Integer[] { 2, 5, 7, 8, 8, 10, 11, 19 };
        assertThat(ceil(array, 10), is(5));
        assertThat(ceil(array, 19), is(7));
        assertThat(ceil(array, 2), is(0));
    }

    @Test
    public void ceilXDoesNotExistInArray() {
        Integer[] array = new Integer[] { 2, 5, 7, 8, 8, 10, 11, 19 };
        assertThat(ceil(array, 3), is(1));
        assertThat(ceil(array, 9), is(5));
        assertThat(ceil(array, 15), is(7));
        assertThat(ceil(array, 25), is(-1));
        assertThat(ceil(array, 1), is(0));
    }

    @Test
    public void ceilAllArrayElementsAreSame() {
        Integer[] array = new Integer[] { 2, 2, 2, 2, 2, 2 };
        assertThat(ceil(array, 3), is(-1));
        assertThat(ceil(array, 2), is(5));
        assertThat(ceil(array, 0), is(0));
    }

    @Test
    public void ceilArraysContainsDuplicates() {
        Integer[] array = new Integer[] { 1, 1, 1, 2, 2, 2, 2, 5, 5, 6 };
        assertThat(ceil(array, 2), is(6));
        assertThat(ceil(array, 3), is(7));
        assertThat(ceil(array, 6), is(9));
        assertThat(ceil(array, 1), is(2));
    }

    @Test
    public void floorTest() {
        Integer[] array = new Integer[] { 1, 2, 8, 10, 10, 12, 19 };
        assertThat(floor(array, 3), is(1));
        assertThat(floor(array, 0), is(-1));
        assertThat(floor(array, 7), is(1));
        assertThat(floor(array, 8), is(2));
        assertThat(floor(array, 30), is(6));
        assertThat(floor(array, 15), is(5));
    }

    @Test
    public void floorAllArrayElementsAreSame() {
        Integer[] array = new Integer[] { 2, 2, 2, 2, 2, 2 };
        assertThat(floor(array, 3), is(5));
        assertThat(floor(array, 2), is(0));
        assertThat(floor(array, 5), is(5));
    }

    @Test
    public void floorArraysContainsDuplicates() {
        Integer[] array = new Integer[] { 1, 1, 1, 2, 2, 2, 2, 5, 5, 6 };
        assertThat(floor(array, 1), is(0));
        assertThat(floor(array, 2), is(3));
        assertThat(floor(array, 3), is(6));
        assertThat(floor(array, 6), is(9));
        assertThat(floor(array, 10), is(9));
        assertThat(floor(array, 0), is(-1));
    }

    @Test
    public void medianArraysWithOneElement() {
        int[] arr1 = new int[] { 1 };
        int[] arr2 = new int[] { 2 };
        assertThat(median(arr1, arr2), is(1));
    }

    @Test
    public void medianArraysWithTwoElements() {
        int[] arr1 = new int[] { 1, 2 };
        int[] arr2 = new int[] { 3, 4 };
        assertThat(median(arr1, arr2), is(2));

        arr1 = new int[] { 1, 4 };
        arr2 = new int[] { 2, 3 };
        assertThat(median(arr1, arr2), is(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void medianEmptyArraysThrowsException() {
        int[] arr1 = new int[] {};
        int[] arr2 = new int[] {};
        median(arr1, arr2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void medianArraysWithDiffLengthsThrowsException() {
        int[] arr1 = new int[] { 1, 2, 3 };
        int[] arr2 = new int[] { 1, 2 };
        median(arr1, arr2);
    }

    @Test
    public void medianTwoArrays() {
        int[] arr1 = new int[] { 1, 12, 15, 26, 38 };
        int[] arr2 = new int[] { 2, 13, 17, 30, 45 };
        assertThat(median(arr1, arr2), is(16));

        arr1 = new int[] { 1, 3, 7, 9, 10 };
        arr2 = new int[] { 5, 11, 12, 15, 16 };
        assertThat(median(arr1, arr2), is(9));

        arr1 = new int[] { 1, 3, 7, 9, 12 };
        arr2 = new int[] { 5, 10, 11, 12, 15 };
        assertThat(median(arr1, arr2), is(9));

        arr1 = new int[] { 1, 2, 3, 4 };
        arr2 = new int[] { 5, 6, 7, 8 };
        assertThat(median(arr1, arr2), is(4));
    }

    @Test
    public void findKSmallestArraySizeNotGreaterThanK() {
        assertThat(findKSmallest(new Integer[] { 50, 1, 20 }, 4), is(Arrays.asList(1, 20, 50)));
    }

    @Test
    public void findKSmallestArraySizeGreaterThanK() {
        assertThat(findKSmallest(new Integer[] { 50, 1, 20, -2, 0 }, 3), is(Arrays.asList(-2, 0, 1)));
        assertThat(findKSmallest(new Integer[] { 5, 4, 3, 2, 1 }, 3), is(Arrays.asList(1, 2, 3)));
    }

    @Test
    public void findOccurrencesElementNotPresentInArray() {
        Integer[] arr = new Integer[] { 1, 2, 2, 4, 5, 6 };
        assertThat(countOccurrences(arr, 7), is(0));
        assertThat(countOccurrences(arr, -5), is(0));
        assertThat(countOccurrences(arr, 3), is(0));
    }

    @Test
    public void findOccurrencesElementIsUnique() {
        Integer[] arr = new Integer[] { 1, 2, 3, 4, 5, 6 };
        assertThat(countOccurrences(arr, 3), is(1));
    }

    @Test
    public void findOccurrencesAllElementsAreSame() {
        Integer[] arr = new Integer[] { 1, 1, 1, 1, 1, 1 };
        assertThat(countOccurrences(arr, 1), is(6));
    }

    @Test
    public void findOccurrencesElementOccursSeveralTimes() {
        Integer[] arr = new Integer[] { 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 4, 5, 6 };
        assertThat(countOccurrences(arr, 2), is(5));
        assertThat(countOccurrences(arr, 5), is(1));
        assertThat(countOccurrences(arr, 3), is(2));
    }

    @Test
    public void kMostFrequentKLessThanOrEqualToDistinctSize() {
        Integer[] arr = new Integer[] { 5, 3, 1, 5, 3, 3 };
        assertThat(kMostFrequent(arr, 1), is(Arrays.asList(3)));
        assertThat(kMostFrequent(arr, 2), is(Arrays.asList(3, 5 )));
        assertThat(kMostFrequent(arr, 3), is(Arrays.asList(3, 5, 1 )));
    }

    @Test
    public void kMostFrequentKGreaterThanDistinctSize() {
        Integer[] arr = new Integer[] { 5, 3, 1, 5, 3, 3 };
        assertThat(kMostFrequent(arr, 6), is(Arrays.asList(3, 5, 1 )));
    }

    @Test
    public void kMostFrequentWordsInText() throws FileNotFoundException {
        URL url = this.getClass().getResource("/alice_in_wonderland.txt");
        Scanner s = new Scanner(new File(url.getFile())); 
        List<String> words = new ArrayList<>();
        try{
            while(s.hasNextLine()){
                String line = s.nextLine();
                for(String word : line.split("\\W+")){
                    if(!word.isEmpty()) words.add(word.toLowerCase());
                }
            }
        }finally{
            if(s != null) s.close();
        }

        System.out.printf("*******************************\nTotal # of words: %d\n", words.size());

        final int k = 10;
        List<String> mostFrequentWords = kMostFrequent(words.toArray(new String[0]), k);

        System.out.printf("%d most frequent words: %s\n*******************************\n", k, mostFrequentWords);
    }

    @Test
    public void findCommonElementsNoCommonElements(){
        int[] arr1 = new int[]{1,4,6,7};
        int[] arr2 = new int[]{2,4,6,10};
        int[] arr3 = new int[]{1,2,7,10};
        assertThat(findCommonElements(arr1, arr2, arr3), is(new ArrayList<>()));
    }

    @Test
    public void findCommonElementsDuplicatesInArrays(){
        int[] arr1 = new int[] {1, 5, 5};
        int[] arr2 = new int[] {3, 4, 5, 5, 10};
        int[] arr3 = new int[] {5, 5, 10, 20};
        assertThat(findCommonElements(arr1, arr2, arr3), is(Arrays.asList(5, 5)));
    }

    @Test
    public void findCommonElementsNoDuplicates(){
        int[] arr1 = new int[] {1, 5, 10, 20, 40, 80};
        int[] arr2 = new int[] {6, 7, 20, 80, 100};
        int[] arr3 = new int[] {3, 4, 15, 20, 30, 70, 80, 120};
        assertThat(findCommonElements(arr1, arr2, arr3), is(Arrays.asList(20, 80)));
    }

    @Test
    public void kSmallestInSortedGrid(){
        int[][] grid = new int[][]{
            {10, 20, 30, 40},
            {15, 25, 35, 45},
            {24, 29, 37, 48},
            {32, 33, 39, 50}
        };
        assertThat(kSmallest(grid, 1), is(10));
        assertThat(kSmallest(grid, 2), is(15));
        assertThat(kSmallest(grid, 3), is(20));
        assertThat(kSmallest(grid, 4), is(24));
        assertThat(kSmallest(grid, 5), is(25));
        assertThat(kSmallest(grid, 6), is(29));
        assertThat(kSmallest(grid, 7), is(30));
        assertThat(kSmallest(grid, 16), is(50));
    }
}