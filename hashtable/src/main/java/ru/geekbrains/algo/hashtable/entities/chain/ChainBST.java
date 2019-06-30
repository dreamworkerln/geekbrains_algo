package ru.geekbrains.algo.hashtable.entities.chain;

import java.util.Map;
import java.util.TreeMap;


public class ChainBST<K,V>  implements Chain<K,V>  {

    private Map<K,V> data = new TreeMap<>();

    @Override
    public void put(K key, V value) {
        data.put(key, value);
    }

    @Override
    public V get(K key) {
        return data.get(key);
    }

    @Override
    public boolean delete(K key) {

        return data.remove(key) != null;
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public void clear() {
        data.clear();
    }

    @Override
    public String toString() {
        return "ChainBST{" +
               "data=" + data +
               '}';
    }
}
