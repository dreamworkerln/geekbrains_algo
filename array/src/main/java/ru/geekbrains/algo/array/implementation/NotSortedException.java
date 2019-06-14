package ru.geekbrains.algo.array.implementation;

public class NotSortedException extends RuntimeException {

    public NotSortedException() {
        super("This operation on non-sorted array not allowed, sort it first");
    }
}
