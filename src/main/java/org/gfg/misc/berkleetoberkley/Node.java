package org.gfg.misc.berkleetoberkley;

public class Node{
    private int nodeId;
    private int longitude;
    private int latitude;
    private String state;
    private String description;

    public String getDescription() {
        return description;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public Node(int nodeId, int longitude, int latitude, String state, String description) {
        this.setNodeId(nodeId);
        this.setLongitude(longitude);
        this.setLatitude(latitude);
        this.setState(state);
        this.setDescription(description);
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Node)) return false;
        Node other = (Node) obj;
        return this.nodeId == other.nodeId;
    }
}