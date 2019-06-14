package ru.geekbrains.algo.array.implementation;


import ru.geekbrains.algo.array.Array;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;


@SuppressWarnings("WeakerAccess")
public class SortedArray<E extends Object & Comparable<? super E>> implements Array<E> {

    protected int INITIAL_SIZE = 16;

    protected E[] data;

    protected boolean sorted;

    protected int size;

    // ---------------------------


    // -----------------------------------------------------------------------------------------------------------------


    public SortedArray() {

        createNewInit();
    }



    // -----------------------------------------------------------------------------------------------------------------


    @Override
    public void add(E value) {

        checkIsSorted();
        checkNeedGrow();

        data[size++] = value;

        sorted = false;
    }
    

    @Override
    public E get(int index) {

        checkIndex(index);
        return data[index];
    }



    @Override
    public boolean remove(E value) {

        boolean result = false;

        int index = binarySearch(value);

        result = index != -1;

        if (result) {
            deleteAt(index);
            checkNeedShrink();
        }

        return result;
    }

    @Override
    public void removeAt(int index) {

        checkIndex(index);
        deleteAt(index);
        checkNeedShrink();
    }



    @Override
    public int indexOf(E value) {

        return binarySearch(value);
    }



    @Override
    public int size() {
        return size;
    }



    @Override
    public boolean isEmpty() {
        return size == 0;
    }



    @Override
    public void display() {

        System.out.println("-----");
        for (int i = 0; i < size; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
        System.out.println("-----");
    }



    @Override
    public void sort(BiConsumer<E[], Integer> sortProvider) {

        sortProvider.accept(data, size);

        // may trust me
        sorted = true;
    }


    @Override
    public void clear() {

        createNewInit();
    }


    // ----------------------------------------------------------------------------------------------

    public void assign(E[] data) {

        this.data = data;
        size = data.length;
        sorted = false;
    }


    // ----------------------------------------------------------------------------------------------



    private void checkIsSorted() {

        if (!sorted) {
            throw new NotSortedException();
        }

    }


    /**
     * Dihotomy search
     * @param value to search
     * @return index, -1 if not found
     */
    private int binarySearch(E value) {

        checkIsSorted();

        int result = -1;
        int mid; // middle
        int cmp; // result of comparision


        int low = 0;  // low index
        int hi = size -1; // hi index

        while (low <= hi) {

            mid = low + (hi - low)/2;

            cmp = value.compareTo(data[mid]);
            if (cmp > 0) {
                low = mid + 1;
            }
            else if (cmp < 0) {
                hi = mid - 1;
            }
            else { // equal, found
                result = mid;
                break;
            }
        }

        return result;
    }


    private void deleteAt(int index) {

        System.arraycopy(data, index + 1, data, index, size - 1 - index);
        data[size-1] = null;
        size--;
    }



    private void checkIndex(int index) {

        if (index < 0 || index > size -1) {
            throw new InvalidArrayStateException(index, size);
        }
    }


    private void checkNeedGrow() {

        if (size == data.length) {
            resize(data.length*2);
        }
    }


    private void checkNeedShrink() {

        if (size == data.length/4) {
            resize(data.length/4);
        }
    }


    /**
     * Resize data
     * @param newLength new length
     */
    private void resize(int newLength) {

        data = Arrays.copyOf(data, newLength);
    }


    @SuppressWarnings("unchecked")
    private void createNewInit() {
        data = (E[])new Object[INITIAL_SIZE];
        size = 0;
        sorted = true;
    }


    // ----------------------------------------------------------------------------------------------


}
