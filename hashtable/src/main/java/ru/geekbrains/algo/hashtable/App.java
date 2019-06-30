package ru.geekbrains.algo.hashtable;

import ru.geekbrains.algo.hashtable.entities.DoubleHashTableImpl;
import ru.geekbrains.algo.hashtable.entities.HashTable;
import ru.geekbrains.algo.hashtable.entities.chain.HashTableBST;
import ru.geekbrains.algo.hashtable.entities.chain.HashTableLinkedList;

/**
 * Hello world!
 *
 */
public class App
{

    private int[] data;

    public static void main(String[] args) {

        new App();
    }



    App() {

        //doubleHashTableTest01();

        System.out.println("\n\n\n\n");

        HashTable<Integer, Integer> ht;

        ht = new HashTableLinkedList<>(5);
        testHT2(ht);

        System.out.println("\n\n\n\n");

        ht = new HashTableBST<>(5);
        testHT2(ht);
    }

    private void  testHT2(HashTable<Integer, Integer> ht) {



        ht.put(0, 0);
        ht.put(1, 1);
        ht.put(11, 11); // will hash to index = 1
        ht.put(21, 21); // will hash to index = 1
        ht.put(31, 31); // will hash to index = 1
        ht.put(41, 41); // will hash to index = 1

        ht.remove(11);

        // cell will collapse to null
        ht.put(2, 2);
        ht.remove(2);

        // cell will collapse to null
        ht.put(3, 3);
        ht.put(33, 33);
        ht.remove(3);
        ht.remove(33);

        // cell will NOT collapse to null (Chain -> Entry not implemented)
        ht.put(4, 4);
        ht.put(44, 44);
        ht.remove(4);

        ht.display();

    }

    private void  doubleHashTableTest01() {



        int[] data = new int[1000];
        for (int i= 0; i< 1000; i++) {
            data[i] = i;
        }

        HashTable<Integer, Integer> ht = new DoubleHashTableImpl<>(5); //real maxSize = 5*2 = 10

        fill(ht, data);



        ht.display();

        ht.remove(1);

        ht.put(4, 7);

        ht.display();


        ht.clear();

        ht.put(1, 1);
        ht.put(11, 11); // will hash to index = 1
        ht.display();

        System.out.println("------------------------ collision -----------------------");

        System.out.println(ht.get(1));
        System.out.println(ht.get(11));
    }


    private void fill(HashTable<Integer, Integer> ht, int[] data) {

        for (int a : data) {

            ht.put(a, a);
        }
    }

}