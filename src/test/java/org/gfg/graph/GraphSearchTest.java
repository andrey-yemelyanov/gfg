package org.gfg.graph;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import org.gfg.Dictionary;
import org.gfg.hash.HashDictionary;
import static org.hamcrest.Matchers.*;
import java.util.*;

public class GraphSearchTest{
    @Test
    public void toposortDag(){
        Dictionary<Character, List<Character>> graph = new HashDictionary<>();
        graph.add('A', Arrays.asList('B'));
        graph.add('B', Arrays.asList('D', 'E'));
        graph.add('C', Arrays.asList('B', 'E'));
        graph.add('D', Arrays.asList('F'));
        graph.add('E', Arrays.asList('F'));
        graph.add('F', Arrays.asList());

        List<Character> toposort = GraphSearch.toposort(graph);
        assertThat(toposort, is(Arrays.asList('C', 'A', 'B', 'E', 'D', 'F')));
    }

    @Test(expected = IllegalArgumentException.class)
    public void toposortDirectedGraphWithCycle(){
        Dictionary<Character, List<Character>> graph = new HashDictionary<>();
        graph.add('A', Arrays.asList('B', 'D'));
        graph.add('B', Arrays.asList('E'));
        graph.add('C', Arrays.asList('E', 'F'));
        graph.add('D', Arrays.asList('B'));
        graph.add('E', Arrays.asList('D'));
        graph.add('F', Arrays.asList('F'));

        GraphSearch.toposort(graph);
    }

    @Test
    public void undirectedGraphWithCycle(){
        Dictionary<Character, List<Character>> graph = new HashDictionary<>();
        graph.add('A', Arrays.asList('B', 'C'));
        graph.add('B', Arrays.asList('A', 'C'));
        graph.add('C', Arrays.asList('A', 'B'));
        assertThat(GraphSearch.hasCycle(graph, false), is(true));
    }

    @Test
    public void undirectedGraphWithoutCycle(){
        Dictionary<Character, List<Character>> graph = new HashDictionary<>();
        graph.add('A', Arrays.asList('B'));
        graph.add('B', Arrays.asList('A', 'D', 'C'));
        graph.add('C', Arrays.asList('B', 'E', 'F', 'G'));
        graph.add('D', Arrays.asList('B'));
        graph.add('E', Arrays.asList('C'));
        graph.add('F', Arrays.asList('C'));
        graph.add('G', Arrays.asList('C'));
        assertThat(GraphSearch.hasCycle(graph, false), is(false));
    }

    @Test
    public void directedGraphWithCycle(){
        Dictionary<Character, List<Character>> graph = new HashDictionary<>();
        graph.add('A', Arrays.asList('B', 'D'));
        graph.add('B', Arrays.asList('E'));
        graph.add('C', Arrays.asList('E', 'F'));
        graph.add('D', Arrays.asList('B'));
        graph.add('E', Arrays.asList('D'));
        graph.add('F', Arrays.asList('F'));
        assertThat(GraphSearch.hasCycle(graph, true), is(true));
    }

    @Test
    public void DAGNoCycle(){
        Dictionary<Character, List<Character>> graph = new HashDictionary<>();
        graph.add('A', Arrays.asList('B'));
        graph.add('B', Arrays.asList('D', 'E'));
        graph.add('C', Arrays.asList('B', 'E'));
        graph.add('D', Arrays.asList('F'));
        graph.add('E', Arrays.asList('F'));
        graph.add('F', Arrays.asList());
        assertThat(GraphSearch.hasCycle(graph, true), is(false));
    }

    @Test
    public void dfsConnectedComponents(){
        Dictionary<Character, List<Character>> graph = new HashDictionary<>();
        graph.add('A', Arrays.asList('B', 'C'));
        graph.add('B', Arrays.asList('A', 'D', 'E'));
        graph.add('C', Arrays.asList('A', 'D'));
        graph.add('D', Arrays.asList('C', 'B', 'E'));
        graph.add('E', Arrays.asList('B', 'D'));
        graph.add('F', Arrays.asList('F'));

        List<Object> result = GraphSearch.getConnectedComponents(graph);
        int nConnectedComponents = (int) result.get(0);
        Dictionary<Character, Integer> components = (Dictionary<Character, Integer>) result.get(1);

        assertThat(nConnectedComponents, is(2));
        assertThat(components.get('A'), is(components.get('B')));
        assertThat(components.get('B'), is(components.get('C')));
        assertThat(components.get('C'), is(components.get('D')));
        assertThat(components.get('D'), is(components.get('E')));
        assertThat(components.get('E'), is(not((components.get('F')))));
    }

    @Test
    public void dfsReachability(){
        Dictionary<Character, List<Character>> graph = new HashDictionary<>();
        graph.add('A', Arrays.asList('B', 'D'));
        graph.add('B', Arrays.asList('E'));
        graph.add('C', Arrays.asList('E', 'F'));
        graph.add('D', Arrays.asList('B'));
        graph.add('E', Arrays.asList('D'));
        graph.add('F', Arrays.asList('F'));

        // source A
        Dictionary<Character, Character> dfsParent = GraphSearch.dfsVisit(graph, 'A');
        assertThat(dfsParent.size(), is(4));
        assertThat(dfsParent.get('A'), is(nullValue()));
        assertThat(dfsParent.get('B'), is('A'));
        assertThat(dfsParent.get('E'), is('B'));
        assertThat(dfsParent.get('D'), is('E'));

        // source C
        dfsParent = GraphSearch.dfsVisit(graph, 'C');
        assertThat(dfsParent.size(), is(5));
        assertThat(dfsParent.get('C'), is(nullValue()));
        assertThat(dfsParent.get('E'), is('C'));
        assertThat(dfsParent.get('D'), is('E'));
        assertThat(dfsParent.get('B'), is('D'));
        assertThat(dfsParent.get('F'), is('C'));

        // source F
        dfsParent = GraphSearch.dfsVisit(graph, 'F');
        assertThat(dfsParent.size(), is(1));
        assertThat(dfsParent.get('F'), is(nullValue()));
    }

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