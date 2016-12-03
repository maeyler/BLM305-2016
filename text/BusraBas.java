package text;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

public class BusraBas implements Processor {
    public String process(String input) {
        String sonuc = "";
        Pattern pattern = Pattern.compile("([0-9]*)");
        Matcher matcher = pattern.matcher(input);
        while(matcher.find())
            sonuc += matcher.group();
        return sonuc;
    }
    public String description(String source) {
        return source+ "find the number in the file";
    }
    public String author() {
        return "Busra Bas";
    }
}
