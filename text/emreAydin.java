package text;
import java.util.*;
public class emreAydin implements Processor {
    public String process(String input) {
        int count=0;
        String[] a =input.split("\\p{Space}|\\{Punct}");
        TreeSet<String> t=new TreeSet<String>();
        for (String s : a) {
            count++;
            t.add(s.toUpperCase());
        }

        int repeatWord = count - t.size();
       return t.toString() + "\n"+" Cumledeki toplam kelime sayisi= " + count + "\n" + " Tekrar Eden Kelime Sayisi= " + repeatWord;
    }
    public String description(String source) {
       return " Alphabetical sorting and Total word in "+source;
    }
    public String author() {
        return "Emre Aydin";
    }
}
