package pt.tabem.aoc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DayFour {

    public static void main(String[] args) throws IOException {
        System.out.println("-> DayFour");
        var sections = parseInput();
        problemOne(sections);
        problemTwo(sections);
    }

    /**
     * The problem asks for the number of complete overlaped sections
     * i.e 5-7 overlaps with 1-9
     * 
     * For each section check the bounds and increase the overlap count if
     * the sections overlap.
     */
    public static void problemOne(List<SectionsInfo> sections) {
        int overlapCount = 0;
        for (var section : sections) {
            if (section.startA >= section.startB && section.endA <= section.endB
                    || section.startB >= section.startA && section.endB <= section.endA) {
                overlapCount++;
            }
        }

        System.out.printf("Number of overlaped sections: %d\n", overlapCount);
    }

    /*
     * For the second part the problem ask for the number of
     * overlaped sections that contain at least one value from the other section;
     */
    public static void problemTwo(List<SectionsInfo> sections) {
        int overlapCount = 0;
        for (var section : sections) {
            next:
            for(int i = section.startA; i <= section.endA; i++) {
                for(int j = section.startB; j <= section.endB; j++) {
                    if(i == j) {
                        overlapCount++;
                        break next;
                    }
                }
            }
        }

        System.out.printf("Number of overlaped sections part2: %d\n", overlapCount);
    }

    public record SectionsInfo(int startA, int endA, int startB, int endB) {
    }

    /*
     * The input contains two sections in each line;
     * For each line we get the start and end of each sections and return a
     * list Sections
     */
    public static List<SectionsInfo> parseInput() throws IOException {
        var sections = new ArrayList<SectionsInfo>();

        for (var line : InputHelper.loadLines("04.txt")) {
            var secs = line.split(",");
            var startA = Integer.valueOf(secs[0].split("-")[0]);
            var endA = Integer.valueOf(secs[0].split("-")[1]);
            var startB = Integer.valueOf(secs[1].split("-")[0]);
            var endB = Integer.valueOf(secs[1].split("-")[1]);

            sections.add(new SectionsInfo(startA, endA, startB, endB));
        }

        return sections;
    }
}
