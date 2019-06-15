package org.gfg.misc.berkleetoberkley;

import java.util.List;

public class ShortestPathResult {
    private double length;
    private List<Node> shortestPath;

    public double getLength() {
        return length;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public ShortestPathResult(double length, List<Node> shortestPath) {
        this.length = length;
        this.shortestPath = shortestPath;
    }

    public String toKml(){
        String kml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                     "<kml xmlns=\"http://earth.google.com/kml/2.1\">\n" +
                     "<Document>\n" +
                     "<Placemark>\n" +
                     "<LineString>\n" +
                     "<extrude>1</extrude>\n" +
                     "<tessellate>1</tessellate>\n" +
                     "<coordinates>\n";
        for(Node node : shortestPath){
            kml += String.format(
                "%f,%f\n", 
                node.getLongitude() / 1000000.0, 
                node.getLatitude() / 1000000.0);
        }
        kml += "</coordinates>\n" +
               "</LineString>\n" +
               "</Placemark>\n" +
               "</Document>\n" +
               "</kml>\n";
        return kml;
    }
}