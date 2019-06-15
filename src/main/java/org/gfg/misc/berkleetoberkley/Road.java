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
     * Returns length of this road in radians measured using great-circle distance taking Earth curvature into account.
     * @return road length
     */
    public double getLength(){
        /*
            MIT's calculation:
            A = node1.latitude * pi / 10**6 / 180
            B = node1.longitude * pi / 10**6 / 180
            C = node2.latitude * pi / 10**6 / 180
            D = node2.longitude * pi / 10**6 / 180
            return acos(sin(A) * sin(C) + cos(A) * cos(C) * cos(B - D))
        */
        double rad = Math.PI / 1000000 / 180;
        double A = fromNode.getLatitude() * Math.PI / 1000000 / 180;
        double B = fromNode.getLongitude() * Math.PI / 1000000 / 180;
        double C = toNode.getLatitude() * Math.PI / 1000000 / 180;
        double D = toNode.getLongitude() * Math.PI / 1000000 / 180;
        return Math.acos(Math.sin(A) * Math.sin(C) + Math.cos(A) * Math.cos(C) * Math.cos(B - D));
    }
}