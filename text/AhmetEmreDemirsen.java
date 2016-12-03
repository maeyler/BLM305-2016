package text;

public class AhmetEmreDemirsen implements Processor {
    public String process(String input) {
        String s = "";
        for (int i = s.length()-1; i >= 0; i--) {
            s = s.concat(input.charAt(i)+"");
        }
	return s;
    }
    public String description(String source) {
        return source+" reversed!";
    }
    public String author() {
        return "aedemirsen";
    }
}
