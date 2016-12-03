package text;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeryaKopsen implements Processor {
    public String process(String input) {
        Pattern p = Pattern.compile("\\b\\w{3}\\b");
        Matcher m = p.matcher(input);
        List<String> allMatches = new ArrayList<>();
        while (m.find()) {
            allMatches.add(m.group());
        }
        return allMatches.toString();
    }
    public String description(String source) {
        return "find words with 3 letters "+source;
    }
    public String author() {
        return "Derya Kopsen";
    }
}
