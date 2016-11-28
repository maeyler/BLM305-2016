import java.awt.Point;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.*;

class Reflect {
    public static Field getField(Class c, String name) throws Exception {
        Field f = c.getDeclaredField(name);
        f.setAccessible(true); return f;
    }
    public static Method getMethod(Class c, String name, Class... a) throws Exception {
        Method m = c.getDeclaredMethod(name, a);
        m.setAccessible(true); return m;
    }
    public static Object fieldValue(Object b, String name) throws Exception {
        return getField(b.getClass(), name).get(b);
    }
    public static Object invokeMethod(Object b, String name) throws Exception {
        return getMethod(b.getClass(), name).invoke(b); //no arguments
    }
    public static Field[] declaredFields(Object b) {
        List<Field> L = new ArrayList<Field>();
        for (Field f: b.getClass().getDeclaredFields()) {
            if (Modifier.isStatic(f.getModifiers())) continue;
            f.setAccessible(true); L.add(f);
        }
        return L.toArray(new Field[0]);
    }
    public static void test(Object b) throws Exception {
        System.out.printf("%s  %n", b.getClass());
        for (Field f: declaredFields(b))
            System.out.printf("  %s %s %n", f.getType(), f.getName());
    }
    public static void main(String[] args) throws Exception {
        test(new Point(30, 40)); test("xxx"); 
    }
}
