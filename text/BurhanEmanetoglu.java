package text;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BurhanEmanetoglu implements Processor {

    public String process(String input) {
        try {
            byte[] infoBin = input.getBytes("UTF-8");
            input="";
            for (byte b : infoBin) {
                input+=("char: " + (char) b + "-> "+ Integer.toBinaryString(b)+"\n");
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(BurhanEmanetoglu.class.getName()).log(Level.SEVERE, null, ex);
        }
          return input;
    }

    public String description(String source) {
        return source + " letters converted to binary ";
    }

    public String author() {
        return "Burhan Emanetoglu";
    }

}
