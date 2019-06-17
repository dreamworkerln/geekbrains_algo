package ru.geekbrains.algo.deque.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.Iterator;

@SuppressWarnings("ConstantConditions")
class DequeDLLTest {

    static Deque<Integer> deq;


    @BeforeAll
    static void initAll() {

        deq = new DequeDLL<>();
    }

    @BeforeEach
    void init() {

        deq.clear();
    }



    @Test
    void add() {

        deq.addFirst(132);

        //Assert.assertEquals(1,deq.size());

        Assertions.assertEquals(1,deq.size());
        Assertions.assertEquals(132, (int)deq.peekFirst());
        Assertions.assertEquals(132, (int)deq.peekLast());

        Assertions.assertEquals(132, (int)deq.pollFirst());
        Assertions.assertNull(deq.pollLast());

        deq.addLast(132);
        Assertions.assertEquals(132, (int)deq.pollLast());
        Assertions.assertNull(deq.pollFirst());

    }

    // Testing Stack
    @Test
    void stack() {

        deq.addFirst(1);
        deq.addFirst(2);
        deq.addFirst(3);

        Assertions.assertEquals(3, (int)deq.pollFirst());
        Assertions.assertEquals(2, (int)deq.pollFirst());
        Assertions.assertEquals(1, (int)deq.pollFirst());
    }


    // Testing Queue
    @Test
    void queue() {

        deq.addFirst(1);
        deq.addFirst(2);
        deq.addFirst(3);

        Assertions.assertEquals(1, (int)deq.pollLast());
        Assertions.assertEquals(2, (int)deq.pollLast());
        Assertions.assertEquals(3, (int)deq.pollLast());
    }


    @Test
    void random() {

        deq.addFirst(1);
        deq.addLast(2);
        deq.addLast(3);

        Assertions.assertEquals(3, (int)deq.pollLast());
        Assertions.assertEquals(2, (int)deq.pollLast());
        Assertions.assertEquals(1, (int)deq.pollLast());

        Assertions.assertNull(deq.pollLast());
        Assertions.assertNull(deq.pollFirst());

        Assertions.assertNull(deq.peekLast());
        Assertions.assertNull(deq.peekFirst());
        Assertions.assertEquals(0, deq.size());

        deq.addFirst(1);
        Assertions.assertEquals(1, (int)deq.pollLast());

        deq.addFirst(2);
        Assertions.assertEquals(2, (int)deq.pollFirst());


        deq.addLast(3);
        Assertions.assertEquals(3, (int)deq.pollLast());


        deq.addLast(4);
        Assertions.assertEquals(4, (int)deq.pollFirst());


        deq.addFirst(1);
        deq.addLast(2);

        Assertions.assertEquals(1, (int)deq.pollFirst());
        Assertions.assertEquals(2, (int)deq.pollLast());


        deq.addFirst(2);
        deq.addLast(1);

        Assertions.assertEquals(2, (int)deq.pollFirst());
        Assertions.assertEquals(1, (int)deq.pollLast());


    }

    @Test
    void iterator() {

        deq.addLast(1);
        deq.addLast(2);
        deq.addLast(3);

        Iterator<Integer> it = deq.iterator();

        int i = 1;
        while(it.hasNext()) {
            Assertions.assertEquals(i++, (int)it.next());
        }
    }

    @Test
    void descendingIterator() {

        deq.addLast(1);
        deq.addLast(2);
        deq.addLast(3);

        Iterator<Integer> it = deq.descendingIterator();

        int i = 3;
        while(it.hasNext()) {
            Assertions.assertEquals(i--, (int)it.next());
        }
    }
}