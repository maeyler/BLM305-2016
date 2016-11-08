abstract class Appointment {
    String desc;
    abstract boolean occursOn(int d, int m, int y);
    public String toString() { return desc; }
    
    static Appointment d, m, n;
    public static void main(String[] args) {
        d = new Daily("Floss teeth");
        m = new Monthly(4, "Call grandma");
        n = new Onetime(4, 12, 2014, "See dentist");
        System.out.printf(" %s%n %s%n %s%n", d, m, n);
    }
}
class Daily extends Appointment {
    public Daily(String s) {
        desc = "Every day -- "+s;
    }
    public boolean occursOn(int d, int m, int y) {
        return true;
    }
}
class Monthly extends Appointment {
    int day;
    public Monthly(int d, String s) {
        day = d;  desc = "Monthly "+d+" -- "+s; 
    }
    public boolean occursOn(int d, int m, int y) {
        return day == d;
    }
}
class Onetime extends Monthly {
    int month; int year;
    public Onetime(int d, int m, int y, String s) {
        super(d, null); //invokes the Monthly constructor 
        month = m; year = y;
        desc = d+"/"+m+"/"+y+" -- "+s;
    }
    public boolean occursOn(int d, int m, int y) {
        return day==d && month==m && year==y;
    }
}
