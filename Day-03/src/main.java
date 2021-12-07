import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("main.in"));
        //Scanner sc = new Scanner(new File("test.in"));

        ArrayList<String> strArr = new ArrayList<>();

        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            if (str.equals("")) continue;
            strArr.add(str);
        }

        int n = strArr.get(0).length();
        String gamma = "";
        String eps = "";

        for (int i = 0; i < n; i++) {
            int ones = 0;
            int zeros = 0;
            for (String str : strArr) {
                if (str.charAt(i) == '1') ones++;
                else zeros++;
            }
            if (ones == zeros) throw new IllegalArgumentException();
            gamma += ones > zeros ? '1' : '0';
            eps += ones > zeros ? '0' : '1';
        }
        System.out.println("Gamma: " + Integer.parseInt(gamma, 2));
        System.out.println("Epsilon: " + Integer.parseInt(eps, 2));
        System.out.println("Res: " + (Integer.parseInt(gamma, 2) * Integer.parseInt(eps, 2)));

        // task 2
        ArrayList<String> task2o2 = new ArrayList<>(strArr);
        ArrayList<String> task2co2 = new ArrayList<>(strArr);

        int pos = 0;
        while (task2o2.size() > 1) {
            char bit = getCommon(task2o2, pos);
            Iterator<String> itr = task2o2.iterator();
            while (itr.hasNext() && task2o2.size() > 1) {
                String cur = itr.next();
                if (cur.charAt(pos) != bit) itr.remove();
            }
            pos++;
        }

        pos = 0;
        while (task2co2.size() > 1) {
            char bit = getCommon(task2co2, pos);
            Iterator<String> itr = task2co2.iterator();
            while (itr.hasNext() && task2co2.size() > 1) {
                String cur = itr.next();
                if (cur.charAt(pos) == bit) itr.remove();
            }
            pos++;
        }
        System.out.println("O2 rating: " + Integer.parseInt(task2o2.get(0), 2));
        System.out.println("CO2 rating: " + Integer.parseInt(task2co2.get(0), 2));
        System.out.println("res: " + Integer.parseInt(task2co2.get(0), 2) * Integer.parseInt(task2o2.get(0), 2));
    }

    private static char getCommon(ArrayList<String> arr, int i) {
        int ones = 0;
        int zeros = 0;
        for (String str : arr) {
            if (str.charAt(i) == '1') ones++;
            else zeros++;
        }
        return ones >= zeros ? '1' : '0';
    }
}
