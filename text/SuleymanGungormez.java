package text;

public class SuleymanGungormez implements Processor {
    public String process(String input) {
        String s = "";
        for (int i = 0; i < input.length(); i++) {
            s = s.concat(input.charAt(i) + " ");
        }
        return s;
    }
    public String description(String source) {
        return source+" karakterlerden sonra bosluk eklendi.";
    }
    public String author() {
        return "Suleyman Gungormez";
    }
}