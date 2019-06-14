package ru.geekbrains.algo.array;

import ru.geekbrains.algo.array.implementation.SortedArray;
import ru.geekbrains.algo.array.sorting.*;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Hello world!
 *
 */
public class App
{

    public static void main(String[] args) {

        new App();
    }



    App() {


        SortedArray<Integer> array = new SortedArray<>();

        final Integer SIZE = (int)1e5;

        Integer[] initial = new Integer[SIZE];

        Integer[] quick;
        Integer[] heap;
        Integer[] merge;


        QuickSort<Integer> quickSort = new QuickSort<>(); // sorry I'm fuck up by stack overflow on sorted data
        QuickSortIterative<Integer> quickSortIt = new QuickSortIterative<>();
        HeapSort<Integer> heapSort = new HeapSort<>();
        MergeSort<Integer> mergeSort = new MergeSort<>();  // useful for linked list

        // --------------------------------------------------------------------------
        // AKA tests
        // --------------------------------------------------------------------------

        System.out.println("Index of 0: " + array.indexOf(0));

        array.display();
        array.add(0);
        array.sort(heapSort);
        array.display();

        System.out.println("Index of 0: " + array.indexOf(0));
        System.out.println("Index of -1: " + array.indexOf(-1));
        System.out.println("Index of 1: " + array.indexOf(1));

        for(int k = 1; k <=15; k++) {
            array.add(k);
            array.sort(heapSort);
        }
        array.display();


        array.add(16);
        array.sort(heapSort);
        array.display();

        System.out.println("Index of 16: " + array.indexOf(16));


//        for(int k =0; k <=16; k++) {
//            System.out.println("Index of " + k + ": " + array.indexOf(k));
//        }


        for(int k = 0; k < 16; k++) {
            array.removeAt(0);
        }
        array.display();

        array.add(444);
        array.sort(heapSort);

        array.display();
        System.out.println("Index of 444: " + array.indexOf(444));
        System.out.println("Index of 16: " + array.indexOf(16));

        array.remove(16);

        System.out.println("Index of 16: " + array.indexOf(16));

        array.clear();
        array.display();

        // --------------------------------------------------------------------------

        

        System.out.println();
        System.out.println("=======================================================");
        System.out.println();

        // --------------------------------------------------------------------------
        System.out.println(String.format("Random array size: %1$d", SIZE));
        System.out.println("-----------------------------");

        for(int k =0; k< 10; k++) {


            for (int i = 0; i < initial.length; i++) {
                initial[i] = ThreadLocalRandom.current().nextInt();
            }

            quick = Arrays.copyOf(initial, initial.length);
            heap = Arrays.copyOf(initial, initial.length);
            merge = Arrays.copyOf(initial, initial.length);


            App.test(array, heap, heapSort);
            App.test(array, merge, mergeSort);
            App.test(array, quick, quickSortIt);
            System.out.println();
        }

//        (size == 10e5)=>
//        Sorting by HeapSort: 39 ms
//        Sorting by MergeSort: 108 ms   !!!

        System.out.println("=======================================================");
        System.out.println();

        // ----------------------------------------------------------------------------------------------------


        System.out.println(String.format("Sorted array size: %1$d", SIZE));
        System.out.println("-----------------------------");

        for (int i = 0; i < initial.length; i++) {
            initial[i] = i;
        }

        quick = Arrays.copyOf(initial, initial.length);
        heap = Arrays.copyOf(initial, initial.length);
        merge = Arrays.copyOf(initial, initial.length);

        App.test(array, heap, heapSort);
        App.test(array, merge, mergeSort);
        App.test(array, quick, quickSortIt);
        System.out.println();

        System.out.println("=======================================================");
        System.out.println();

        // ----------------------------------------------------------------------------------------------------

        System.out.println(String.format("Inverted sorted array size: %1$d", SIZE));
        System.out.println("-----------------------------");

        for (int i = 0; i < initial.length; i++) {
            initial[i] = initial.length-1 - i;
        }

        quick = Arrays.copyOf(initial, initial.length);
        heap = Arrays.copyOf(initial, initial.length);
        merge = Arrays.copyOf(initial, initial.length);

        App.test(array, heap, heapSort);
        App.test(array, merge, mergeSort);
        App.test(array, quick, quickSortIt);
        System.out.println();

        System.out.println("=======================================================");
        System.out.println();



        // ----------------------------------------------------------------------------------------------------


        System.out.println(String.format("All equals array size: %1$d", SIZE));
        System.out.println("-----------------------------");

        for (int i = 0; i < initial.length; i++) {
            initial[i] = 1;
        }

        quick = Arrays.copyOf(initial, initial.length);
        heap = Arrays.copyOf(initial, initial.length);
        merge = Arrays.copyOf(initial, initial.length);

        App.test(array, heap, heapSort);
        App.test(array, merge, mergeSort);
        App.test(array, quick, quickSortIt);
        System.out.println();

        System.out.println("=======================================================");
        System.out.println();



        // ----------------------------------------------------------------------------------------------------


        System.out.println(String.format("All equals except first lower array size: %1$d", SIZE));
        System.out.println("-----------------------------");

        for (int i = 0; i < initial.length; i++) {
            initial[i] = 5;
        }
        initial[0] = 4;

        quick = Arrays.copyOf(initial, initial.length);
        heap = Arrays.copyOf(initial, initial.length);
        merge = Arrays.copyOf(initial, initial.length);

        App.test(array, heap, heapSort);
        App.test(array, merge, mergeSort);
        App.test(array, quick, quickSortIt);
        System.out.println();

        System.out.println("=======================================================");
        System.out.println();



        // ----------------------------------------------------------------------------------------------------



        System.out.println(String.format("All equals except first great array size: %1$d", SIZE));
        System.out.println("-----------------------------");

        for (int i = 0; i < initial.length; i++) {
            initial[i] = 5;
        }
        initial[0] = 6;


        quick = Arrays.copyOf(initial, initial.length);
        heap = Arrays.copyOf(initial, initial.length);
        merge = Arrays.copyOf(initial, initial.length);

        App.test(array, heap, heapSort);
        App.test(array, merge, mergeSort);
        App.test(array, quick, quickSortIt);
        System.out.println();

        System.out.println("=======================================================");
        System.out.println();



        // ----------------------------------------------------------------------------------------------------



        System.out.println(String.format("All equals except last lower array size: %1$d", SIZE));
        System.out.println("-----------------------------");

        for (int i = 0; i < initial.length; i++) {
            initial[i] = 5;
        }
        initial[initial.length-1] = 4;

        quick = Arrays.copyOf(initial, initial.length);
        heap = Arrays.copyOf(initial, initial.length);
        merge = Arrays.copyOf(initial, initial.length);

        App.test(array, heap, heapSort);
        App.test(array, merge, mergeSort);
        App.test(array, quick, quickSortIt);
        System.out.println();

        System.out.println("=======================================================");
        System.out.println();



        // ----------------------------------------------------------------------------------------------------



        System.out.println(String.format("All equals except last great array size: %1$d", SIZE));
        System.out.println("-----------------------------");

        for (int i = 0; i < initial.length; i++) {
            initial[i] = 5;
        }
        initial[initial.length-1] = 6;

        quick = Arrays.copyOf(initial, initial.length);
        heap = Arrays.copyOf(initial, initial.length);
        merge = Arrays.copyOf(initial, initial.length);

        App.test(array, heap, heapSort);
        App.test(array, merge, mergeSort);
        App.test(array, quick, quickSortIt);
        System.out.println();

        System.out.println("=======================================================");
        System.out.println();



        // ----------------------------------------------------------------------------------------------------



        // ----------------------------------------------------------------------------------------------------


//        Sorted array size: 100000
//        -----------------------------
//        Sorting by HeapSort: 10 ms
//        Sorting by MergeSort: 8 ms
//        Sorting by QuickSortIterative: 14261 ms
//
//
//
//        Inverted sorted array size: 100000
//        -----------------------------
//        Sorting by HeapSort: 11 ms
//        Sorting by MergeSort: 9 ms
//        Sorting by QuickSortIterative: 9780 ms

    }


    static long test(SortedArray<Integer> array,
                     Integer[] data,
                     BiConsumer<Integer[], Integer> sortAlg) {

        long result;

        array.assign(data);

        Instant ins = Instant.now();
        array.sort(sortAlg);
        result =  Duration.between(ins, Instant.now()).toMillis();
        System.out.println(String.format("Sorting by %1$s: %2$d ms", sortAlg.getClass().getSimpleName(), result));
        return result;
    }

}
