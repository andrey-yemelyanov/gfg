package org.gfg.sort;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.gfg.sort.Sorting.*;
import org.junit.Test;
import java.util.stream.*;
import java.util.*;

public class SortingTest{
    @Test
    public void insertionSortOnEmptyArray(){
        Integer[] array = new Integer[]{};
        insertionSort(array);
        assertThat(array, is(new Integer[]{}));
    }

    @Test
    public void insertionSortOnArrayWithOneElement(){
        Integer[] array = new Integer[]{1};
        insertionSort(array);
        assertThat(array, is(new Integer[]{1}));
    }

    @Test
    public void insertionSortOnArrayWithTwoElements(){
        Integer[] array = new Integer[]{1, 2};
        insertionSort(array);
        assertThat(array, is(new Integer[]{1, 2}));

        array = new Integer[]{2, 1};
        insertionSort(array);
        assertThat(array, is(new Integer[]{1, 2}));
    }

    @Test
    public void insertionSortOnAlreadySortedArray(){
        Integer[] array = new Integer[]{1, 2, 3, 4, 5};
        insertionSort(array);
        assertThat(array, is(new Integer[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void insertionSortOnReverseSortedArray(){
        Integer[] array = new Integer[]{5, 4, 3, 2, 1};
        insertionSort(array);
        assertThat(array, is(new Integer[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void insertionSortOnArraysWithDuplicates(){
        Integer[] array = new Integer[]{5, 4, 3, 2, 1, 1, 2, 3, 4, 5};
        insertionSort(array);
        assertThat(array, is(new Integer[]{1, 1, 2, 2, 3, 3, 4, 4, 5, 5}));
    }

    @Test
    public void insertionSortOnRandomArray(){
        // Note that it took 51.111 sec to sort an array of 100,000 elements
        Integer[] array = new Random().ints(100, 0, 100)
                                      .boxed()
                                      .collect(Collectors.toList())
                                      .toArray(new Integer[0]);
        insertionSort(array);
        assertThat(isSorted(array), is(true));
    }

    @Test
    public void mergeSortEmptyArray(){
        Integer[] array = new Integer[]{};
        mergeSort(array);
        assertThat(array, is(new Integer[]{}));
    }

    @Test
    public void mergeSortArrayWithOneElement(){
        Integer[] array = new Integer[]{1};
        mergeSort(array);
        assertThat(array, is(new Integer[]{1}));
    }

    @Test
    public void mergeSortArrayWithTwoElements(){
        Integer[] array = new Integer[]{1, 2};
        mergeSort(array);
        assertThat(array, is(new Integer[]{1, 2}));

        array = new Integer[]{2, 1};
        mergeSort(array);
        assertThat(array, is(new Integer[]{1, 2}));
    }

    @Test
    public void mergeSortArrayWithThreeElements(){
        Integer[] array = new Integer[]{3, 1, 2};
        mergeSort(array);
        assertThat(array, is(new Integer[]{1, 2, 3}));

        array = new Integer[]{2, 1, 2};
        mergeSort(array);
        assertThat(array, is(new Integer[]{1, 2, 2}));

        array = new Integer[]{2, 2, 1};
        mergeSort(array);
        assertThat(array, is(new Integer[]{1, 2, 2}));
    }

    @Test
    public void mergeSortAlreadySortedArray(){
        Integer[] array = new Integer[]{1, 2, 3, 4, 5, 6};
        mergeSort(array);
        assertThat(array, is(new Integer[]{1, 2, 3, 4, 5 ,6}));
    }

    @Test
    public void mergeSortReverseSortedArray(){
        Integer[] array = new Integer[]{6, 5, 4, 3, 2, 1};
        mergeSort(array);
        assertThat(array, is(new Integer[]{1, 2, 3, 4, 5 ,6}));
    }

    @Test
    public void mergeSortArrayWithDuplicates(){
        Integer[] array = new Integer[]{6, 5, 4, 3, 2, 1, 1, 2, 3, 4, 5};
        mergeSort(array);
        assertThat(array, is(new Integer[]{1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6}));
    }

    @Test
    public void mergeSortOnRandomArray(){
        Integer[] array = new Random().ints(5, 0, 100)
                                      .boxed()
                                      .collect(Collectors.toList())
                                      .toArray(new Integer[0]);
        mergeSort(array);
        assertThat(String.format(
            "Array must be sorted but was %s\n", Arrays.toString(array)), 
            isSorted(array), is(true));
    }
}