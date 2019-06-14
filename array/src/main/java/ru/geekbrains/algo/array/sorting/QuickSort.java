package ru.geekbrains.algo.array.sorting;


import java.util.function.BiConsumer;

// Java program for implementation of QuickSort
//
//https://www.geeksforgeeks.org/quick-sort/
//
public class QuickSort<E extends Object & Comparable<? super E>> implements BiConsumer<E[], Integer>
{
    /* This function takes last element as pivot,
       places the pivot element at its correct
       position in sorted array, and places all
       smaller (smaller than pivot) to left of
       pivot and all greater elements to right
       of pivot */
    int partition(E[] arr, int low, int high)
    {
        E pivot = arr[high];
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j].compareTo(pivot) <= 0)
            {
                i++;

                // swap arr[i] and arr[j]
                E temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        E temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }


    /* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
    void sort(E[] arr, int low, int high)
    {
        if (low < high)
        {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            sort(arr, low, pi-1);
            sort(arr, pi+1, high);
        }
    }

    @Override
    public void accept(E[] data, Integer size) {

        sort(data, 0, size -1);

    }
}
/*This code is contributed by Rajat Mishra */

