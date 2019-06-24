package ru.geekbrains.algo.recursion.entities;

public class Item {

    private double weight;
    private double worth;
    private String name;

    public Item(String name, double weight, double worth) {
        this.weight = weight;
        this.worth = worth;
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }
    public double getWorth() {
        return worth;
    }

    public String getName() {
        return name;
    }
}
