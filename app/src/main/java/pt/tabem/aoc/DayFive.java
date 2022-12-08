package pt.tabem.aoc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DayFive {

    public static void main(String[] args) throws IOException {
        System.out.println("-> DayFive");
        problemOne();
        problemTwo();
    }

    /*
     * Given the moves, return the top crates;
     */
    public static void problemOne() throws IOException {
        var data = parseInput();
        for (var move : data.moves) {
            for (int i = 0; i < move.amnt; i++) {
                var last = data.crates.get(move.from).removeLast();
                data.crates.get(move.to).add(last);
            }
        }

        var output = "";
        for (var crate : data.crates.entrySet()) {
            output += crate.getValue().getLast();
        }

        System.out.printf("Final topstack: %s\n", output);
    }

    /*
     * Instead of moving one each time, CrateMover 9001 moves
     * the Move::amnt in one go instead of moving one each time
     */
    public static void problemTwo() throws IOException {
        var data = parseInput();
        for (var move : data.moves) {
            if (data.crates.get(move.from).size() == 0)
                continue;
            var crates = new ArrayList<String>();
            for (int i = 0; i < move.amnt; i++) {
                crates.add(data.crates.get(move.from).removeLast());
            }
            Collections.reverse(crates);
            for (int i = 0; i < crates.size(); i++) {
                data.crates.get(move.to).add(crates.get(i));
            }
        }

        var output = "";
        for (var crate : data.crates.entrySet()) {
            output += crate.getValue().getLast();
        }

        System.out.printf("Final topstack part2: %s\n", output);
    }

    public record Move(int amnt, int from, int to) {
    }

    public record InputData(Map<Integer, LinkedList<String>> crates, List<Move> moves) {
    }

    /*
     * We reword the input to be parser friendly.
     * Instead of:
     * [a]
     * [b]
     * [b]
     * 1
     * 
     * move 1 from 1 to 1
     * 
     * Is reworked to:
     * 1-b,b,a
     * -
     * 1,1,1
     */
    public static InputData parseInput() throws IOException {
        var crates = new HashMap<Integer, LinkedList<String>>();
        var moves = new ArrayList<Move>();
        for (var line : InputHelper.loadLines("05.txt")) {
            if (line.length() > 1 && line.charAt(1) == '-') {
                int crateIndex = Integer.valueOf(line.split("-")[0]);
                var cratesInIndex = new LinkedList<String>();
                for (var crate : line.split("-")[1].split(",")) {
                    cratesInIndex.add(crate);
                }
                crates.put(crateIndex, cratesInIndex);
            } else if (line.equals("-")) {
                continue;
            } else if (Character.isDigit(line.charAt(0))) {
                var move = line.split(",");
                moves.add(new Move(Integer.valueOf(move[0]), Integer.valueOf(move[1]), Integer.valueOf(move[2])));
            } else {
                System.out.printf("Not implemented yet %s\n", line);
            }
        }
        return new InputData(crates, moves);
    }
}
