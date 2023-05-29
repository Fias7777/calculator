package Calcuator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Map<String, Integer> mapRoman = new HashMap<>();
    private static final Map<Integer, String> mapArabic = new HashMap<>();

    static {
        mapRoman.put("I", 1);
        mapRoman.put("II", 2);
        mapRoman.put("III", 3);
        mapRoman.put("IV", 4);
        mapRoman.put("V", 5);
        mapRoman.put("VI", 6);
        mapRoman.put("VII", 7);
        mapRoman.put("VIII", 8);
        mapRoman.put("IX", 9);
        mapRoman.put("X", 10);
        mapRoman.put("L", 50);
        mapRoman.put("C", 100);

        mapArabic.put(1, "I");
        mapArabic.put(2, "II");
        mapArabic.put(3, "III");
        mapArabic.put(4, "IV");
        mapArabic.put(5, "V");
        mapArabic.put(6, "VI");
        mapArabic.put(7, "VII");
        mapArabic.put(8, "VIII");
        mapArabic.put(9, "IX");
        mapArabic.put(10, "X");
        mapArabic.put(50, "L");
        mapArabic.put(100, "C");
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введи пример"); // 10 + 10
        String input = scanner.nextLine();
        calc(input);
    }

    public static void calc(String input) {
        String[] array = input.split(" "); //[10, +, 10]
        exception(array);
        switch (array[1]) {
            case "+":
                if (mapRoman.containsKey(array[0])) {
                    int[] intArray = converterRomeToArabic(array);
                    int sum = intArray[0] + intArray[1];
                    System.out.println(converterArabicToRoman(sum));
                } else {
                    System.out.println(Integer.parseInt(array[0]) + Integer.parseInt(array[2]));
                }
                break;
            case "-":
                if (mapRoman.containsKey(array[0])) {
                    int[] intArray = converterRomeToArabic(array);
                    int dif = intArray[0] - intArray[1];
                    System.out.println(converterArabicToRoman(dif));
                } else {
                    System.out.println(Integer.parseInt(array[0]) - Integer.parseInt(array[2]));
                }
                break;

            case "*":
                if (mapRoman.containsKey(array[0])) {
                    int[] intArray = converterRomeToArabic(array);
                    int mult = intArray[0] * intArray[1];
                    System.out.println(converterArabicToRoman(mult));
                } else {
                    System.out.println(Integer.parseInt(array[0]) * Integer.parseInt(array[2]));
                }
                break;
            case "/":
                if (mapRoman.containsKey(array[0])) {
                    int[] intArray = converterRomeToArabic(array);
                    int div = intArray[0] / intArray[1];
                    System.out.println(converterArabicToRoman(div));
                } else {
                    System.out.println(Integer.parseInt(array[0]) / Integer.parseInt(array[2]));
                }
                break;
            default:
                throw new RuntimeException("Неправильный формат ввода данных");
        }
    }


    public static int[] converterRomeToArabic(String[] array) { // [X, +, III]
        int a = 0; //10
        int b = 0; //3
        int[] intArray = new int[2];
        if (mapRoman.containsKey(array[0])) {
            a = mapRoman.get(array[0]);
        }
        if (mapRoman.containsKey(array[2])) {
            b = mapRoman.get(array[2]);
        }
        intArray[0] = a;
        intArray[1] = b;
        return intArray;
    }

    public static String converterArabicToRoman(int a) { //73 LXXIII
        String s = "";
        while (a != 0) {
            if (mapArabic.containsKey(a)) {
                s += mapArabic.get(a);
                return s;
            } else if (a < 100 && a > 50) {
                s += "L";
                a -= 50;
            } else if (a < 50 && a > 10) {
                s += "X";
                a -= 10;
            } else {
                s += mapArabic.get(a);
            }
        }
        return s;
    }

    public static void exception(String[] array) {
        if (array.length != 3) {
            throw new RuntimeException("Неправильный формат ввода данных");
        }
        if (!mapRoman.containsKey(array[0]) && !mapArabic.containsKey(Integer.parseInt(array[0]))) {
            throw new RuntimeException("Неправильный формат ввода данных");
        }
        if (mapRoman.containsKey(array[0])) {
            if (!mapRoman.containsKey(array[2]) ||
                    mapRoman.get(array[0]) > 10 ||
                    mapRoman.get(array[0]) < 1 ||
                    mapRoman.get(array[2]) > 10 ||
                    mapRoman.get(array[2]) < 1
            ) {
                throw new RuntimeException("Неправильный формат ввода данных");
            }
        } else if (mapArabic.containsKey(Integer.parseInt(array[0]))) {
            if (!mapArabic.containsKey(Integer.parseInt(array[2])) ||
                    Integer.parseInt(array[0]) > 10 ||
                    Integer.parseInt(array[0]) < 1 ||
                    Integer.parseInt(array[2]) > 10 ||
                    Integer.parseInt(array[2]) < 1
            ) {
                throw new RuntimeException("Неправильный формат ввода данных");
            }
        }

    }
}


