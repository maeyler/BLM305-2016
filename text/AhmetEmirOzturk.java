package text;
import javax.swing.JOptionPane;

class AhmetEmir implements Processor {
//Enter java program
    
    public String process(String input) {
        String a = JOptionPane.showInputDialog("Aranacak kelime: ");
        String s = "";
        if(a!=null){
            for (int i = 0; i <= input.length() - a.length(); i++) {
                if (a.equals(input.substring(i, i + a.length()))) {
                    s +="Aranan "+a+" kelimesi "+i+". indexte bulundu \n";
                }
            }
        }
        return s;
    }
    public String description(String source) {
        return source+" searching word in a text file";
    }
    public String author() {
        return "Ahmet Emir Öztürk";
    }
}
