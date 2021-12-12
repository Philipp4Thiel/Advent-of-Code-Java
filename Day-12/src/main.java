import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class main {

    static cave START;
    static cave END;
    static SmallCave SMALLTWICE;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("main.in"));
        //Scanner sc = new Scanner(new File("test.in"));
        Map<String, cave> allCaves = new HashMap<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("")) continue;
            String[] temp = line.split("-");
            for (String s : temp) {
                if (!allCaves.containsKey(s)) {
                    if (Character.isLowerCase(s.charAt(0))) {
                        allCaves.put(s, new SmallCave(s));
                    } else {
                        allCaves.put(s, new BigCave(s));
                    }
                }
            }
            allCaves.get(temp[0]).addNext(allCaves.get(temp[1]));
            allCaves.get(temp[1]).addNext(allCaves.get(temp[0]));
        }
        START = allCaves.get("start");
        END = allCaves.get("end");

        System.out.println(START.visit(END).size());
    }

    interface cave {

        //int visit(cave goal);
        List<String> visit(cave goal);

        void addNext(cave next);
    }

    static class BigCave implements cave {
        String name;
        Set<cave> next = new HashSet<>();

        BigCave(String name) {
            this.name = name;
        }

        @Override
        public void addNext(cave next) {
            this.next.add(next);
        }

        /*
                @Override
                public int visit(cave goal) {
                    if (this == goal) return 1;
                    int res = 0;
                    for (cave c : next)
                        res += c.visit(goal);
                    return res;
                }
        */
        @Override
        public List<String> visit(cave goal) {
            if (this == goal) return List.of(name);
            List<String> res = new ArrayList<>();
            for (cave c : next)
                for (String path : c.visit(goal))
                    res.add(name + "," + path);

            return res;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    static class SmallCave implements cave {
        String name;
        boolean visited;
        boolean visitedTwice;
        Set<cave> next = new HashSet<>();

        public SmallCave(String name) {
            this.name = name;
        }

        @Override
        public void addNext(cave next) {
            this.next.add(next);
        }

        /*
                @Override
                public int visit(cave goal) {
                    if (this == goal) return 1;
                    if (visited) return 0;
                    visited = true;
                    int res = 0;
                    for (cave c : next)
                        res += c.visit(goal);
                    return res;
                }
        */
        @Override
        public List<String> visit(cave goal) {
            boolean second = false;
            if (this == goal) return List.of(name);
            if (visited)
                if (SMALLTWICE == null && this != START) {
                    SMALLTWICE = this;
                    visitedTwice = true;
                    second = true;
                } else
                    return List.of();
            else {
                visited = true;
            }
            List<String> res = new ArrayList<>();
            for (cave c : next)
                for (String path : c.visit(goal))
                    res.add(name + "," + path);
            if (second) {
                visitedTwice = false;
                SMALLTWICE = null;
            } else {
                visited = false;
            }
            return res;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}