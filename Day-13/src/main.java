import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class main {
    static Set<Point> POINTS = new HashSet<>();

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("main.in"));
        //Scanner sc = new Scanner(new File("test.in"));

        // points
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("")) break;
            POINTS.add(new Point(Arrays.stream(line.trim().split(",")).mapToInt(Integer::parseInt).toArray()));
        }

        // fold
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("")) break;
            line = line.replaceAll("[^(y|x)=\\d]", "");
            int fold = Integer.parseInt(line.substring(2));
            Set<Point> tempPoints = new HashSet<>();
            if (line.charAt(0) == 'x') {
                for (Point p : POINTS) {
                    if (p.x == fold) System.out.println("pls don't happen");
                    else if (p.x > fold) {
                        tempPoints.add(new Point(2 * fold - p.x, p.y));
                    } else tempPoints.add(p);
                }
            } else if (line.charAt(0) == 'y') {
                for (Point p : POINTS) {
                    if (p.y == fold) System.out.println("pls don't happen");
                    else if (p.y > fold) {
                        tempPoints.add(new Point(p.x, 2 * fold - p.y));
                    } else tempPoints.add(p);
                }
            } else throw new IllegalArgumentException("WTF is this input");
            POINTS = tempPoints;
            System.out.println("#Points: " + POINTS.size());
            //printField();
        }
        printField();
    }

    static void printField() {
        int maxX = 0;
        int maxY = 0;
        for (Point p : POINTS) {
            maxX = Math.max(maxX, p.x);
            maxY = Math.max(maxY, p.y);
        }

        char[][] field = new char[maxY + 1][maxX + 1];
        for (char[] c : field) Arrays.fill(c, ' ');
        for (Point p : POINTS) {
            field[p.y][p.x] = '#';
        }
        for (char[] a : field) {
            for (char c : a)
                System.out.print(c);
            System.out.println();
        }
    }
}

class Point {
    int x;
    int y;

    Point(int[] arr) {
        this.x = arr[0];
        this.y = arr[1];
    }

    Point(int x, int y) {
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
