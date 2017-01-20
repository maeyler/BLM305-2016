import java.io.*;
import java.util.*;

class Student {
    int id; String name; float gpa;
    List<String> list = new ArrayList<>();
    public Student(int k, String n, float g) {
        id = k; name = n; gpa = g;
    }
    public void addCourse(String code) { list.add(code); }
    public String toString() { return name; }
    public int hashCode() { return id; } //Q7
    public boolean equals(Object x) {
        Student s = (Student)x; return (s.id == id);
    }
}

class FinalExam implements Runnable {
    File file;  int numLeft;
    final static String
        LINE_SEP = System.getProperty("line.separator"), TAB = "\t";
    Collection<Student> data = new ArrayList<>(); //Q3
        // or new HashSet<>(); -- TreeSet is not applicable
    Map<String, Student> map = new TreeMap<>(); //Q5
    public FinalExam(File f) { 
        file = f; readData(); 
    }
    void readLine(String line) {
        String[] a = line.split(TAB);
        int k = Integer.parseInt(a[0]);
        float g = Float.parseFloat(a[2]);
        Student s = new Student(k, a[1], g);
        data.add(s);
        for (int j=3; j<a.length; j++) 
            s.addCourse(a[j]); 
        map.put(a[0], s); //Q5
    }
    void readData()  {
        System.out.printf("%s %s bytes %n", file, file.length());
        try {
            InputStream in = new FileInputStream(file);
            byte[] ba = new byte[in.available()];
            in.read(ba); in.close(); 
            String[] sa = new String(ba).split(LINE_SEP);
            System.out.printf("%s satýr okundu %n", sa.length);
            for (String s : sa) { readLine(s); }
        } catch (IOException x) {
            System.out.println(x);
        }
    }
    public Student findStudent(String id) { //Q4
        int k = Integer.parseInt(id);
        for (Student s : data)
            if (s.id == k) return s;
        return null;
    }
    public Student findFast(String id) { //Q5
        return map.get(id);
    }
    public void printStudent(Student s) {
        if (s != null) 
          System.out.printf("%s %s %.2f %s%n", s.id, s.name, s.gpa, s.list);
        else System.out.printf("%s not found %n", s);
    }
    public void printStudent(String id) {
        printStudent(findFast(id));
    }
    public void printCourse(String code) { //Qx sýnavda sorulmadý
        code = code.toUpperCase();
        System.out.print(code+": [ ");
        for (Student s : data)
            if (s.list.contains(code)) 
                System.out.print(s.name+' ');
        System.out.println(']');
    }
    public void printCourses(boolean list) { //Q6
        Set<String> set = new TreeSet<>();
        for (Student s : data)
            set.addAll(s.list);
        if (list) System.out.println(set); 
        else System.out.println(set.size());
    }
    public void stop() { numLeft = 0; }
    public void run() { //Q10
        Student[] a = data.toArray(new Student[0]); //Q9
        while (numLeft > 0) try {
            int i = (int)(Math.random()*a.length);
            printStudent(a[i]);
            Thread.sleep(500); numLeft--;
        } catch (Exception x) {
        }
    }
    public void printStudents(int n) {
        numLeft = n;
        new Thread(this).start(); //Q10
    }
    public void doQuestions() {
        System.out.println("Q1. Ayþe Kaya için bir kayýt oluþturun");
        Student s = new Student(1234, "Ayþe Kaya", 2.05f);
        s.addCourse("BLM 401"); s.addCourse("Math 254");
        printStudent(s); 
        System.out.println("Q3. Okulda "+data.size()+" öðrenci var");
        System.out.println("Q5. Numarasý verilen bir öðrenciyi bulun");
        printStudent("214001771");
        System.out.print("Q6. Açýlan ders sayýsý: ");
        printCourses(false);  
        System.out.print("Q7. HashSet h.size(): ");
        Set<Student> h = new HashSet<>(data);
        System.out.println(h.size());
        System.out.println("Qx. Bir dersi alan öðrenci isimleri");
        printCourse("math 206"); 
        System.out.println("Q10. Rastgele 5 öðrencinin listesi");
        printStudents(5);
    }
    
    public static void main(String[] args) {
        File f = new File("exam", "Students.txt");
        new FinalExam(f).doQuestions();
    }
}
