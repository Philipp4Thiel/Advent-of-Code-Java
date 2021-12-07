import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("main.in"));
        //Scanner sc = new Scanner(new File("test.in"));

        int[] in = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();

        int min = Arrays.stream(in).min().getAsInt();
        int max = Arrays.stream(in).max().getAsInt();

        int bestPos = -1;
        int bestFuel = Integer.MAX_VALUE;

        for (int i = min; i <= max; i++) {
            int temp = getFuel(in, i);
            if (temp < bestFuel) {
                bestFuel = temp;
                bestPos = i;
            }
        }
        System.out.println("pos: " + bestPos);
        System.out.println("fuel: " + bestFuel);
    }

    private static int getFuel(int[] in, int i) {
        int res = 0;
        for (int a : in) {
            int dist = Math.abs(a - i);
            res += dist * (dist + 1) / 2;
        }
        return res;
    }
}
