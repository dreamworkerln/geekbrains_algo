package ru.geekbrains.algo.recursion;

import ru.geekbrains.algo.recursion.entities.Item;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

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

        recursionSolution(6);

        System.out.println();
        System.out.println();
        System.out.println("------------------------------------------------------------------------ ");

        //------------------------------------------------------------------------
        //Iterative
        //------------------------------------------------------------------------

        iterativeSolution(6);



    }

    private void recursionSolution(double capacity) {

        Instant t = Instant.now();

        List<Item> itemList = createItems();
        NavigableMap<Double, List < Integer >> worthResult = new TreeMap<>();

        CombGenerator cg = new CombGenerator();
        for (int i = 1; i <=itemList.size() ; i++) {

            List<List<Integer>> result = cg.getCombinations(itemList.size() - 1, i);


            for (List<Integer> singleTry : result) {

                double worthSum = 0;
                double weightSum = 0;
                for (Integer index : singleTry) {
                    worthSum += itemList.get(index).getWorth();
                    weightSum +=  itemList.get(index).getWeight();
                }

                if (weightSum <= capacity) {
                    worthResult.put(worthSum, singleTry);
                }
            }
        }


        System.out.println("Backpack carrying capacity: " + capacity);

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for(Map.Entry<Double, List<Integer>> entry : worthResult.entrySet()) {


            sb1.setLength(0);
            sb2.setLength(0);

            sb1.append(entry.getKey() + ": ");

            double tmp = 0;

            for (Integer i : entry.getValue()) {

                Item item = itemList.get(i);
                sb2.append(item.getName() + ", ");
                tmp+= item.getWeight();
            }
            sb1.append(tmp + "   ");
            sb1.append(sb2);

            System.out.println(sb1);
        }

        System.out.println("---------");
        System.out.println("Elapsed: " + Duration.between(t, Instant.now()).toMillis());

    }

    // -------------------------------------------------------------------------




    private void iterativeSolution(double capacity) {

        Instant t = Instant.now();

        List<Item> itemList = createItems();
        NavigableMap<Double,Integer> worthResult = new TreeMap<>();


        //List<Integer> bitMaskList = new ArrayList<>();

        int max = (1 << itemList.size()) - 1; // set all digits of 1 (1111111)

        // Cycle through all combination of items
        for (int number = 1; number < max; number++) {



            double worthSum = 0;
            double weightSum = 0;

            // количество бит в number (log2(number))
            int maxIndex = BigInteger.valueOf(number).bitLength();

            // Check what bits are set on
            for (int index = 0; index < maxIndex; index++) {

                boolean bit = (number & (1 << index)) != 0;

                if (bit) {
                    worthSum += itemList.get(index).getWorth();
                    weightSum += itemList.get(index).getWeight();
                }
            }

            if (weightSum <= capacity) {
                worthResult.put(worthSum, number);
            }

        }

        // Printing -------------------------------


        System.out.println("Backpack carrying capacity: " + capacity);

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for(Map.Entry<Double, Integer> entry : worthResult.entrySet()) {


            sb1.setLength(0);
            sb2.setLength(0);

            sb1.append(entry.getKey() + ": ");

            double tmp = 0;


            // Check what bits are set on
            int maxIndex = BigInteger.valueOf(entry.getValue()).bitLength();
            for (int index = 0; index < maxIndex; index++) {

                boolean bit = (entry.getValue() & (1 << index)) != 0;
                if (bit) {

                    Item item = itemList.get(index);
                    sb2.append(item.getName() + ", ");
                    tmp+= item.getWeight();
                }
            }
            sb1.append(tmp + "   ");
            sb1.append(sb2);


            System.out.println(sb1);
        }

        System.out.println("---------");
        System.out.println("Elapsed: " + Duration.between(t, Instant.now()).toMillis());
    }





    // =======================================================================

    List<Item> createItems() {

        List<Item> result = new ArrayList<>();

        result.add(new Item("book", 1, 600));
        result.add(new Item("binoculars", 2, 5000));
        result.add(new Item("aid", 4, 1500));
        result.add(new Item("laptop", 2, 40000));
        result.add(new Item("pot", 1, 500));
        result.add(new Item("charge", 10, 10000));
        result.add(new Item("detonator", 1, 1000));
        result.add(new Item("gps", 2, 30000));
        result.add(new Item("ak", 6, 20000));
        result.add(new Item("ammo", 10, 2000));
        result.add(new Item("sleepingbag", 3, 10000));
        result.add(new Item("food", 8, 1000));
        result.add(new Item("gas", 4, 500));
        result.add(new Item("stove", 2, 10000));

        return result;

    }

    private static int log2(int x) {

        //http://mathforum.org/library/drmath/view/55565.html
        return (int)(Math.log(x) / Math.log(2) + 1);

    }

}
