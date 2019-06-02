package org.gfg.graph;

import org.gfg.Dictionary;
import org.gfg.hash.HashDictionary;
import java.util.*;

/**
 * Implements Bellman-Ford algorithm for single-source shortest path problem.
 */
public class BellmanFord{
    /**
     * Contains output data from running Bellman-Ford algorithm on a graph.
     * @param <V> type of vertices in the graph
     */
    static class Result<V>{
        private Dictionary<V, Double> distance;
        private Dictionary<V, V> predecessor;
        private List<V> negativeWeightCycle;

        public Result(Dictionary<V, Double> distance, Dictionary<V, V> predecessor){
            this.distance = distance;
            this.predecessor = predecessor;
        }

        public Result(Dictionary<V, Double> distance, Dictionary<V, V> predecessor, List<V> negativeWeightCycle){
            this.distance = distance;
            this.predecessor = predecessor;
            this.negativeWeightCycle = negativeWeightCycle;
        }

        /**
         * Returns distance map from the source vertex to each other vertex in the graph.
         * @return distance map
         */
        public Dictionary<V, Double> getDistance(){
            return distance;
        }

        /**
         * Returns predecessor pointer map from each vertex back to the source vertex in the graph.
         * @return predecessor pointer map
         */
        public Dictionary<V, V> getPredecessor(){
            return predecessor;
        }

        /**
         * Returns true of the input graph contains a negative weight cycle.
         * @return true if negative weight cycle found
         */
        public boolean negativeWeightCycleFound(){
            return negativeWeightCycle != null;
        }

        /**
         * Returns negative weight cycle.
         * @return negative weight cycle
         */
        public List<V> negativeWeightCycle(){
            return negativeWeightCycle;
        }
    }

    /**
     * Executes Bellman-Ford shortest path algorithm on the input graph from a given source vertex.
     * @param <V> type of vertices in the graph
     * @param graph input graph
     * @param source source vertex
     * @return distance and parent pointer maps produced after running the algorithm 
     * as well as negative weight cycle (if any)
     */
    public static <V> Result<V> run(Dictionary<V, List<WeightedEdge<V>>> graph, V source){
        final Double INF = Double.MAX_VALUE;
        Dictionary<V, Double> d = new HashDictionary<>();
        Dictionary<V, V> p = new HashDictionary<>();
        
        // initialization
        for(V v : graph.keys()){
            d.add(v, INF);
            p.add(v, null);
        }
        d.add(source, 0.0);

        // relax edges repeatedly
        for(int i = 0; i < graph.size() - 1; i++){ // relax |V| - 1 times
            // relax all edges
            for(V u : graph.keys()){
                List<WeightedEdge<V>> edges = graph.get(u);
                for(WeightedEdge<V> edge : edges){
                    V v = edge.getVertex();
                    double w = edge.getWeight();
                    if(d.get(v) > d.get(u) + w){
                        d.add(v, d.get(u) + w);
                        p.add(v, u);
                    }
                }
            }
        }

        // check for negative weight cycle
        for(V u : graph.keys()){
            List<WeightedEdge<V>> edges = graph.get(u);
            for(WeightedEdge<V> edge : edges){
                V v = edge.getVertex();
                double w = edge.getWeight();
                if(d.get(v) > d.get(u) + w){
                    return new Result<>(d, p, buildNegativeWeightCycle(u, p));
                }
            }
        }

        return new Result<>(d, p);
    }

    private static <V> List<V> buildNegativeWeightCycle(V source, Dictionary<V, V> predecessor){
        List<V> cycle = new ArrayList<>();
        cycle.add(source);
        V v = source;
        do{
            v = predecessor.get(v);
            cycle.add(v);
        }while(v != source);
        Collections.reverse(cycle);
        return cycle;
    }
}