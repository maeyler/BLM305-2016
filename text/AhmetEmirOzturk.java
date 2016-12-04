package text;
import javax.swing.JOptionPane;

class AhmetEmir implements Processor {
//Enter java program
    
    public String process(String input) {
        String a="aeýioöuü";
        String s="";
        for (char d : a.toCharArray()) {
            for (int i = 0; i < input.length(); i++) {
                if (d==input.charAt(i)) {
                    s=s+ d + " -> " + i+". indexte \n";
                   
                } 
            }
        }    
        return s;
    }
    public String description(String source) {
        return source+" finding vowel letters";
    }
    public String author() {
        return "AEO";
    }
}
