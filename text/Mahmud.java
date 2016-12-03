package text;

import java.util.*;

public class Mahmud implements Processor {
    public String process(String input) {
        String c = input.concat(".. " + author());
        return c.toUpperCase();
    }
    public String description(String source) {
        return "Converted to upper case "+source;
    }
    public String author() {
        return "Mahmud kasim";
    }
}
