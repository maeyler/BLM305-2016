enum Month { Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec }

public class Date {

    static Class M = Month.class;
    int day; Month mon; int year;
    public Date(int d, Month m, int y) {
        day = d; mon = m; year = y;
    }
/** implement these methods
    public int hashCode() {
        return ;
    }
    public boolean equals(Object x) {
        return ;
    }
    public int compareTo(Date d) {
        return ;
    }
**/
    public String toString() { // "Jan 1, 2015"
        return mon+" "+day+", "+year;
    }
    public static void main(String[] args) {
        Date p = new Date(1, Month.Jan, 2015);
        Date q = new Date(9, Month.Jan, 1983);
        System.out.println(p+"\n"+q);
    }
}
