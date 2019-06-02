package org.gfg.graph;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import org.gfg.Dictionary;
import org.gfg.graph.BellmanFord.Result;
import org.gfg.hash.HashDictionary;
import static org.hamcrest.Matchers.*;
import java.util.*;

public class BellmanFordTest{
    @Test
    public void bellmanFordNoNegativeWeightCycle(){
        Dictionary<Character, List<WeightedEdge<Character>>> graph = new HashDictionary<>();
        graph.add('A', Arrays.asList(
            new WeightedEdge<Character>('B', -1),
            new WeightedEdge<Character>('C', 4)));
        graph.add('B', Arrays.asList(
            new WeightedEdge<Character>('C', 3),
            new WeightedEdge<Character>('D', 2),
            new WeightedEdge<Character>('E', 2)));
        graph.add('C', Arrays.asList());
        graph.add('D', Arrays.asList(
            new WeightedEdge<Character>('C', 5),
            new WeightedEdge<Character>('B', 1)));
        graph.add('E', Arrays.asList(
            new WeightedEdge<Character>('D', -3)));

        Result<Character> result = BellmanFord.run(graph, 'A');
        Dictionary<Character, Double> d = result.getDistance();
        Dictionary<Character, Character> p = result.getPredecessor();

        assertThat(result.negativeWeightCycleFound(), is(false));
        assertThat(d.get('A'), is(0.0));
        assertThat(d.get('B'), is(-1.0));
        assertThat(d.get('C'), is(2.0));
        assertThat(d.get('D'), is(-2.0));
        assertThat(d.get('E'), is(1.0));

        assertThat(p.get('A'), is(nullValue()));
        assertThat(p.get('B'), is('A'));
        assertThat(p.get('C'), is('B'));
        assertThat(p.get('D'), is('E'));
        assertThat(p.get('E'), is('B'));
    }

    @Test
    public void bellmanFordWithNegativeWeightCycle(){
        Dictionary<Character, List<WeightedEdge<Character>>> graph = new HashDictionary<>();
        graph.add('A', Arrays.asList(
            new WeightedEdge<Character>('B', -1),
            new WeightedEdge<Character>('C', 4)));
        graph.add('B', Arrays.asList(
            new WeightedEdge<Character>('C', 3),
            new WeightedEdge<Character>('D', 2),
            new WeightedEdge<Character>('E', 2)));
        graph.add('C', Arrays.asList());
        graph.add('D', Arrays.asList(
            new WeightedEdge<Character>('C', 5),
            new WeightedEdge<Character>('B', 1)));
        graph.add('E', Arrays.asList(
            new WeightedEdge<Character>('D', -4)));

        Result<Character> result = BellmanFord.run(graph, 'A');

        assertThat(result.negativeWeightCycleFound(), is(true));
        assertThat(result.negativeWeightCycle(), is(Arrays.asList('B', 'E', 'D', 'B')));
    }
}