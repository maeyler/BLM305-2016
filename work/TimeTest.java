class TimeTest {
    static int num;
    int id; long start;
    public TimeTest() {
        num++; id = num;
        start = System.currentTimeMillis();
        System.out.printf("Born %s %n", id);
    }
    public long time() {
        return System.currentTimeMillis() - start;
    }
    public String toString() {
        return "Test "+id;
    }
    protected void finalize() {
        System.out.printf("Dead %s %s %n", id, time());
    }
    public static void main(String[] args) {
        new TimeTest();
    }
}
