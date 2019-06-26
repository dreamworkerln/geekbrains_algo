package ru.geekbrains.algo.recursion.entities;

@SuppressWarnings("Duplicates")
public class MUtils {



    public static void main(String[] args) {

        System.out.println(power(3, 5));
        System.out.println(powIt(3, 5));

        System.out.println(power(3, -5));
        System.out.println(powIt(3, -5));


        // Infinity seems to be acceptable without throwing error
        System.out.println(power(0,-2));
        System.out.println(powIt(0,-2));

    }

    static double power(double base, int exponent) {

        double result;

        if (exponent > 0) {
            result = base;
        }
        else if (exponent < 0) {
            result = 1/base;
            exponent = -exponent;
        }
        else {
            result = 1;
        }

        if (Math.abs(exponent) > 1) {

            result *= power(result, --exponent);
        }

        return result;
    }



    static double powIt(double base, int exponent) {

        double result;

        if (exponent > 0) {
            result = base;
        }
        else if (exponent < 0) {
            result = 1/base;
            exponent = -exponent;
        }
        else {
            result = 1;
        }
        base = result;

        for(int i = 1; i < exponent; i++) {
            result*= base;
        }

        return result;
    }

}
