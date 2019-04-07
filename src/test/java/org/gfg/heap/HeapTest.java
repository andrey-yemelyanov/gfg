package org.gfg.heap;

import org.gfg.Util;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import java.util.*;
import java.util.stream.Collectors;
import static org.hamcrest.MatcherAssert.*;

public class HeapTest {
    private final int N = 1000;

    @Test
    public void maxHeap(){
        int[] array = new int[]{5,1,9,6,6,10, 2,20};
        int[] top =   new int[]{5,5,9,9,9,10,10,20};
        Heap<Integer> maxHeap = new Heap<>((Integer i1, Integer i2) -> Integer.compare(i2, i1));
        assertThat(maxHeap.size(), is(0));
        for(int i = 0; i < array.length; i++){
            maxHeap.insert(array[i]);
            assertThat(maxHeap.peek(), is(top[i]));
            assertThat(maxHeap.size(), is(i + 1));
        }
        // remove 20
        assertThat(maxHeap.remove(), is(20));
        assertThat(maxHeap.size(), is(array.length - 1));
        // remove 10
        assertThat(maxHeap.remove(), is(10));
        assertThat(maxHeap.size(), is(array.length - 2));
        // remove 9
        assertThat(maxHeap.remove(), is(9));
        assertThat(maxHeap.size(), is(array.length - 3));
        // remove 6
        assertThat(maxHeap.remove(), is(6));
        assertThat(maxHeap.size(), is(array.length - 4));
        // remove 6
        assertThat(maxHeap.remove(), is(6));
        assertThat(maxHeap.size(), is(array.length - 5));
        // remove 5
        assertThat(maxHeap.remove(), is(5));
        assertThat(maxHeap.size(), is(array.length - 6));

        assertThat(maxHeap.peek(), is(2));
        maxHeap.insert(1);
        assertThat(maxHeap.peek(), is(2));
        maxHeap.insert(3);
        assertThat(maxHeap.peek(), is(3));
    }

    @Test
    public void minHeap(){
        int[] array = new int[]{5,1,9,6,6,-10, 2,-20};
        int[] top =   new int[]{5,1,1,1,1,-10,-10,-20};
        Heap<Integer> minHeap = new Heap<>((Integer i1, Integer i2) -> Integer.compare(i1, i2));
        assertThat(minHeap.size(), is(0));
        for(int i = 0; i < array.length; i++){
            minHeap.insert(array[i]);
            assertThat(minHeap.peek(), is(top[i]));
            assertThat(minHeap.size(), is(i + 1));
        }
        
        // remove -20
        assertThat(minHeap.remove(), is(-20));
        assertThat(minHeap.size(), is(array.length - 1));
        assertThat(minHeap.peek(), is(-10));

        // remove -10
        assertThat(minHeap.remove(), is(-10));
        assertThat(minHeap.size(), is(array.length - 2));
        assertThat(minHeap.peek(), is(1));

        minHeap.insert(2);
        assertThat(minHeap.size(), is(array.length - 1));
        assertThat(minHeap.peek(), is(1));

        minHeap.insert(-1);
        assertThat(minHeap.size(), is(array.length));
        assertThat(minHeap.peek(), is(-1));
    }

    @Test
    public void sortUsingMinHeap(){
        Heap<Integer> minHeap = new Heap<>((Integer i1, Integer i2) -> Integer.compare(i1, i2));
        Integer[] array = new Random().ints(N, 0, 100)
                                      .boxed()
                                      .collect(Collectors.toList())
                                      .toArray(new Integer[0]);
        assertThat(Util.isSorted(array, (i1, i2) -> Integer.compare(i1, i2)), is(false));
        Arrays.stream(array).forEach(i -> minHeap.insert(i));
        assertThat(minHeap.size(), is(N));
        List<Integer> sortedList = new ArrayList<>();
        while(!minHeap.isEmpty()){
            sortedList.add(minHeap.remove());
        }
        assertThat(Util.isSorted(sortedList.toArray(new Integer[0]), (i1, i2) -> Integer.compare(i1, i2)), is(true));
    }

    @Test
    public void sortUsingMaxHeap(){
        Heap<Integer> maxHeap = new Heap<>((Integer i1, Integer i2) -> Integer.compare(i2, i1));
        Integer[] array = new Random().ints(N, 0, 100)
                                      .boxed()
                                      .collect(Collectors.toList())
                                      .toArray(new Integer[0]);
        assertThat(Util.isSorted(array, (i1, i2) -> Integer.compare(i2, i1)), is(false));
        Arrays.stream(array).forEach(i -> maxHeap.insert(i));
        assertThat(maxHeap.size(), is(N));
        List<Integer> sortedList = new ArrayList<>();
        while(!maxHeap.isEmpty()){
            sortedList.add(maxHeap.remove());
        }
        assertThat(Util.isSorted(sortedList.toArray(new Integer[0]), (i1, i2) -> Integer.compare(i2, i1)), is(true));
    }

    @Test
    public void heapsort(){
        Integer[] array = new Random().ints(N, 0, 100)
                                      .boxed()
                                      .collect(Collectors.toList())
                                      .toArray(new Integer[0]);
        assertThat(Util.isSorted(array, (i1, i2) -> Integer.compare(i1, i2)), is(false));
        Heap.sort(array, (i1, i2) -> Integer.compare(i2, i1)); // sort using max heap
        assertThat(Util.isSorted(array, (i1, i2) -> Integer.compare(i1, i2)), is(true));
    }
}