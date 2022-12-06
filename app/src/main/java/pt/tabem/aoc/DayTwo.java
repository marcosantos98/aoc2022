package pt.tabem.aoc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DayTwo {

    public static void main(String[] args) throws IOException {
        System.out.println("-> DayTwo");
        var rounds = parseInput();
        problemOne(rounds);
        problemTwo(rounds);
    }

    /**
     * Get the score using the cheatsheet that the elve gave us:
     * AX -> Rock 1
     * BY -> Paper 2
     * CZ -> Scissors 3
     * Win: 6
     * Draw: 3
     * Lose: 0
     */
    public static void problemOne(List<Round> rounds) {
        int score = 0;

        for (var round : rounds) {
            score += switch (round.b) {
                case "X" -> 1;
                case "Y" -> 2;
                case "Z" -> 3;
                default -> 0;
            };
            if (round.a.equals("A") && round.b.equals("Y"))
                score += 6;
            else if (round.a.equals("A") && round.b.equals("X"))
                score += 3;
            else if (round.a.equals("B") && round.b.equals("Z"))
                score += 6;
            else if (round.a.equals("B") && round.b.equals("Y"))
                score += 3;
            else if (round.a.equals("C") && round.b.equals("X"))
                score += 6;
            else if (round.a.equals("C") && round.b.equals("Z"))
                score += 3;
        }

        System.out.printf("Rock Paper Scissors total score: %d\n", score);
    }

    /*
     * The actual cheatsheet is wrong.
     * 
     * The new one is:
     * IF X THEN Y
     * A -> Draw
     * B -> Lose
     * C -> Win
     * 
     * They score points stays the same and we need to lose, draw or win depending
     * on the
     * openent input.
     * AX -> Rock 1
     * BY -> Paper 2
     * CZ -> Scissors 3
     * Win: 6
     * Draw: 3
     * Lose: 0
     */
    public static void problemTwo(List<Round> rounds) {
        int score = 0;

        for (var round : rounds) {
            if (round.a.equals("A") && round.b.equals("Y"))
                score += 4;
            else if (round.a.equals("A") && round.b.equals("X"))
                score += 3;
            else if (round.a.equals("A") && round.b.equals("Z"))
                score += 8;
            else if (round.a.equals("B") && round.b.equals("Y"))
                score += 5;
            else if (round.a.equals("B") && round.b.equals("X"))
                score += 1;
            else if (round.a.equals("B") && round.b.equals("Z"))
                score += 9;
            else if (round.a.equals("C") && round.b.equals("Y"))
                score += 6;
            else if (round.a.equals("C") && round.b.equals("X"))
                score += 2;
            else if (round.a.equals("C") && round.b.equals("Z"))
                score += 7;
        }

        System.out.printf("With the new cheatsheet: %d\n", score);
    }

    public record Round(String a, String b) {
    }

    public static List<Round> parseInput() throws IOException {
        var rounds = new ArrayList<Round>();
        for (var line : InputHelper.loadLines("02.txt")) {
            var plays = line.split(" ");
            rounds.add(new Round(plays[0], plays[1]));
        }
        return rounds;
    }
}
