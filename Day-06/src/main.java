import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("main.in"));
        //Scanner sc = new Scanner(new File("test.in"));

        long[] fish = new long[9];
        for(int n:Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray())
            fish[n]++;

        System.out.println(Arrays.toString(fish));

        for (int i = 0; i < 256; i++) {
            long[] temp = new long[9];
            System.arraycopy(fish, 1, temp, 0, 8);
            temp[6] += fish[0];
            temp[8] = fish[0];
            fish = temp;
        }
        System.out.println(Arrays.stream(fish).sum());
        System.out.println(Arrays.toString(fish));
    }
}
