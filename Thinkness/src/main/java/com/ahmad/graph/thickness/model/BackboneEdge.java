package com.ahmad.graph.thickness.model;

public class BackboneEdge extends Point {
    public boolean connect;

    public BackboneEdge(double a, double b, boolean d) {
        super(a, b);
        this.connect = d;
    }
}