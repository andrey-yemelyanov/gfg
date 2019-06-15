package org.gfg.misc.berkleetoberkley;

import org.gfg.Dictionary;
import org.gfg.hash.HashDictionary;
import java.io.IOException;
import java.util.*;

/**
 * Represents a road network consisting of nodes and roads that connect the nodes.
 */
public class RoadNetwork {

    private RoadNetwork(
        Dictionary<Integer, List<Road>> network, 
        Dictionary<String, Node> nodeNameMap,
        Dictionary<Integer, Node> nodeIdMap){
            this.network = network;
            this.nodeNameMap = nodeNameMap;
            this.nodeIdMap = nodeIdMap;
        }

    private Dictionary<Integer, List<Road>> network;
    private Dictionary<String, Node> nodeNameMap;
    private Dictionary<Integer, Node> nodeIdMap;

    /**
     * Returns all nodes that are part of this road network.
     * @return node ids for each node in the road network
     */
    public List<Integer> getNodes(){
        return network.keys();
    }

    /**
     * Returns {@code Node} object associated with the given node id.
     * @param nodeId node id
     * @return {@code Node} object
     */
    public Node getNode(Integer nodeId){
        if(!nodeIdMap.containsKey(nodeId)){
            throw new IllegalArgumentException(
                String.format("Node with id '%d' not found.", nodeId));
        }
        return nodeIdMap.get(nodeId);
    }

    /**
     * Returns a {@code Node} object associated with the given node name.
     * @param nodeName node name in format |STATE|CITY| e.g. OKCHESTER or NYHARRISON PURCHAS
     * @return {@code Node} object associated with the given node name
     * @throws IllegalArgumentException if node name not found in the network
     */
    public Node getNode(String nodeName){
        if(!nodeNameMap.containsKey(nodeName)){
            throw new IllegalArgumentException(
                String.format("Node with name '%s' not found.", nodeName));
        }
        return nodeNameMap.get(nodeName);
    }

    /**
     * Returns nodes that are adjacent to the given node.
     * @param node node whose neighbors are to be computed
     * @return node ids corresponding to adjacent nodes
     */
    public List<Road> adjacentNodes(int nodeId){
        return network.get(nodeId);
    }

    /**
     * Creates a road network from a National Highway Planning Network (NHPN) database.
     * @param nodeFilePath path to the NHPN file containing nodes
     * @param linkFilePath path to the NHPN file containing links
     * @return road network
     * @throws IOException if database files are not found or some other IO error
     */
    public static RoadNetwork fromFile(String nodeFilePath, String linkFilePath) throws IOException {
        List<Node> nodes = NhpnReader.readNodes(nodeFilePath);
        List<Link> links = NhpnReader.readLinks(linkFilePath);
        return new RoadNetwork(
            buildNetwork(nodes, links), 
            buildNodeNameMap(nodes),
            buildNodeIdMap(nodes));
    }

    private static Dictionary<Integer, Node> buildNodeIdMap(List<Node> nodes){
        Dictionary<Integer, Node> nodeIdMap = new HashDictionary<>();
        for(Node node : nodes){
            nodeIdMap.add(node.getNodeId(), node);
        }
        return nodeIdMap;
    }

    private static Dictionary<String, Node> buildNodeNameMap(List<Node> nodes){
        Dictionary<String, Node> nodeNameMap = new HashDictionary<>();
        for(Node node : nodes){
            String nodeKey = node.getState() + node.getDescription();
            nodeNameMap.add(nodeKey, node);
        }
        return nodeNameMap;
    }

    private static Dictionary<Integer, List<Road>> buildNetwork(List<Node> nodes, List<Link> links){
        Dictionary<Integer, Node> nodeMap = buildNodeMap(nodes);
        Dictionary<Integer, List<Road>> network = new HashDictionary<>();
        
        for(Link link : links){
            int node1 = link.getaNode();
            int node2 = link.getbNode();

            if(!network.containsKey(node1)){
                network.add(node1, new ArrayList<>());
            }

            if(!network.containsKey(node2)){
                network.add(node2, new ArrayList<>());
            }

            network.get(node1).add(new Road(
                nodeMap.get(node1), nodeMap.get(node2), link.getDescription()));
            network.get(node2).add(new Road(
                nodeMap.get(node2), nodeMap.get(node1), link.getDescription()));
        }
        
        return network;
    }

    private static Dictionary<Integer, Node> buildNodeMap(List<Node> nodes){
        Dictionary<Integer, Node> map = new HashDictionary<>();
        for(Node node : nodes){
            map.add(node.getNodeId(), node);
        }
        return map;
    }
}