package org.gfg.misc.berkleetoberkley;

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
    public ShortestPathResult findShortestPath(String source, String destination) throws PathFinderException {
        try{
            Node sourceNode = roadNetwork.getNode(Node.toNodeName(source));
            Node destinationNode = roadNetwork.getNode(Node.toNodeName(destination));
            return null;
        }catch(Exception ex){
            throw new PathFinderException(ex.getMessage());
        }
    }
}