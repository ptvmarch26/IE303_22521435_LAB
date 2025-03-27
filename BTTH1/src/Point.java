import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Point implements Comparable<Point> {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point p) {
        return this.x == p.x ? this.y - p.y : this.x - p.x;
    }

    public static int tichCoHuong(Point a, Point b, Point c) {
        return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
    }

    @Override
    public String toString() {
        return x + " " + y;
    }

    public static List<Point> nhapToaDo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập số lượng trạm: ");
        int n = sc.nextInt();

        List<Point> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.print("Nhập tọa độ trạm thứ " + (i + 1) + ": ");
            int x = sc.nextInt();
            int y = sc.nextInt();
            points.add(new Point(x, y));
        }

        return points;
    }

    public static List<Point> findConvexHull() {
        List<Point> points = nhapToaDo();
        if(points.size() <= 2) {
            return points;
        }

        Collections.sort(points);

        List<Point> hull = new ArrayList<>();

        // Lower hull
        for(Point p : points) {
            while(hull.size() >= 2 && Point.tichCoHuong(hull.get(hull.size() - 2), hull.get(hull.size() - 1), p) <= 0) {
                hull.remove(hull.size() - 1);
            }
            hull.add(p);
        }

        // Upper hull
        int lowerSize = hull.size();
        for(int i = points.size() - 2;i >= 0;i--) {
            Point p = points.get(i);
            while(hull.size() > lowerSize && Point.tichCoHuong(hull.get(hull.size() - 2), hull.get(hull.size() - 1), p) <= 0) {
                hull.remove(hull.size() - 1);
            }
            hull.add(p);
        }

        hull.remove(hull.size() - 1);
        return hull;
    }

    public static void xuatToaDo() {
        List<Point> convexHull = findConvexHull();

        for (Point p : convexHull) {
            System.out.println(p);
        }
    }
}


