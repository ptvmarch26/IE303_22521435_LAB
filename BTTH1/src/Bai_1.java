import java.util.Random;
import java.util.Scanner;

public class Bai_1 {

    public static void circleArea() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập bán kính r: ");
        double r = sc.nextDouble();
        int numPoints = 10000000;
        Random rand = new Random();
        int insideCircle = 0;
        for(int i = 0;i < numPoints;i++) {
            double x = (rand.nextDouble() * 2 * r) - r;
            double y = (rand.nextDouble() * 2 * r) - r;

            if(x * x + y * y <= r * r) {
                insideCircle++;
            }
        }
        double s = 4.0 * ((double) insideCircle / numPoints) * r * r;
        System.out.println("Diện tích của hình tròn là: " + s);
    }
}
