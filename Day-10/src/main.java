import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class main {

    static final Map<Character, Integer> SCORE = Map.of(
            ')', 3,
            ']', 57,
            '}', 1197,
            '>', 25137
    );

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("main.in"));
        //Scanner sc = new Scanner(new File("test.in"));

        long scoreT1 = 0;
        ArrayList<Long> scoresT2 = new ArrayList<>();

        outer:
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("")) continue;
            Stack<Character> stack = new Stack<>();

            for (char c : line.toCharArray()) {
                switch (c) {
                    case ')':
                        char cur = stack.pop();
                        if (cur == '(') continue;
                        scoreT1 += SCORE.get(c);
                        continue outer;
                    case '}':
                        cur = stack.pop();
                        if (cur == '{') continue;
                        scoreT1 += SCORE.get(c);
                        continue outer;
                    case ']':
                        cur = stack.pop();
                        if (cur == '[') continue;
                        scoreT1 += SCORE.get(c);
                        continue outer;
                    case '>':
                        cur = stack.pop();
                        if (cur == '<') continue;
                        scoreT1 += SCORE.get(c);
                        continue outer;
                    default:
                        stack.push(c);
                }
            }
            long scoreT2 = 0;
            while (!stack.empty()) {
                char c = stack.pop();
                scoreT2 *= 5;
                switch (c) {
                    case '(':
                        scoreT2 += 1;
                        break;
                    case '[':
                        scoreT2 += 2;
                        break;
                    case '{':
                        scoreT2 += 3;
                        break;
                    case '<':
                        scoreT2 += 4;
                }
            }
            scoresT2.add(scoreT2);
        }
        System.out.println("T1: " + scoreT1);
        System.out.println("T2: " + scoresT2.stream().mapToLong(Long::longValue).sorted().toArray()[scoresT2.size() / 2]);
    }
}
