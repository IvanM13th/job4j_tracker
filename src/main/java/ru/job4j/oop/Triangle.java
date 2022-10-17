package ru.job4j.oop;

public class Triangle {
    private Point first;
    private Point second;
    private Point third;

    public Triangle(Point ap, Point bp, Point cp) {
        this.first = ap;
        this.second = bp;
        this.third = cp;
    }

    public double semiPeremiter(double a, double b, double c) {
        return (a + b + c) / 2;
    }

    public boolean exist(double ab, double ac, double bc) {
        return (ab + ac) > bc && (ab + bc) > ac && (ac + bc) > ab;
    }

    public double area() {
        double rsl = -1;
        double ab = first.distance(second);
        double ac = first.distance(third);
        double bc = second.distance(third);
        if (this.exist(ab, ac, bc)) {
            double p = semiPeremiter(ab, ac, bc);
            rsl = Math.sqrt(p * (p - ab) * (p - bc) * (p - ac));
        }
        return rsl;
    }

    public static void main(String[] args) {
        Point a = new Point(0, 0);
        Point b = new Point(4, 0);
        Point c = new Point(0, 4);
        Triangle triangle = new Triangle(a, b, c);
        double area1 = triangle.area();
        System.out.println(area1);
    }
}
