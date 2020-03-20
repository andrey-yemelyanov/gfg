package org.gfg.graph;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import java.util.*;

public class HamiltonianCycleTest{
    @Test
    public void findCycle(){

        // (1)-----(2)
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(1, Arrays.asList(2));
        graph.put(2, Arrays.asList(1));
        List<Integer> cycle = new ArrayList<>();
        assertThat(HamiltonianCycle.find(graph, cycle), is(true));
        assertThat(cycle, is(Arrays.asList(1, 2)));

        /*
            (0)--(1)--(2)
            |   / \   |
            |  /   \  | 
            | /     \ |
            (3)-------(4)
        */
        graph = new HashMap<>();
        graph.put(0, Arrays.asList(1,3));
        graph.put(1, Arrays.asList(0,2,3,4));
        graph.put(2, Arrays.asList(1,4));
        graph.put(3, Arrays.asList(0,1,4));
        graph.put(4, Arrays.asList(1,2,3));
        cycle = new ArrayList<>();
        assertThat(HamiltonianCycle.find(graph, cycle), is(true));
        assertThat(cycle, is(Arrays.asList(0,1,2,4,3)));

        /*
            (0)--(1)--(2)
            |   / \   |
            |  /   \  | 
            | /     \ |
            (3)      (4)
        */
        graph = new HashMap<>();
        graph.put(0, Arrays.asList(1,3));
        graph.put(1, Arrays.asList(0,2,3,4));
        graph.put(2, Arrays.asList(1,4));
        graph.put(3, Arrays.asList(0,1));
        graph.put(4, Arrays.asList(1,2));
        cycle = new ArrayList<>();
        assertThat(HamiltonianCycle.find(graph, cycle), is(false));
    }
}