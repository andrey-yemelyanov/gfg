package org.gfg.misc.berkleetoberkley;

import java.util.*;
import org.gfg.Dictionary;
import org.gfg.hash.HashDictionary;

/**
 * Finds shortest path between a source node and all other nodes using Dijsktra's algorithm.
 */
public class PathFinder{
    private RoadNetwork roadNetwork;

    /**
     * Initializes a new instance of {@code PathFinder}.
     * @param roadNetwork data structure representing a graph of nodes and links between them
     */
    public PathFinder(RoadNetwork roadNetwork){
        this.roadNetwork = roadNetwork;
    }

    /**
     * Computes shortest path between {@code source} node and {@code destination} node using Dijkstra's algorithm.
     * @param source starting node name in format [CITY STATE] e.g. BERKELEY CA or NEW HAVEN CT
     * @param destination destination node name in format [CITY STATE] e.g. BERKELEY CA or NEW HAVEN CT
     * @return shortest path length and all nodes along this path
     * @throws PathFinderException if an error occurs during execution e.g. a node with a given name not found
     */
    public ShortestPathResult findShortestPath(
        String source, String destination) throws PathFinderException {
        try{
            final double INF = Double.MAX_VALUE;
            Node sourceNode = roadNetwork.getNode(Node.toNodeName(source));
            Node destinationNode = roadNetwork.getNode(Node.toNodeName(destination));
            Set<Integer> visited = new HashSet<>();

            // initialize parent and distance maps
            Dictionary<Integer, Double> dist = new HashDictionary<>();
            Dictionary<Integer, Integer> parent = new HashDictionary<>();
            for(int node : roadNetwork.getNodes()){
                dist.add(node, INF);
            }
            dist.add(sourceNode.getNodeId(), 0.0);
            parent.add(sourceNode.getNodeId(), null);

            // initialize min priority queue of nodes to visit in the order of increasing distance
            PriorityQueue<NodeIdDistancePair> pq = new PriorityQueue<>(
                (n1, n2) -> Double.compare(n1.getDistance(), n2.getDistance()));
            for(int node : roadNetwork.getNodes()){
                pq.add(new NodeIdDistancePair(roadNetwork.getNode(node), dist.get(node)));
            }

            while(!pq.isEmpty()){
                NodeIdDistancePair node = pq.remove();
                int u = node.getNode().getNodeId();
                // terminate if we have reached the destination
                if(u == destinationNode.getNodeId()){
                    break;
                }
                
                visited.add(u);

                // relax all unvisited neighbors of 'u'
                for(Road edge : roadNetwork.adjacentNodes(u)){
                    int v = edge.getToNode().getNodeId();
                    // relax edge if unvisited
                    if(!visited.contains(v)){
                        if(dist.get(v) > node.getDistance() + edge.getLength()){
                            dist.add(v, node.getDistance() + edge.getLength());
                            parent.add(v, u);
                            pq.add(new NodeIdDistancePair(edge.getToNode(), dist.get(v)));
                        }
                    }
                }
            }

            return new ShortestPathResult(
                dist.get(destinationNode.getNodeId()),
                getShortestPath(sourceNode.getNodeId(), destinationNode.getNodeId(), parent));

        }catch(Exception ex){
            throw new PathFinderException(ex.getMessage());
        }
    }

    private List<Node> getShortestPath(
        Integer source, 
        Integer destination,
        Dictionary<Integer, Integer> parent){
        List<Node> path = new ArrayList<>();
        Integer nodeId = destination;
        while(nodeId != null){
            path.add(roadNetwork.getNode(nodeId));
            nodeId = parent.get(nodeId);
        }
        Collections.reverse(path);
        return path;
    }
}