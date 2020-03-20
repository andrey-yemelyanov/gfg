package org.gfg.graph;

import java.util.*;

public class HamiltonianCycle{

    /**
     * Searches for a Hamiltonian cycle in the suppled undirected graph.
     * Hamiltonian Path in an undirected graph is a path that visits each vertex exactly once. 
     * A Hamiltonian cycle (or Hamiltonian circuit) is a Hamiltonian Path such that there is an edge (in the graph) 
     * from the last vertex to the first vertex of the Hamiltonian Path.
     * @param graph input graph
     * @param hamiltonianCycle this list will contain the Hamiltonian cycle if it exists
     * @return true if the Hamiltonian cycle exists
     */
    public static boolean find(Map<Integer, List<Integer>> graph, List<Integer> hamiltonianCycle){
        int origin = graph.keySet().stream().min((i, j) -> Integer.compare(i, j)).get();
        Set<Integer> visited = new HashSet<>();
        visited.add(origin);
        hamiltonianCycle.add(origin);
        return find(graph, hamiltonianCycle, origin, origin, visited);
    }

    private static boolean find(Map<Integer, List<Integer>> graph, List<Integer> cycle, int origin, int v, Set<Integer> visited){
        if(visited.size() == graph.size() && graph.get(v).contains(origin)) return true;
        for(int adjVertex : graph.get(v)){
            if(!visited.contains(adjVertex)){
                visited.add(adjVertex);
                cycle.add(adjVertex);
                if(find(graph, cycle, origin, adjVertex, visited)) return true;
                cycle.remove(cycle.size() - 1);
                visited.remove(adjVertex);
            }
        }
        return false;
    }
}