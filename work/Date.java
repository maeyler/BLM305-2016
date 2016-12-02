enum Month { Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec }

public class Date implements Comparable<Date> {

    static Class M = Month.class;
    int day; Month mon; int year;
    String str;
    public Date(int d, Month m, int y) {
        day = d; mon = m; year = y;
        String s = ""+mon.ordinal();
        if (mon.ordinal() < 10) s = "0"+s;
        str = year+s+day;
    }
    public int hashCode() {
        return str.hashCode();
    }
    public boolean equals(Object x) {
        Date d = (Date)x;
        return (str.equals(d.str));
    }
    public int compareTo(Date d) {
        return str.compareTo(d.str);
    }
    public String toString() { // "Jan 1, 2015"
        return mon+" "+day+", "+year;
    }
    public static void main(String[] args) {
        Date p = new Date(1, Month.Jan, 2015);
        Date q = new Date(9, Month.Jan, 1983);
        System.out.println(p+"\n"+q);
    }
}
