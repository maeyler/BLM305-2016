package text;

public class HamzaOzder implements Processor {
    public String process(String input) {
        return input.replace(' ', '*');
    }
    public String description(String source) {
        return source+" Bosluklar yildiz olsun.";
    }
    public String author() {
        return "Hamza Ozder";
    }
}
