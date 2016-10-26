class Rectangle {
    //north-west and south-east corners
    Point nw, se; 
    public Rectangle(Point a, Point b) {
        nw = a; se = b;
    }
    public String toString() {
        return nw+":"+se;
    }
}

class Point {
    int x, y;  
    static Rectangle a;
    public Point(int a, int b) {
        x = a; y = b;
    }
    public String toString() {
        return "("+x+","+y+")";
    }
    public static void main(String[] args) {
        Point p = new Point(3, 4);
        Point q = new Point(5, 8);
        a = new Rectangle(p, q); 
        System.out.println(a); 
    }
}
