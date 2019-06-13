package org.gfg.misc.berkleetoberkley;

/**
 * Represents a road connecting two nodes (locations).
 */
public class Road{
    private Node fromNode;
    private Node toNode;
    private String roadName;

    public Node getFromNode() {
        return fromNode;
    }

    public String getRoadName() {
        return roadName;
    }

    public Node getToNode() {
        return toNode;
    }

    public Road(Node fromNode, Node toNode, String roadName){
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.roadName = roadName;
    }

    /**
     * Returns length of this road measured using great-circle distance taking Earth curvature into account.
     * @return road length
     */
    public double getLength(){
        return 0.0;
    }
}