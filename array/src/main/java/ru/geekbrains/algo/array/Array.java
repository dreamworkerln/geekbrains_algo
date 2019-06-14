package ru.geekbrains.algo.array;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface Array<E> {

    void add(E value);

    E get(int index);

    boolean remove(E value);

    void removeAt(int index);

    default boolean contains(E value) {

        return indexOf(value) != -1;
    }

    int indexOf(E value);

    int size();
    boolean isEmpty();

    void display();

    void sort(BiConsumer<E[], Integer> sortProvider);

    void clear();
//    void bubbleSort();
//    void selectSort();
//    void insertSort();
//    void heapSort();
//    void quickSort();

}
