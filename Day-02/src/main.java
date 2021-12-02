import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("main.in"));
        //Scanner sc = new Scanner(new File("test.in"));
        int hor = 0;
        int vert = 0;
        int aim = 0;
        while (sc.hasNext()) {
            String dir = sc.next();
            int X = sc.nextInt();

            switch (dir) {
                case "forward":
                    hor += X;
                    vert += aim*X;
                    break;
                case "down":
                    aim += X;
                    break;
                case "up":
                    aim -= X;
                    break;
                default:
                    System.out.println("wth");
            }
        }
        System.out.println("res: "+hor*vert);
    }
}
