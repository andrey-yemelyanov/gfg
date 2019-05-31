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
     * @return <p>List<Object>.get(0) - a dictionary of distances from the source vertex to all other discovered vertices in the connected component.
     * If a vertex is not reachable from the source vertex, it will not be present in the dist dictionary.</p>
     * <p>List<Object>.get(1) - The routine also returns parent pointer structure which can be used to re-construct shortest paths.</p>
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

    /**
     * Performs topological ordering on the input graph.
     * The graph is expected to be a Directed Acyclic Graph (DAG).
     * Otherwise, an {@code IllegalArgumentException} is thrown.
     * @param graph input graph
     * @throws IllegalArgumentException if the input graph is not a DAG
     * @return a list of graph vertices in one of many possible topological orderings
     */
    public static <V> List<V> toposort(Dictionary<V, List<V>> graph){
        if(hasCycle(graph, true)){
            throw new IllegalArgumentException("Input graph contains a directed cycle.");
        } 
        List<V> toposort = new ArrayList<>();
        Dictionary<V, V> dfsParent = new HashDictionary<>();
        for(V v : graph.keys()){
            if(!dfsParent.containsKey(v)){
                dfsParent.add(v, null);
                dfsToposort(graph, v, dfsParent, toposort);
            }
        }
        Collections.reverse(toposort);
        return toposort;
    }

    private static <V> void dfsToposort(
        Dictionary<V, List<V>> graph, 
        V u, 
        Dictionary<V, V> dfsParent, 
        List<V> toposort){
        for(V v : graph.get(u)){
            if(!dfsParent.containsKey(v)){
                dfsParent.add(v, u);
                dfsToposort(graph, v, dfsParent, toposort);
            }
        }
        toposort.add(u);
    }

    private static final int WHITE = 0;
    private static final int GRAY = 1;
    private static final int BLACK = 2;

    /**
     * Checks for presence of a cycle in the input graph.
     * @param graph input graph
     * @param isDirected indicates whether the input graph is directed or undirected
     * @return true if the input graph contains a cycle
     */
    public static <V> boolean hasCycle(Dictionary<V, List<V>> graph, boolean isDirected){
        Dictionary<V, Integer> color = new HashDictionary<>();
        for(V v : graph.keys()){
            color.add(v, WHITE);
        }
        for(V v : graph.keys()){
            if(color.get(v) == WHITE){
                color.add(v, GRAY);
                if(hasCycle(graph, v, v, color, isDirected)) return true;
            }
        }
        return false;
    }

    private static <V> boolean hasCycle(
        Dictionary<V, List<V>> graph, 
        V u, 
        V parent, 
        Dictionary<V, Integer> color,
        boolean isDirected){
        for(V v : graph.get(u)){
            if(color.get(v) != BLACK){
                if(color.get(v) == GRAY){
                    if(isDirected || v != parent) return true;
                }else{
                    color.add(v, GRAY);
                    if(hasCycle(graph, v, u, color, isDirected)) return true;
                }
            }
        }
        color.add(u, BLACK);
        return false;
    }

    /**
     * Computes connected components for a graph.
     * @param <V> type of vertices in the graph
     * @param graph input graph
     * @return <p>List<Object>.get(0) - number of connected components in the input graph</p>
     * <p>List<Object>.get(1) - dictionary mapping each vertex in the graph to its connected component id</p>
     */
    public static <V> List<Object> getConnectedComponents(Dictionary<V, List<V>> graph){
        int id = 0;
        Dictionary<V, Integer> component = new HashDictionary<>();
        for(V v : graph.keys()){
            if(!component.containsKey(v)){
                component.add(v, id);
                dfsConnectedComponent(graph, v, component, id++);
            }
        }
        return Arrays.asList(id, component);
    }

    private static <V> void dfsConnectedComponent(
        Dictionary<V, List<V>> graph, 
        V u, 
        Dictionary<V, Integer> component, 
        int componentId){
        for(V v : graph.get(u)){
            if(!component.containsKey(v)){
                component.add(v, componentId);
                dfsConnectedComponent(graph, v, component, componentId);
            }
        }
    }

    /**
     * Performs depth-first search in a graph from a given source vertex.
     * @param <V> type of vertices in the graph
     * @param graph input graph (directed or undirected)
     * @param u initial source vertex from which DFS is started
     * @return parent pointer structure with all reachable vertices from the given source vertex
     */
    public static <V> Dictionary<V, V> dfsVisit(Dictionary<V, List<V>> graph, V u){
        Dictionary<V, V> dfsParent = new HashDictionary<>();
        dfsParent.add(u, null);
        dfsVisit(graph, u, dfsParent);
        return dfsParent;
    }

    private static <V> void dfsVisit(Dictionary<V, List<V>> graph, V u, Dictionary<V, V> dfsParent){
        for(V v : graph.get(u)){
            if(!dfsParent.containsKey(v)){
                dfsParent.add(v, u);
                dfsVisit(graph, v, dfsParent);
            }
        }
    }
}