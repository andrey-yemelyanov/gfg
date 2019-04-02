package org.gfg.search;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.gfg.search.SearchAlgorithms.*;
import org.junit.Test;

public class SearchTest{
    @Test
    public void binarySearchLargerArray(){
        Integer[] arr = new Integer[]{1,2,4,6,7,8,8,9,10};
        assertThat(binarySearch(arr, 3), is(-1));
        assertThat(binarySearch(arr, 1), is(0));
        assertThat(binarySearch(arr, 0), is(-1));
        assertThat(binarySearch(arr, 10), is(8));
        assertThat(binarySearch(arr, 7), is(4));
    }

    @Test
    public void binarySearchEmptyArray(){
        Integer[] arr = new Integer[]{};
        assertThat(binarySearch(arr, 3), is(-1));
    }

    @Test
    public void binarySearchArrayWithOneElement(){
        Integer[] arr = new Integer[]{1};
        assertThat(binarySearch(arr, 3), is(-1));
        assertThat(binarySearch(arr, 1), is(0));
        assertThat(binarySearch(arr, -1), is(-1));
    }

    @Test
    public void findPeakInArrayWithOneElement(){
        assertThat(findPeak(new int[] {1}), is(0));
    }

    @Test
    public void findPeakInStrictlyIncreasingArray(){
    	assertThat(findPeak(new int[] {1, 2, 3, 4, 5}), is(4));
    }

    @Test
    public void findPeakInStrictlyDecreasingArray(){
    	assertThat(findPeak(new int[] {5, 4}), is(0));
    	assertThat(findPeak(new int[] {5, 4, 3, 2, 1}), is(0));
    }

    @Test
    public void findPeakInArrayWithSeveralLocalPeaks(){	
    	assertThat(findPeak(new int[] {1, 2, 1, 4, 6, 9, 2}), is(5));
    }

    @Test
    public void findPeakInArrayWithAllElementsEqual(){
    	assertThat(findPeak(new int[] {1, 1, 1, 1}), is(1));
    }

    @Test
    public void ceilEmptyArray(){
        Integer[] array = new Integer[]{};
        assertThat(ceil(array, -2), is(-1));
    }

    @Test
    public void ceilArrayWithOneElement(){
        Integer[] array = new Integer[]{5};
        assertThat(ceil(array, -2), is(0));
        assertThat(ceil(array, 4), is(0));
        assertThat(ceil(array, 5), is(0));
        assertThat(ceil(array, 6), is(-1));
    }

    @Test
    public void ceilXIsLessThanAllElements(){
        Integer[] array = new Integer[]{2,5,7,8,8,9,11,19};
        assertThat(ceil(array, -2), is(0));
    }

    @Test
    public void ceilXIsGreaterThanAllElements(){
        Integer[] array = new Integer[]{2,5,7,8,8,10,11,19};
        assertThat(ceil(array, 20), is(-1));
    }

    @Test
    public void ceilXExistsInArray(){
        Integer[] array = new Integer[]{2,5,7,8,8,10,11,19};
        assertThat(ceil(array, 10), is(5));
        assertThat(ceil(array, 19), is(7));
        assertThat(ceil(array, 2), is(0));
    }

    @Test
    public void ceilXDoesNotExistInArray(){
        Integer[] array = new Integer[]{2,5,7,8,8,10,11,19};
        assertThat(ceil(array, 3), is(1));
        assertThat(ceil(array, 9), is(5));
        assertThat(ceil(array, 15), is(7));
        assertThat(ceil(array, 25), is(-1));
        assertThat(ceil(array, 1), is(0));
    }

    @Test
    public void ceilAllArrayElementsAreSame(){
        Integer[] array = new Integer[]{2,2,2,2,2,2};
        assertThat(ceil(array, 3), is(-1));
        assertThat(ceil(array, 2), is(2));
        assertThat(ceil(array, 0), is(0));
    }

    @Test
    public void floorTest(){
        Integer[] array = new Integer[]{1, 2, 8, 10, 10, 12, 19};
        assertThat(floor(array, 3), is(1));
        assertThat(floor(array, 0), is(-1));
        assertThat(floor(array, 7), is(1));
        assertThat(floor(array, 8), is(2));
        assertThat(floor(array, 30), is(6));
        assertThat(floor(array, 15), is(5));
    }
}