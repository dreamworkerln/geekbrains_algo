package ru.geekbrains.algo.hashtable.entities;

import java.util.Arrays;

public class HashTableImpl<K,V> implements HashTable<K,V> {


    final private Object[] data;
    private int size;            // current size
    final private int maxSize;   // maximum allowed size

    public HashTableImpl(int maxSize) {

        this.maxSize = maxSize;
        this.data = new Object[maxSize * 2];
    }

    private int hashFunc(K key) {
        return key.hashCode() % data.length;
    }


    protected int getStep(K key) {
        return 1;
    }

    // -----------------------------------------------------------------
    

    @Override
    public boolean put(K key, V value) {

        if (size == maxSize) {
            return false;
        }

        // Защита от dead loop при реализации getStep() != index++
        int count = 0;

        int index = hashFunc(key);

        while (data[index] != null) {


            //noinspection unchecked
            Entry<K, V> entry = (Entry<K, V>)data[index];

            // Same key exists - update value
            if (entry.getKey().equals(key)) {

                entry.setValue(value);
                return true;
            }

            // triggering dead loop watchdog on
            if (count >= data.length) {
                return false;
            }

            index += getStep(key);
            count++;
            index %= data.length; // loop-over-end-to-begin of data[]
        }

        data[index] = new Entry<>(key, value);
        size++;

        return true;

    }

    @Override
    public V get(K key) {

        V result = null;

        int index = indexOf(key);

        if (index != -1) {

            //noinspection unchecked
            Entry<K, V> entry = (Entry<K, V>)data[index];

            result = entry.getValue();
        }

        return result;
    }


    @Override
    public boolean remove(K key) {
        int index = indexOf(key);
        if (index != -1) {
            data[index] = null;
            size--;
            return true;
        }

        return false;
    }


    // --------------------------------------------------------------

    private int indexOf(K key) {

        int result = -1;

        int index = hashFunc(key);
        int count = 0;

        while (data[index] != null && count < data.length) {


            //noinspection unchecked
            Entry<K,V> entry = (Entry<K,V>)data[index];

            if (entry.getKey().equals(key)) {

                result = index;
                break;
            }

            index += getStep(key);
            index %= data.length;  // loop-over-end-to-begin of data[]
            count++;
        }
        return result;
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
    public void clear() {
        size = 0;
        Arrays.fill(data, null);
    }



    @Override
    public void display() {
        System.out.println("-----------");
        for (int i = 0; i < data.length; i++) {
            System.out.printf("%d = [%s]", i, data[i]);
            System.out.println();
        }
        System.out.println("-----------");
    }
}
