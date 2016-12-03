package text;

public class OmerKocbil implements Processor {
    
    public String process(String input) {
        StringBuffer bf = new StringBuffer(input);
        return bf.reverse().toString();
    }
    
    public String description(String source) {
        return source+" reversed";
    }
    
    public String author() {
        return "Omer Kocbil";
    }

}
