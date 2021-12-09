import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("main.in"));
        //Scanner sc = new Scanner(new File("testSimple.in"));
        //Scanner sc = new Scanner(new File("test.in"));

        // task 1
        /*
        int counter = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("")) continue;
            String[] in = line.split(" \\| ");
            String[] out = in[1].strip().split(" ");
            for (String s : out) {
                int l = s.length();
                if (l == 2 || l == 3 || l == 4 || l == 7)
                    counter++;
            }
        }
        System.out.println(counter);
         */

        // task 2

        int tot = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("")) continue;

            String[] in = line.split(" \\| ");

            Map<String, Integer> mapping = getMapping(in[0].strip().split(" "));

            String[] out = in[1].strip().split(" ");
            String res = "";
            for (String s : out) {
                res += mapping.get(sortString(s));
            }
            tot += Integer.parseInt(res);
        }
        System.out.println(tot);
    }

    public static Map<String, Integer> getMapping(String[] in) {
        String ONE = null, TWO = null, THREE = null, FOUR = null, FIVE = null, SIX = null, SEVEN = null, EIGHT = null, NINE = null, ZERO = null;

        // find trivial:
        for (String s : in) {
            switch (s.length()) {
                case 2:
                    ONE = s;
                    break;
                case 3:
                    SEVEN = s;
                    break;
                case 4:
                    FOUR = s;
                    break;
                case 7:
                    EIGHT = s;
                    break;
                default:
            }
        }
        assert ONE != null;
        assert FOUR != null;
        assert SEVEN != null;
        assert EIGHT != null;

        // find 3
        for (String s : in) {
            if (s.length() != 5) continue;
            int counter = 0;
            for (char c : ONE.toCharArray()) {
                if (s.contains(c + "")) counter++;
            }
            if (counter == 2) {
                THREE = s;
                break;
            }
        }
        assert THREE != null;

        // find f
        char f = '\0';
        for (char c : FOUR.toCharArray()) {
            if (THREE.contains(c + "")) continue;
            f = c;
            break;
        }
        assert f != '\0';

        // find 5
        for (String s : in) {
            if (s.length() != 5) continue;
            if (s.equals(THREE)) continue;
            if (!s.contains("" + f)) continue;
            FIVE = s;
            break;
        }
        assert FIVE != null;

        // find 5
        for (String s : in) {
            if (s.length() != 5) continue;
            if (s.equals(THREE)) continue;
            if (s.equals(FIVE)) continue;
            TWO = s;
            break;
        }
        assert TWO != null;

        // find 6
        for (String s : in) {
            if (s.length() != 6) continue;
            if (commonChars(s, ONE) != 1) continue;
            SIX = s;
            break;
        }
        assert SIX != null;

        // find 0
        for (String s : in) {
            if (s.length() != 6) continue;
            if (s.equals(SIX)) continue;
            if (commonChars(s, THREE) != 4) continue;
            ZERO = s;
            break;
        }
        assert ZERO != null;

        // find 9
        for (String s : in) {
            if (s.length() != 6) continue;
            if (s.equals(SIX) || s.equals(ZERO)) continue;
            NINE = s;
            break;
        }
        assert NINE != null;

        Map<String, Integer> res = new HashMap<>();
        res.put(sortString(ZERO), 0);
        res.put(sortString(ONE), 1);
        res.put(sortString(TWO), 2);
        res.put(sortString(THREE), 3);
        res.put(sortString(FOUR), 4);
        res.put(sortString(FIVE), 5);
        res.put(sortString(SIX), 6);
        res.put(sortString(SEVEN), 7);
        res.put(sortString(EIGHT), 8);
        res.put(sortString(NINE), 9);
        return res;
    }

    public static String sortString(String in) {
        char[] charArr = in.toCharArray();
        Arrays.sort(charArr);
        return String.valueOf(charArr);
    }

    public static int commonChars(String a, String b) {
        int res = 0;
        for (char c : a.toCharArray()) {
            if (b.contains("" + c)) res++;
        }
        return res;
    }
}
