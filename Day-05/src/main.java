import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("main.in"));
        //Scanner sc = new Scanner(new File("test.in"));
        ArrayList<int[]> in = new ArrayList<>();
        int max = 0;

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("")) continue;
            String[] temp = line.split(" -> ");

            int[] nr = new int[]{
                    Integer.parseInt(temp[0].split(",")[0]),
                    Integer.parseInt(temp[0].split(",")[1]),
                    Integer.parseInt(temp[1].split(",")[0]),
                    Integer.parseInt(temp[1].split(",")[1])
            };
            for (int a : nr) {
                max = Math.max(a, max);
            }

            //if (nr[0] != nr[2] && nr[1] != nr[3]) continue;
            in.add(nr);
        }

        int[][] field = new int[1000][1000];

        for (int[] line : in) {
            //printfield(field, 10);
            //System.out.println(Arrays.toString(line) + ":");
            if (line[0] == line[2]) {
                int start = Math.min(line[1], line[3]);
                int end = Math.max(line[1], line[3]);

                for (int i = start; i <= end; i++)
                    field[line[0]][i]++;
                continue;
            }
            if (line[1] == line[3]) {
                int start = Math.min(line[0], line[2]);
                int end = Math.max(line[0], line[2]);

                for (int i = start; i <= end; i++)
                    field[i][line[1]]++;
                continue;
            }
            int diff = line[0] - line[2];
            diff = Math.abs(diff);
            for (int i = 0; i <= diff; i++) {
                int[] temp = lerp(line, i);
                field[temp[0]][temp[1]]++;
            }
        }
        //printfield(field, 10);

        int counter = 0;
        for (int[] a : field)
            for (int b : a)
                if (b > 1)
                    counter++;
        System.out.println(counter);
    }

    static void printfield(int[][] field, int max) {
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                int temp = field[j][i];
                System.out.print(temp == 0 ? '.' : "" + temp);
            }
            System.out.println();
        }
    }

    static int[] lerp(int[] coords, int steps) {
        int[] res = new int[]{0, 0};
        int x1 = coords[0];
        int y1 = coords[1];
        int x2 = coords[2];
        int y2 = coords[3];

        if (x1 > x2)
            res[0] = x1 - steps;
        else
            res[0] = x1 + steps;

        if (y1 > y2)
            res[1] = y1 - steps;
        else
            res[1] = y1 + steps;

        return res;
    }
}
