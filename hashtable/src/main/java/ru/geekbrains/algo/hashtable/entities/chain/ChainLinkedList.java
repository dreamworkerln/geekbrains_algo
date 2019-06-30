package ru.geekbrains.algo.hashtable.entities.chain;

import ru.geekbrains.algo.hashtable.entities.Entry;

import java.util.LinkedList;
import java.util.List;

public class ChainLinkedList<K,V> implements Chain<K,V> {

    private List<Entry<K,V>> data = new LinkedList<>();


    @Override
    public void put(K key, V value) {

        //noinspection unchecked
        //Entry entryNew = new Entry(key, value);


        boolean found = false;
        for (int i = 0; i< data.size(); i++) {

            Entry<K,V> entry = data.get(i);

            if (entry.getKey().equals(key)) {

                // update existing Entry
                entry.setValue(value);
                found = true;
                break;
            }
        }

        // add new Entry
        if (!found) {

            //noinspection unchecked
            data.add(new Entry(key, value));
        }
    }

    @Override
    public V get(K key) {

        V result = null;

        for (Entry entry : data) {

            if (entry.getKey().equals(key)) {

                //noinspection unchecked
                result = (V)entry.getValue();

                break;
            }
        }
        return result;
    }


    @Override
    public boolean delete(K key) {

        boolean result = false;

        for (int i = 0; i< data.size(); i++) {

            Entry entry = data.get(i);

            if (entry.getKey().equals(key)) {

                result = true;
                data.remove(i);
                break;
            }
        }
        return result;
    }

    @Override
    public void clear() {
        data.clear();
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public String toString() {
        return "ChainLinkedList{" +
               "data=" + data +
               '}';
    }
}
