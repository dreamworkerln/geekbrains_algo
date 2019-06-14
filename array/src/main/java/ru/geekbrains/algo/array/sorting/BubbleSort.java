package ru.geekbrains.algo.array.sorting;

import java.util.function.BiConsumer;


/**
 * Don't use this
 * @param <E>
 */
public class BubbleSort<E extends Object & Comparable<? super E>> implements BiConsumer<E[], Integer> {

    public void sort(E[] data, int size) {

        //int n = data.length;
        int n = size;
        E tmp;

        for (int i = 0; i < n-1; i++) {  //

            for (int j = 0; j < (n-1) - i; j++) {

                if (data[j].compareTo(data[j+1]) > 0) {

                    tmp = data[j];
                    data[j] = data[j+1];
                    data[j+1] = tmp;
                }

            }
        }

    }


    // ---------------------------------------------------

    @Override
    public void accept(E[] data, Integer size) {

        this.sort(data, size);

    }
}
