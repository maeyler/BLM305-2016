package text;

public class SuhedaAcar implements Processor {
    public String process(String input) {
        return Integer.toString(input.length());
    }
    public String description(String source) {
        return source+" find length of a string";
    }
    public String author() {
        return "Suheda Acar";
    }
}
