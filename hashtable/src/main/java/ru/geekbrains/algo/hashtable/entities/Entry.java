package ru.geekbrains.algo.hashtable.entities;

import java.util.function.Supplier;

public class Entry<K, V> implements Supplier<CellType> {

    private final K key;
    private V value;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }


    public  CellType get() {
        return CellType.ENTRY;
    }


    @Override
    public String toString() {
        return "Entry {" +
               "key=" + key +
               ", value=" + value +
               '}';
    }
}

