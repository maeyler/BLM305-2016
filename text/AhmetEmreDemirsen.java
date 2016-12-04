package text;

public class AhmetEmreDemirsen implements Processor {
    public String process(String input) {
        String s = "";
        for (int i = 0; i < input.length(); i++) {
            s = s.concat((int)input.charAt(i)+" ");
        }
	return s;
    }
    public String description(String source) {
        return source+" converted to a string that contains ASCII numbers of its characters.";
    }
    public String author() {
        return "aedemirsen";
    }
}
