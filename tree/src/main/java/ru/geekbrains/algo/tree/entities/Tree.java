package ru.geekbrains.algo.tree.entities;

public interface Tree<E extends Comparable<? super E>> {

    enum TraverseMode {
        IN_ORDER,
        PRE_ORDER,
        POST_ORDER,
    }

    boolean add(E value);

    boolean addCludged(E value, int hops);

    boolean find(E value);

    boolean remove(E value);

    boolean isEmpty();

    int size();

    void traverse(TraverseMode mode);

    void display();

    boolean isBalanced();

    boolean isBalanced(Node<E> node);

    Node<E> getRoot();

    int height(Node<E> node);
}
