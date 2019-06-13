package org.gfg.misc.berkleetoberkley;

public class Link{
    private int aNode;
    private int bNode;
    private String description;

    public int getaNode() {
        return aNode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getbNode() {
        return bNode;
    }

    public void setbNode(int bNode) {
        this.bNode = bNode;
    }

    public void setaNode(int aNode) {
        this.aNode = aNode;
    }

    public Link(int aNode, int bNode, String description){
        setaNode(aNode);
        setbNode(bNode);
        setDescription(description);
    }
}