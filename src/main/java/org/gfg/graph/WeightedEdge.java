package org.gfg.graph;

public class WeightedEdge<V>{
    private V vertex;
    private double weight;

    public WeightedEdge(V vertex, double weight){
        this.vertex = vertex;
        this.weight = weight;
    }

    public V getVertex(){
        return vertex;
    }

    public double getWeight(){
        return weight;
    }
}