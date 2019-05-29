package org.gfg.graph;

import java.util.*;
import org.gfg.Dictionary;
import org.gfg.hash.HashDictionary;

/**
 * Implements graph searching routines using BFS and DFS.
 */
public class GraphSearch{

    /**
     * Runs breadth-first search on a graph (directed or undirected).
     * @param <V> type of graph vertices
     * @param graph input graph
     * @param source source vertex
     * @return a dictionary of distances from the source vertex to all other discovered vertices in the connected component.
     * If a vertex is not reachable from the source vertex, it will not be present in the dist dictionary.
     */
    public static <V> List<Object> bfs(Dictionary<V, List<V>> graph, V source){
        Dictionary<V, Integer> dist = new HashDictionary<>();
        Dictionary<V, V> parent = new HashDictionary<>();
        Queue<V> s = new LinkedList<>();
        s.add(source);
        dist.add(source, 0);
        parent.add(source, null);
        while(!s.isEmpty()){
            V u = s.remove();
            for(V v : graph.get(u)){
                if(!dist.containsKey(v)){
                    dist.add(v, dist.get(u) + 1);
                    parent.add(v, u);
                    s.add(v);
                }
            }
        }
        return Arrays.asList(dist, parent);
    }

    /**
     * Returns a shortest BFS path from a source vertex to {@code vertex}.
     * @param <V> type of vertices in the graph
     * @param bfsParent dictionary with parent pointers for each reachable vertex via BFS
     * @param vertex vertex to which to compute the shortest path from the source vertex
     * @return a list containing all vertices along the shortest path from the source vertex to {@code vertex}
     */
    public static <V> List<V> getShortestPath(Dictionary<V, V> bfsParent, V vertex){
        List<V> path = new ArrayList<>();
        if(!bfsParent.containsKey(vertex)) return path;
        while(vertex != null){
            path.add(vertex);
            vertex = bfsParent.get(vertex);
        }
        Collections.reverse(path);
        return path;
    }
}