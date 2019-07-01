package ru.geekbrains.algo.graph.entities;

import java.util.List;

public class RouteResult {

    private List<Integer> path;
    private int length;

    public RouteResult(List<Integer> path, int length) {
        this.path = path;
        this.length = length;
    }

    public List<Integer> getPath() {
        return path;
    }

    public int getLength() {
        return length;
    }
}
