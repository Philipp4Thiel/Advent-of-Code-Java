import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("main.in"));
        //Scanner sc = new Scanner(new File("test.in"));

        ArrayList<int[]> inArr = new ArrayList<>();

        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            if (s.equals("")) continue;
            int[] temp = new int[s.length()];
            inArr.add(temp);
            int i = 0;
            for (char c : s.toCharArray()) {
                temp[i++] = Character.getNumericValue(c);
            }
        }

        int[][] in = new int[inArr.size()][];
        in = inArr.toArray(in);

        int n = in.length;
        int m = in[0].length;

        Set<Point> lowPoints = new HashSet<>();

        // find low
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int cur = in[i][j];
                boolean low;
                low = (i == 0 || cur < in[i - 1][j]) &&
                        (i == n - 1 || cur < in[i + 1][j]) &&
                        (j == 0 || cur < in[i][j - 1]) &&
                        (j == m - 1 || cur < in[i][j + 1]);
                if (low) lowPoints.add(new Point(i, j));
            }
        }

        int[][] size = new int[n][m];
        boolean[][] visited = new boolean[n][m];

        for (int k = 9; k-- > 0; ) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (in[i][j] == k) {
                        int temp = 1;
                        if (i > 0) if (in[i][j] < in[i - 1][j] && in[i - 1][j] != 9) if (!visited[i - 1][j]) {
                            temp += size[i - 1][j];
                            visited[i - 1][j] = true;
                        }
                        if (i < n - 1) if (in[i][j] < in[i + 1][j] && in[i + 1][j] != 9) if (!visited[i + 1][j]) {
                            temp += size[i + 1][j];
                            visited[i + 1][j] = true;
                        }
                        if (j > 0) if (in[i][j] < in[i][j - 1] && in[i][j - 1] != 9) if (!visited[i][j - 1]) {
                            temp += size[i][j - 1];
                            visited[i][j - 1] = true;
                        }
                        if (j < m - 1) if (in[i][j] < in[i][j + 1] && in[i][j + 1] != 9) if (!visited[i][j + 1]) {
                            temp += size[i][j + 1];
                            visited[i][j + 1] = true;
                        }
                        size[i][j] = temp;
                    }
                }
            }
        }
        int[] basins = new int[lowPoints.size()];
        int i = 0;
        for (Point p : lowPoints) {
            basins[i++] = size[p.x][p.y];
        }
        int[] temp = Arrays.stream(basins).sorted().toArray();
        System.out.println(temp[i - 1] * temp[i - 2] * temp[i - 3]);
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}