package ru.geekbrains.algo.hashtable.entities;

public interface HashTable<K, V> {

    boolean put(K key, V value);

    V get(K key);

    boolean remove(K key);

    int size();

    boolean isEmpty();

    void clear();

    void display();
}
