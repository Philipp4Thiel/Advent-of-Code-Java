import com.sun.source.tree.SwitchTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class main {

    static final HashMap<String, String> RULES = new HashMap<>();
    static char START;
    static char END;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("main.in"));
        //Scanner sc = new Scanner(new File("test.in"));

        String cur = sc.nextLine().strip();
        START = cur.charAt(0);
        END = cur.charAt(cur.length() - 1);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("")) continue;
            String[] temp = line.split(" -> ");
            RULES.put(temp[0], temp[1]);
        }
        // task 2
        HashMap<String, Long> polymer = new HashMap<>();

        for (int i = 0; i < cur.length() - 1; i++) {
            String temp = "" + cur.charAt(i) + cur.charAt(i + 1);
            if (!polymer.containsKey(temp)) polymer.put(temp, 1L);
            else polymer.put(temp, polymer.get(temp) + 1);
        }
        int n = 40;
        //System.out.println(polymer);
        for (int i = 1; i <= n; i++) {
            //System.out.print("Step " + i + ": ");
            polymer = getNext(polymer);
            //System.out.println(polymer);
        }

        System.out.println(getScore(polymer));
        /* task 1: yeah this won't work for n=40
        int steps = 10;
        for (int i = 1; i <= steps; i++) {
            System.out.print("After step " + i + ": ");
            cur = getNext(cur);
            System.out.println(cur);
        }

        HashMap<Character, Integer> count = new HashMap<>();
        for (char c : cur.toCharArray()) {
            if (!count.containsKey(c))
                count.put(c, 1);
            else
                count.put(c, count.get(c) + 1);
        }

        long max = Integer.MIN_VALUE;
        long min = Integer.MAX_VALUE;
        for (int i : count.values()) {
            max = Math.max(max, i);
            min = Math.min(min, i);
        }
        System.out.println(max-min);
    }

    private static String getNext(String cur) {
        String res = "";
        for (int i = 0; i < cur.length() - 1; i++) {
            res += cur.charAt(i);
            res += RULES.get(cur.charAt(i) + "" + cur.charAt(i + 1));
        }
        return res + cur.charAt(cur.length() - 1);
     */
    }

    private static HashMap<String, Long> getNext(HashMap<String, Long> polymer) {
        HashMap<String, Long> res = new HashMap<>();

        for (String s : polymer.keySet()) {
            long n = polymer.get(s);

            String temp = s.charAt(0) + RULES.get(s);
            if (!res.containsKey(temp)) res.put(temp, n);
            else res.put(temp, res.get(temp) + n);

            temp = RULES.get(s) + s.charAt(1);
            if (!res.containsKey(temp)) res.put(temp, n);
            else res.put(temp, res.get(temp) + n);
        }

        return res;
    }

    private static long getScore(HashMap<String, Long> polymer) {

        HashMap<Character, Long> count = new HashMap<>();

        count.put(START, 1L);
        count.put(END, 1L);

        for (String s : polymer.keySet()) {
            long n = polymer.get(s);

            for (char c : s.toCharArray()) {
                if (!count.containsKey(c)) count.put(c, n);
                else count.put(c, count.get(c) + n);
            }
        }
        long max = count.values().stream().max(Long::compareTo).get() / 2;
        long min = count.values().stream().min(Long::compareTo).get() / 2;
        System.out.println(max);
        System.out.println(min);
        return max - min;
    }
}
