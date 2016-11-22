import java.util.*;

class AppBook implements Runnable {
    int day, month, year; //current date d/m/y
    int delay = 4*1000; //msec = one day
    int n = 0; //days
    Thread thd;
    Collection<Appointment> L = new ArrayList<>();
    public AppBook(int d, int m, int y) {
        day = d; month = m; year = y; 
    }
    public void nextDay() { //modifies d/m/y to the following day
        day++; 
        if (day <= 30) return;
        day = 1; month++;
        if (month <= 12) return;
        month = 1; year++;
    }
    public void report() { 
    //print each Appointment in L that occurs on current date d/m/y
        System.out.printf("%nTo be done on %s/%s/%s: \n", day, month, year);
        for (Appointment a : L)
            if (a.occursOn(day, month, year))
                System.out.printf("%s%n", a);
    }
    public void start(int x) {  
        n = x; thd = new Thread(this); thd.start(); 
    }
    public void stop() {  n = 0; thd = null; }
    public void run() { //reports the items on current date, waits for 24 hours
        while (n > 0) {
            report();
            try { //why try?  ...
                Thread.sleep(delay);
            } catch (Exception e) {
            }
            nextDay();  n--;
        }
    }
    public void addTestData() { 
        L.add(new Daily("Floss teeth"));
        L.add(new Monthly(14, "Call grandma"));
        L.add(new Onetime(11, 11, 2016, "See dentist"));
    }
    public static void main(String[] args) {
        AppBook b = new AppBook(9, 11, 2016); //initial date
        b.addTestData();
        b.start(60);   //start new Thread using b
    }
}
