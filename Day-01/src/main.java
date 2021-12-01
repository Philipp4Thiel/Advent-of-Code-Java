import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("main.in"));

        int first = sc.nextInt();
        int second,counter=0;
        while(sc.hasNextInt()){
            second = first;
            first = sc.nextInt();
            if (first > second) counter++;
        }
        System.out.println("Task 1: " +counter);

        sc = new Scanner(new File("main.in"));

        int cur = 0;
        int last = 0;

        int[] arr = new int[3];

        int i = 0;
        counter = 0;

        while (sc.hasNextInt()) {
            arr[i % 3] = sc.nextInt();
            last = cur;
            cur = arr[0] + arr[1] + arr[2];
            if (i >= 3) {
                if (cur > last) counter++;
            }
            i++;
        }
        System.out.println("Task 2: " +counter);
    }
}
