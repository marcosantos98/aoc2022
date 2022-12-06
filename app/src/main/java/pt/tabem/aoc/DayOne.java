/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package pt.tabem.aoc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DayOne {

    public static void main(String[] args) throws IOException {
        System.out.println("-> DayOne");
        var values = parseInput();
        problemOne(values);
        problemTwo(values);
    }

    /*
     * Since the problem asks for the elve that holds the most amount of calories
     * and the list is already sorted, we just need to take the bottom one.
     */
    public static void problemOne(List<Integer> values) {
        System.out.printf("MostCalories: %d\n", values.get(0));
    }

    /**
     * Just like the first problem we only need to add up the first three entries of the list.
     */
    public static void problemTwo(List<Integer> values) {
        int caloriesTopThree = values.get(0) + values.get(1) + values.get(2);
        System.out.printf("TopThree: %d\n", caloriesTopThree);
    }

    /**
     * The problem asks for the most calories that a single elve holds. 
     * This parses the input and sorts it in reverse order.
     */
    public static List<Integer> parseInput() throws IOException {
        var calories = new ArrayList<Integer>();
        int elveCalories = 0;
        for (var line : InputHelper.loadLines("01.txt")) {
            if (line.isEmpty()) {
                calories.add(elveCalories);
                elveCalories = 0;
            } else {
                elveCalories += Integer.valueOf(line);
            }
        }
        calories.sort(Comparator.reverseOrder());
        return calories;
    }
}