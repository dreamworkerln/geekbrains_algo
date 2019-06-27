package ru.geekbrains.algo.tree.entities;

public class BalanceLevel {
    public int left = 0;
    public int right = 0;

    @Override
    public String toString() {
        return "{" +
               left + ", " +
               right +
               '}';
    }
}
