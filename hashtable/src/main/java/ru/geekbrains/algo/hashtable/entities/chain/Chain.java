package ru.geekbrains.algo.hashtable.entities.chain;

import ru.geekbrains.algo.hashtable.entities.CellType;

import java.util.function.Supplier;

public interface Chain<K,V> extends Supplier<CellType> {

    void put(K key, V value);

    V get(K key);

    boolean delete(K key);


    // WhoamI!
    default CellType get() {
        return CellType.CHAIN;
    }

    void clear();

    int size();


}
