package org.gfg.misc.berkleetoberkley;

public class NodeIdDistancePair {

    private double distance;
    private Node node;

    public double getDistance() {
        return distance;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public NodeIdDistancePair(Node node, double distance){
        setNode(node);
        setDistance(distance);
    }
}