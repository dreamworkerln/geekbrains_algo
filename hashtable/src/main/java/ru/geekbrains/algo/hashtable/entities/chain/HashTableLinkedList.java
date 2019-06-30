package ru.geekbrains.algo.hashtable.entities.chain;

import ru.geekbrains.algo.hashtable.entities.chain.Chain;
import ru.geekbrains.algo.hashtable.entities.chain.ChainLinkedList;
import ru.geekbrains.algo.hashtable.entities.chain.HashTableChain;

public class HashTableLinkedList<K,V> extends HashTableChain<K,V> {

    public HashTableLinkedList(int maxSize) {
        super(maxSize);
    }

    @Override
    Chain<K, V> createChain() {
        return new ChainLinkedList<>();
    }
}
