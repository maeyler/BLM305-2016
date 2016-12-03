package text;

public class AhmetYazici implements Processor {
    public String process(String input) {
        String text[] = input.split(" ");
        String transtext = "";
        for (int i = 0; i < text.length; i++) {
            
          transtext = transtext + " " + text[text.length - i-1];
        }
        return transtext + ".... Programý yapan :" + author();
    }
    public String description(String source) {
        return source+" magnify the next letter after a dot";
    }
    public String author() {
        return "Ahmet YAZICI";
    }
}
