package ru.geekbrains.algo.array.implementation;

public class InvalidArrayStateException extends RuntimeException {

    public InvalidArrayStateException(int index, int arraySize) {
        super(String.format("Invalid index value %d; array size is %d", index, arraySize));
    }
}
