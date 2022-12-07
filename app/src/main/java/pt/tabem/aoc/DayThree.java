package pt.tabem.aoc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DayThree {
    public static void main(String[] args) throws IOException {
        System.out.println("-> DayThree");
        var rucksacks = parseInput();
        problemOne(rucksacks);
        problemTwo(rucksacks);
    }

    /**
     * Get all the rucksacks and check for the same item in each bank.
     * When found, sum the priority of the item for all the rucksacks.
     * 
     * a -> z = 1..26
     * A -> Z = 27..52
     */
    public static void problemOne(List<Rucksack> rucksacks) {
        int sumOfPriorities = 0;
        for (var rucksack : rucksacks) {
            for (int i = 0; i < rucksack.first.length(); i++) {
                if (rucksack.second.indexOf(rucksack.first.charAt(i)) != -1) {
                    var ascii = (int) rucksack.first.charAt(i);
                    int priority = 0;
                    if (ascii >= 65 && ascii <= 90)
                        priority = ascii - 65 + 27;
                    else if (ascii >= 97 && ascii <= 122)
                        priority = ascii - 97 + 1;

                    sumOfPriorities += priority;
                    break;
                }
            }
        }

        System.out.printf("Sum of priorities: %d\n", sumOfPriorities);
    }

    /*
     * Group all items in each rucksack and check if the next two contains the same item
     * Then sum all the badge priorities with the same rules that the part one gave us.
     */
    public static void problemTwo(List<Rucksack> rucksacks) {
        int sumOfPriorities = 0;
        int i = 0;
        while (i < rucksacks.size()) {
            var a = rucksacks.get(i).first + rucksacks.get(i).second;
            var b = rucksacks.get(i + 1).first + rucksacks.get(i + 1).second;
            var c = rucksacks.get(i + 2).first + rucksacks.get(i + 2).second;

            for (int j = 0; j < a.length(); j++) {
                if (b.indexOf(a.charAt(j)) != -1 && c.indexOf(a.charAt(j)) != -1) {
                    var ascii = (int) a.charAt(j);
                    int priority = 0;
                    if (ascii >= 65 && ascii <= 90)
                        priority = ascii - 65 + 27;
                    else if (ascii >= 97 && ascii <= 122)
                        priority = ascii - 97 + 1;

                    sumOfPriorities += priority;
                    break;
                }
            }
            i += 3;
        }
        System.out.printf("Sum of badge priorities: %d\n", sumOfPriorities);
    }

    public record Rucksack(String first, String second) {
    }

    public static List<Rucksack> parseInput() throws IOException {
        var rucksacks = new ArrayList<Rucksack>();
        for (var line : InputHelper.loadLines("03.txt"))
            rucksacks.add(new Rucksack(line.substring(0, line.length() / 2), line.substring(line.length() / 2)));
        return rucksacks;
    }
}
