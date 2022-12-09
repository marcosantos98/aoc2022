package pt.tabem.aoc;

public class App {
    public static void main(String[] args) throws Exception {
        timed(DayOne::main);
        timed(DayTwo::main);
        timed(DayThree::main);
        timed(DayFour::main);
        timed(DayFive::main);
        timed(DaySix::main);
    }

    private static void timed(ExFunc block) throws Exception {
        long start = System.currentTimeMillis();
        block.apply();
        long end = System.currentTimeMillis() - start;
        System.out.printf("-> Took: %dms\n", end);
        System.out.println("==============================================");
    }

    @FunctionalInterface
    public interface ExFunc {
        void apply() throws Exception;
    }
}
