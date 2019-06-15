package org.gfg.misc.berkleetoberkley;

public class NodeIdDistancePair {

    private double distance;
    private int nodeId;

    public double getDistance() {
        return distance;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public NodeIdDistancePair(int nodeId, double distance){
        setNodeId(nodeId);
        setDistance(distance);
    }
}