package ru.geekbrains.algo.hashtable.entities.chain;

import ru.geekbrains.algo.hashtable.entities.HashTableImpl;

import java.util.TreeMap;

public class HashTableBST<K,V> extends HashTableChain<K,V> {

    public HashTableBST(int maxSize) {
        super(maxSize);
    }

    @Override
    Chain<K, V> createChain() {
        return new ChainBST<>();
    }

}
