package text;

public class BarbarosKaradag implements Processor {
    public String process(String input) {
        String cumle[] = input.split("\\.");
        String output = "";
        for (int i = 0; i < cumle.length; i++) {
            
            cumle[i] = cumle[i].trim();
            int len = cumle[i].length();
       
            String a = cumle[i].substring(0,1).toUpperCase();
            
            cumle[i] = a + cumle[i].substring(1,len);
            output += cumle[i] + ". ";
        }
        return output;
    }
    public String description(String source) {
        return source+" magnify the next letter after a dot";
    }
    public String author() {
        return "Barbaros Hayrettin Karadag";
    }
}
