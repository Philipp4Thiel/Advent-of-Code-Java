import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("main.in"));
        //Scanner sc = new Scanner(new File("test.in"));

        ArrayList<int[]> arrayList = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("")) continue;

            int[] temp = new int[line.length()];
            int i = 0;
            for (char c : line.toCharArray())
                temp[i++] = Character.getNumericValue(c);
            arrayList.add(temp);
        }

        int[][] in = new int[arrayList.size()][];
        in = arrayList.toArray(in);

        int n = in.length;
        int m = in[0].length;

        int[][] tempIn = new int[5 * n][5 * m];
        for (int i = 0; i < n; i++) {
            System.arraycopy(in[i], 0, tempIn[i], 0, m);
        }
        for (int i = n; i < 5 * n; i++) {
            for (int j = 0; j < m; j++) {
                tempIn[i][j] = (tempIn[i - n][j] % 9) + 1;
            }
        }
        for (int i = 0; i < 5 * n; i++) {
            for (int j = m; j < 5 * m; j++) {
                tempIn[i][j] = (tempIn[i][j - m] % 9) + 1;
            }
        }

        in = tempIn;
        n = in.length;
        m = in[0].length;

        boolean[][] visited = new boolean[n][m];

        PriorityQueue<Pair> prioQ = new PriorityQueue<>();
        prioQ.add(new Pair(0, 0, 0));
        while (!prioQ.isEmpty()) {
            Pair cur = prioQ.poll();
            int i = cur.i;
            int j = cur.j;
            int score = cur.score;
            if (visited[i][j]) continue;
            if (i == n - 1 && j == m - 1) {
                System.out.println(score);
                break;
            }
            visited[i][j] = true;

            if (i > 0) prioQ.add(new Pair(i - 1, j, score + in[i - 1][j]));
            if (j > 0) prioQ.add(new Pair(i, j - 1, score + in[i][j - 1]));
            if (i < n - 1) prioQ.add(new Pair(i + 1, j, score + in[i + 1][j]));
            if (j < m - 1) prioQ.add(new Pair(i, j + 1, score + in[i][j + 1]));
        }
    }
}

class Pair implements Comparable {
    int i;
    int j;

    int score;

    Pair(int i, int j, int score) {
        this.i = i;
        this.j = j;
        this.score = score;
    }


    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Pair)) throw new RuntimeException("fuck of");
        Integer ts = this.score;
        Integer os = ((Pair) o).score;
        return ts.compareTo(os);
    }
}