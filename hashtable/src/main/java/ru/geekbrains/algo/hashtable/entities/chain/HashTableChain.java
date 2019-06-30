package ru.geekbrains.algo.hashtable.entities.chain;

import org.junit.jupiter.api.Assertions;
import ru.geekbrains.algo.hashtable.entities.CellType;
import ru.geekbrains.algo.hashtable.entities.Entry;
import ru.geekbrains.algo.hashtable.entities.HashTable;

import java.util.Arrays;
import java.util.function.Supplier;

public abstract class HashTableChain<K,V> implements HashTable<K,V> {


    // -------------------------------------------------------------------------


    final private Object[] data;
    private int size;
    final private int maxSize;

    public HashTableChain(int maxSize) {

        this.maxSize = maxSize;
        this.data = new Object[maxSize * 2];
    }

    private int hashFunc(K key) {
        return key.hashCode() % data.length;
    }



    // -----------------------------------------------------------------


    @Override
    public boolean put(K key, V value) {

        /*
        Три варианта -
        1. ячейка пустая.
        2. Там лежит Entry
        3. Там лежит Chain
        */


//        if (size == maxSize) {
//            return false;
//        }

        int index = hashFunc(key);

        // 1. ячейка пустая.
        if (data[index] == null) {

            data[index] = new Entry<>(key, value);
            size ++;
        }
        else {

            // ASSERT
            Assertions.assertTrue(data[index] instanceof Supplier);

            //noinspection unchecked
            CellType ct = ((Supplier<CellType>)(data[index])).get();

            Chain<K,V> chain = null;
            switch (ct) {
                //2. Там лежит Entry
                case ENTRY:

                    //noinspection unchecked
                    Entry<K,V> entry = (Entry)data[index];

                    // Если key совпадает с существующем
                    // - перетереть value
                    if (entry.getKey().equals(key)) {
                        entry.setValue(value);
                    }
                    else {

                        chain = createChain();

                        // Save current Entry to Chain
                        chain.put(entry.getKey(), entry.getValue());

                        // now data[index] point to chain
                        data[index] = chain;

                        // ASSERT
                        Assertions.assertNotNull(chain);

                        //store new key-value
                        chain.put(key, value);

                        size ++;
                    }

                    break;

                // 3. Там лежит Chain
                case CHAIN:

                    // ASSERT
                    Assertions.assertTrue(data[index] instanceof Chain);
                    //noinspection unchecked
                    chain = (Chain<K, V>) data[index];

                    int oldSize = chain.size();

                    // ASSERT
                    Assertions.assertNotNull(chain);

                    chain.put(key, value);

                    if (chain.size() > oldSize) {
                        size++;
                    }

                    break;
            }


        }

        return true;

    }





    @Override
    public V get(K key) {

        V result = null;

        int index = hashFunc(key);

        // По идексу что-то есть
        if (data[index] != null) {

            // ASSERT
            Assertions.assertTrue(data[index] instanceof Supplier);

            //noinspection unchecked
            CellType ct = ((Supplier<CellType>) (data[index])).get();

            switch (ct) {

                case ENTRY:

                    //noinspection unchecked
                    Entry<K, V> entry = (Entry<K, V>)data[index];

                    result = entry.getValue();

                    break;
                case CHAIN:

                    //noinspection unchecked
                    Chain<K, V> chain = (Chain<K, V>)data[index];

                    result = chain.get(key);
                    break;
            }
        }
        return result;
    }


    @Override
    public boolean remove(K key) {

        boolean result = false;

        int index = hashFunc(key);

        // По идексу что-то есть
        if (data[index] != null) {

            // ASSERT
            Assertions.assertTrue(data[index] instanceof Supplier);

            //noinspection unchecked
            CellType ct = ((Supplier<CellType>) (data[index])).get();

            switch (ct) {

                case ENTRY:

                    // For ASSERT
                    //noinspection unchecked
                    Entry<K, V> entry = (Entry<K, V>)data[index];

                    // ASSERT
                    Assertions.assertEquals(key, entry.getKey());

                    data[index] = null;
                    size--;
                    result = true;

                    break;
                case CHAIN:

                    //noinspection unchecked
                    Chain<K, V> chain = (Chain<K, V>)data[index];


                    boolean res = chain.delete(key);
                    if(res) {size--;}


                    // Трансформироваться обратно в Entry<K, V> не будем
                    /*
                    if (chain.size() == 1) {

                    }*/

                    if (chain.size() == 0) {
                        data[index] = null;
                    }

                    result = true;
                    break;
            }
        }

        return result;
    }


    // --------------------------------------------------------------

//    private int indexOf(K key) {
//
//        int result = -1;
//
//        int index = hashFunc(key);
//        int count = 0;
//
//        while (data[index] != null && count < data.length) {
//
//
//            //noinspection unchecked
//            Entry<K,V> entry = (Entry<K,V>)data[index];
//
//            if (entry.getKey().equals(key)) {
//
//                result = index;
//                break;
//            }
//
//            index += getStep(key);
//            index %= data.length;  // loop-over-end-to-begin of data[]
//            count++;
//        }
//        return result;
//    }


    // -----------------------------------------------------------------------------------


    abstract Chain<K,V> createChain();

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

        Arrays.fill(data, null); // GC to collect Chain elements
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



//noinspection unchecked