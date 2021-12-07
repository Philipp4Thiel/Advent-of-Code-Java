import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("main.in"));
        //Scanner sc = new Scanner(new File("test.in"));

        Scanner drawNumbers = new Scanner(sc.nextLine().replace(",", " "));
        ArrayList<Board> boards = new ArrayList<>();
        String[] tempArr = new String[5];
        int counter = 0;
        while (sc.hasNextLine()) {
            String nextLine = sc.nextLine();
            if (nextLine.equals("")) continue;
            tempArr[counter] = nextLine;
            if (counter++ == 4) {
                boards.add(new Board(tempArr));
            }
            counter %= 5;
        }
        Board last = null;
        while (drawNumbers.hasNextInt() && boards.size() > 0) {
            String temp = "" + drawNumbers.nextInt();
            Iterator<Board> iter = boards.iterator();
            while (iter.hasNext() && boards.size() > 0) {
                Board b = iter.next();
                if (b.play(temp)) {
                    // task 1
                    /*
                    System.out.println(b);
                    System.out.println(b.score());
                    return;
                     */
                    // task 2
                    last = b;
                    iter.remove();
                }
            }
        }
        System.out.println(last);
        System.out.println(last.score());
    }
}

class Board {
    String[][] numbers = new String[5][5];
    boolean[][] marked = new boolean[5][5];
    String last;

    Board(String[] in) {
        for (int i = 0; i < 5; i++) {
            String[] temp = in[i].replace("  ", " ").strip().split("\\s");
            for (int j = 0; j < 5; j++) {
                numbers[i][j] = temp[j];
                marked[i][j] = false;
            }
        }
    }

    /**
     * @param number number that gets played
     * @return if the board won
     */
    boolean play(String number) {
        last = number;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (numbers[i][j].equals(number))
                    marked[i][j] = true;
            }
        }

        for (int i = 0; i < 5; i++) {
            boolean temp = true;
            for (int j = 0; j < 5; j++) {
                temp = temp && marked[i][j];
            }
            if (temp) return true;

            temp = true;
            for (int j = 0; j < 5; j++) {
                temp = temp && marked[j][i];
            }
            if (temp) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String res = "\n";
        for (String[] row : numbers) {
            res += Arrays.toString(row) + "\n";
        }
        return res;
    }

    int score() {
        int res = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!marked[i][j]) res += toInt(numbers[i][j]);
            }
        }
        return res * toInt(last);
    }

    int toInt(String str) {
        return Integer.parseInt(str);
    }
}
