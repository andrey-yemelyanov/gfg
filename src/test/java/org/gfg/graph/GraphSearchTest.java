package org.gfg.graph;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import org.gfg.Dictionary;
import org.gfg.hash.HashDictionary;
import static org.hamcrest.Matchers.*;
import java.util.*;

public class GraphSearchTest{
    @Test
    public void testBfs(){
        Dictionary<Character, List<Character>> graph = new HashDictionary<>();
        graph.add('A', Arrays.asList('B', 'C'));
        graph.add('B', Arrays.asList('A', 'D', 'E'));
        graph.add('C', Arrays.asList('A', 'D'));
        graph.add('D', Arrays.asList('C', 'B', 'E'));
        graph.add('E', Arrays.asList('B', 'D'));
        graph.add('F', Arrays.asList());

        List<Object> result = GraphSearch.bfs(graph, 'A');
        Dictionary<Character, Integer> dist = (Dictionary<Character, Integer>)result.get(0);
        Dictionary<Character, Character> parent = (Dictionary<Character, Character>)result.get(1);

        assertThat(dist.get('A'), is(0));
        assertThat(dist.get('B'), is(1));
        assertThat(dist.get('C'), is(1));
        assertThat(dist.get('D'), is(2));
        assertThat(dist.get('E'), is(2));
        assertThat(dist.containsKey('F'), is(false));

        assertThat(GraphSearch.getShortestPath(parent, 'A'), is(Arrays.asList('A')));
        assertThat(GraphSearch.getShortestPath(parent, 'B'), is(Arrays.asList('A', 'B')));
        assertThat(GraphSearch.getShortestPath(parent, 'C'), is(Arrays.asList('A', 'C')));
        assertThat(GraphSearch.getShortestPath(parent, 'D'), is(Arrays.asList('A', 'B', 'D')));
        assertThat(GraphSearch.getShortestPath(parent, 'E'), is(Arrays.asList('A', 'B', 'E')));
        assertThat(GraphSearch.getShortestPath(parent, 'F'), is(Arrays.asList()));
    }
}