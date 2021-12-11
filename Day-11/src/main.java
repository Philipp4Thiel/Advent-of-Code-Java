import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class main {
    static int FLASHES;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("main.in"));
        //Scanner sc = new Scanner(new File("test.in"));

        Oktopus[][] cave = new Oktopus[10][10];
        for (int i = 0; i < 10; i++) {
            String line = sc.nextLine();
            int j = 0;
            for (char c : line.toCharArray()) {
                cave[i][j] = new Oktopus(cave, i, j, Character.getNumericValue(c));
                j++;
            }
        }
        // task 1
        /*
        FLASHES = 0;
        for (int n = 1; n <= 100; n++) {
            increment(cave);
            reset(cave);
            //printCave(cave, n);
        }
        System.out.println(FLASHES);
        */
        // task 2
        int n = 0;
        while (FLASHES < 100) {
            FLASHES = 0;
            n++;
            increment(cave);
            reset(cave);
        }
        System.out.println(n);
    }

    static void increment(Oktopus[][] cave) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cave[i][j].increment();
            }
        }
    }

    static void reset(Oktopus[][] cave) {
        for (Oktopus[] oArr : cave) {
            for (Oktopus o : oArr) {
                o.reset();
            }
        }
    }

    static void printCave(Oktopus[][] cave, int step) {
        System.out.printf("%nAfter step %d:%n", step);
        for (Oktopus[] oArr : cave) {
            for (Oktopus o : oArr) {
                System.out.print(o.toString().equals("0") ? '#' : o.toString());
            }
            System.out.println();
        }
    }

    static class Oktopus {
        Oktopus[][] cave;
        int x;
        int y;
        int status;
        boolean flashed;

        public Oktopus(Oktopus[][] cave, int x, int y, int status) {
            this.cave = cave;
            this.x = x;
            this.y = y;
            this.status = status;
            this.flashed = false;
        }

        void increment() {
            if (++status > 9 && !flashed) {
                FLASHES++;
                flashed = true;
                for (int i = -1; i < 2; i++)
                    for (int j = -1; j < 2; j++)
                        if (valid(x + i, y + j))
                            cave[x + i][y + j].increment();
            }
        }

        boolean valid(int x, int y) {
            return x >= 0 && y >= 0 && x < 10 && y < 10;
        }

        void reset() {
            if (flashed) status = 0;
            if (status > 9) throw new IllegalStateException("should have flashed");
            flashed = false;
        }

        @Override
        public String toString() {
            return "" + status;
        }
    }
}