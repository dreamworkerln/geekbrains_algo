package ru.geekbrains.algo.array.sorting;


import java.util.Collections;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

// https://www.geeksforgeeks.org/iterative-quick-sort/

public class QuickSortIterative<E extends Object & Comparable<? super E>> implements BiConsumer<E[], Integer> {

    // A utility function to swap two elements
    void swap(E[] arr, int i, int j)
    {
        E tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /* This function is same in both iterative and recursive*/
    int partition(E[] arr, int l, int h)
    {
        E x = arr[h];
        int i = (l - 1);

        for (int j = l; j <= h - 1; j++) {
            if (arr[j].compareTo(x) <= 0) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr,i + 1, h);
        return (i + 1);
    }

    /* A[] --> Array to be sorted,
    l --> Starting index,
    h --> Ending index */
    void quickSortIterative(E[] arr, int l, int h)
    {
        // Create an auxiliary stack
        int[] stack = new int[h - l + 1];

        // initialize top of stack
        int top = -1;

        // push initial values of l and h to stack
        stack[++top] = l;
        stack[++top] = h;

        // Keep popping from stack while is not empty
        while (top >= 0) {
            // Pop h and l
            h = stack[top--];
            l = stack[top--];

            // Set pivot element at its correct position
            // in sorted array
            int p = partition(arr, l, h);

            // If there are elements on left side of pivot,
            // then push left side to stack
            if (p - 1 > l) {
                stack[++top] = l;
                stack[++top] = p - 1;
            }

            // If there are elements on right side of pivot,
            // then push right side to stack
            if (p + 1 < h) {
                stack[++top] = p + 1;
                stack[++top] = h;
            }
        }
    }

    @Override
    public void accept(E[] data, Integer size) {

        quickSortIterative(data, 0, size -1);

    }
}