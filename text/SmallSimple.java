package text;

import java.util.*;

public class SmallSimple implements Processor {
    public String process(String input) {
        String[] a = input.split("\\p{Space}|\\p{Punct}");
        Set<String> h = new HashSet<>();
        for (String s : a) h.add(s);
        return h.toString();
    }
    public String description(String source) {
        return "set of distinct words in "+source;
    }
    public String author() {
        return "Small Simple";
    }
}
