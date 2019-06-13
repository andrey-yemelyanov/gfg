package org.gfg.misc.berkleetoberkley;

public class Node{
    private int nodeId;
    private int longitude;
    private int latitude;
    private int state;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
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

    public Node(int nodeId, int longitude, int latitude, int state, String description) {
        this.setNodeId(nodeId);
        this.setLongitude(longitude);
        this.setLatitude(latitude);
        this.setState(state);
        this.setDescription(description);
    }
}