package ru.geekbrains.algo.recursion;

import java.util.ArrayList;
import java.util.List;

public class CombGenerator {

    private int n; // Общее количество элементов
    private int m; // Количество элементов в сочетании

    private List<Integer> list;
    List<List<Integer>> result;





    /**
     *
     * @param n Общее количество элементов
     * @param m Количество элементов в сочетании
     */
    public List<List<Integer>> getCombinations(int n, int m) {

        this.n = n;
        this.m = m;

        result = new ArrayList<>();
        list = new ArrayList<>();

        generate(0,-1);

        return result;
    }

    /**
     *
     * @param pos количество элементов в сочетании
     * @param last последний добавляемый элемент
     */
    private void generate(int pos , int last) {

        // print result
        if(pos == m){

            // clone
            ArrayList<Integer> tmp = new ArrayList<>(list);
            result.add(tmp);
        }

        for(int i = last + 1; i <= n && pos < m; i++) {

            list.add(i);
            generate(pos + 1, i);
            list.remove(list.size() -1);
        }
    }
}
