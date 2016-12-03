package text;

public class EnesCelik implements Processor {
    public String process(String input) {
        return input.replaceAll("\\s+","");
    }
    public String description(String source) {
        return source+" cleared from whitespaces";
    }
    public String author() {
        return "Enes Celik";
    }
}
