package ru.geekbrains.algo.tree;

import ru.geekbrains.algo.tree.entities.Tree;
import ru.geekbrains.algo.tree.entities.TreeImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Hello world!
 *
 */
public class App
{

    public static void main(String[] args) {

        new App();
    }
    
    

    App() {

        Tree<Integer> tree = new TreeImpl<>();
        
        tree.add(60);
        tree.add(25);
        tree.add(66);
        tree.add(15);
        tree.add(5);
        tree.add(20);
        tree.add(45);
        tree.add(30);
        tree.add(32);
        tree.add(55);

        System.out.println("Balanced(hand made): " + tree.isBalanced());
        System.out.println("Balanced(NOT hand made): " + tree.isBalanced(tree.getRoot()));

        tree = new TreeImpl<>();
        
        tree.add(15);
        tree.add(5);
        tree.add(20);

        System.out.println("Balanced(hand made): " + tree.isBalanced());
        System.out.println("Balanced(NOT hand made): " + tree.isBalanced(tree.getRoot()));


        //List<Tree<Integer>> treeList = new ArrayList<>();

        final int TREE_COUNT = 20;
        final int MAX_DEPTH = 4;

        int balancedCount = 0;


        System.out.println();

        for (int i = 0; i < TREE_COUNT; i++) {

            tree = new TreeImpl<>();

            while (tree.height(tree.getRoot()) < MAX_DEPTH) {

                int rnd = ThreadLocalRandom.current().nextInt(-20, 20 + 1);
                tree.addCludged(rnd, MAX_DEPTH);
            }


            if (tree.isBalanced(tree.getRoot()))
                balancedCount++;

        }

        System.out.println("Unbalanced: " + Math.round(((double)(TREE_COUNT-balancedCount)/TREE_COUNT)*100) + "%");

    }

}
