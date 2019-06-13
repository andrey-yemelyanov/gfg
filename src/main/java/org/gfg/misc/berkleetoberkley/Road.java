package org.gfg.misc.berkleetoberkley;

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

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public Node getToNode() {
        return toNode;
    }

    public void setToNode(Node toNode) {
        this.toNode = toNode;
    }

    public void setFromNode(Node fromNode) {
        this.fromNode = fromNode;
    }

    public Road(Node fromNode, Node toNode, String roadName){
        setFromNode(fromNode);
        setToNode(toNode);
        setRoadName(roadName);
    }

    public double getDistance(){
        return 0.0;
    }
}