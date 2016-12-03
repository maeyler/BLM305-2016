package text;

public class ErolKaftanoglu implements Processor {
    public String process(String input) {
        return "length: "+ input.length() + " , " + input;
    }
    public String description(String source) {
        return source + " text is length: " + source.length();
    }
    public String author() {
        return "Erol Kaftanoglu";
    }
}
