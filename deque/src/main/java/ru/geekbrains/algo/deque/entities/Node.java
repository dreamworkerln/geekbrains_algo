package ru.geekbrains.algo.deque.entities;

public class Node<E> {
    E data;

    Node<E> previous = null;
    Node<E> next = null;

    public Node(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
