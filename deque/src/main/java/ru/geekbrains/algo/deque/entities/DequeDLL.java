package ru.geekbrains.algo.deque.entities;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

/**
 * Deque dual linked list
 * @param <E>
 */
public class DequeDLL<E> implements Deque<E>  {

    Node<E> head = null;
    Node<E> tail= null;
    int size = 0;


    /**
     * Add to head
     * @param e Node
     */
    @Override
    public void addFirst(E e) {

        //boolean headSameTail = head == tail;

        Node<E> n = new Node<>(e);

        n.next = head;
        n.previous = null;

        if(head != null) {
            head.previous = n;
        }

        head = n;
        size++;

        if(tail == null) {
            tail = head;
        }
    }



    /**
     * Add to tail
     * @param e Node
     */
    @Override
    public void addLast(E e) {

        Node<E> n = new Node<>(e);

        n.next = null;
        n.previous = tail;

        if(tail!= null) {
            tail.next = n;
        }

        tail = n;
        size++;

        if(head == null) {
            head = tail;
        }
    }




    /**
     * Remove ang get from head
     * @param e Node
     */
    @SuppressWarnings("Duplicates")
    @Override
    public E pollFirst() {

        E result = null;

        if(head != null) {

            result = head.getData();

            head = head.next;

            if(head != null) {
                head.previous = null;
            }
            else {
                tail = null;
            }
            size--;
        }

        return result;
    }

    

    /**
     * Remove ang get from tail
     * @param e Node
     */
    @SuppressWarnings("Duplicates")
    @Override
    public E pollLast() {

        E result = null;

        if(tail != null) {

            result = tail.getData();
            tail = tail.previous;

            if(tail != null) {
                tail.next = null;
            }
            else {
                head = null;
            }
            size--;
        }

        return result;
    }


    /**
     * Examine head (don't touch)
     * @param e Node
     */
    @Override
    public E peekFirst() {

        E result = null;

        if(head != null) {
            result = head.getData();
        }

        return result;
    }


    /**
     * Examine tail (don't touch)
     * @param e Node
     */
    @Override
    public E peekLast() {

        E result = null;

        if(tail != null) {
            result = tail.getData();
        }

        return result;
    }


    @Override
    public void clear() {

        Node<E> node = head;

        while (node != null) {
            node.setData(null); // aka ~Node()
            node = node.next;
        }

        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {

        Iterator<E> it = new Iterator<E>() {

            private Node<E> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {

                E result = node.getData();
                node = node.next;
                return result;
            }
        };

        return it;
    }

    @Override
    public Iterator<E> descendingIterator() {

        Iterator<E> it = new Iterator<E>() {

            private Node<E> node = tail;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                E result = node.getData();
                node = node.previous;
                return result;
            }
        };

        return it;
    }

    // Требуется отладчику -------------------------------

    @Override
    public Object[] toArray() {

        Object[] result = new Object[size];
        Node node = head;

        int i=0;
        while (node != null) {

            result[i++] = node.getData();
            node = node.next;
        }

        return result;
    }

    @Override
    public <E> E[] toArray(E[] t) {

        E[] result = (E[]) new Object[size];
        Node node = head;

        int i=0;
        while (node != null) {

            result[i++] = (E)node.getData();
            node = node.next;
        }

        return result;
    }


    // ------------------------------------------------------------------------------------------
    // Do not want implement
    // ------------------------------------------------------------------------------------------


    // Queue and Stack interface methods

    @Override
    public boolean add(E e) {
        throw new NotImplementedException();
    }


    @Override
    public E poll() {
        throw new NotImplementedException();
    }


    @Override
    public E peek() {
        throw new NotImplementedException();
    }

    @Override
    public void push(E e) {
        throw new NotImplementedException();
    }

    @Override
    public E pop() {
        throw new NotImplementedException();
    }

    @Override
    public boolean remove(Object o) {
        throw new NotImplementedException();
    }


    @Override
    public E element() {
        throw new NotImplementedException();
    }

    @Override
    public E remove() {
        throw new NotImplementedException();
    }

    @Override
    public boolean offer(E e) {
        throw new NotImplementedException();
    }

    // ------------------------------------------------------------------------------------------------


    @Override
    public boolean contains(Object o) {
        throw new NotImplementedException();
    }




    @Override
    public boolean removeAll(Collection<?> collection) {
        throw new NotImplementedException();
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new NotImplementedException();
    }


    @Override
    public boolean containsAll(Collection<?> collection) {
        throw new NotImplementedException();
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        throw new NotImplementedException();
    }

    @Override
    public boolean offerFirst(E e) {
        throw new NotImplementedException();
    }

    @Override
    public boolean offerLast(E e) {
        throw new NotImplementedException();
    }

    @Override
    public E removeFirst() {
        throw new NotImplementedException();
    }

    @Override
    public E removeLast() {
        throw new NotImplementedException();
    }

    @Override
    public E getFirst() {
        throw new NotImplementedException();
    }

    @Override
    public E getLast() {
        throw new NotImplementedException();
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        throw new NotImplementedException();
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        throw new NotImplementedException();
    }


}
