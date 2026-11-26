
package day1027;

class Point1 {
    private int x, y;

    public Point1(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object obj) {
        Point1 p = (Point1) obj;
        if (x == p.x && y == p.y)
            return true;
        else
            return false;
    }
}

public class EqualsEx {
    public static void main(String[] args) {
        Point1 a = new Point1(2, 3);
        Point1 b = new Point1(2, 3);
        Point1 c = new Point1(3, 4);

        if (a == b) // false
            System.out.println("a==b");

        if (a.equals(b)) // true
            System.out.println("a is equal to b");

        if (a.equals(c)) // false
            System.out.println("a is equal to c");
    }
}
