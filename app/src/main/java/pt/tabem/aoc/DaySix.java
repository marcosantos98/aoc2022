package pt.tabem.aoc;

import java.io.IOException;

public class DaySix {

    public static void main() throws IOException {
        System.out.println("-> DaySix");
        var data = InputHelper.loadAsString("06.txt");
        problemOne(data);
        problemTwo(data);
    }

    /**
     * Get the where the marker is placed after the first 4
     * non repeating letters are found.
     * 
     * i.e alsaldfgakfjlkdspksp
     * ^
     * |
     * 
     * In the case above the first four as a common letter and
     * the next four non repeating letters and at 7;
     * 
     */
    public static void problemOne(String data) {
        System.out.printf("Marker position: %d\n", findMarkerStart(data, 4));
    }

    /*
     * Instead of looking for 4 non repeating letters now we need
     * to find 14 and the return the start of those 14 letters.
     */
    public static void problemTwo(String data) {
        System.out.printf("Start of message marker position: %d\n", findMarkerStart(data, 14));
    }

    /*
     * Use a sliding window on the input and check for the count of distinct letters.
     * If same as n return;
     */
    private static int findMarkerStart(String data, int n) {
        int cursor = 0;
        var window = "";
        while (cursor < data.length()) {
            int last = cursor + n > data.length() ? cursor + (data.length() - cursor) : cursor + n;
            window = data.substring(cursor, last);

            if (window.chars().distinct().count() == n)
                break;

            cursor++;
        }
        return cursor + window.length();
    }
}
