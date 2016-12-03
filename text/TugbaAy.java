package text;

public class TugbaAy implements Processor {
	String kalinharfler = "AIOUaýou";
    String inceharfler = "EÝÖÜeiöü";
    String rakamlar = "0123456789";
    boolean kelimekontrol(String kelime) {
        boolean sonuc = false;
        boolean varyok1 = false;
        boolean varyok2 = false;
        boolean varyok3 = false;

        for (char c : kelime.toCharArray()) {
            if (kalinharfler.indexOf(c) != -1) {
                varyok1 = true;
            }
            if (inceharfler.indexOf(c) != -1) {
                varyok2 = true;
            }
            if (rakamlar.indexOf(c) != -1) {
                varyok3 = true;
            }
        }
        if ((varyok1 != varyok2) && !varyok3) {
            sonuc = true;
        }
        return sonuc;
    }
    
    String[] kelimeler;
    String output="";
    public String process(String input) {
        kelimeler=input.split("\\p{Space}|\\p{Punct}");
        for (String s:kelimeler) {
            if (kelimekontrol(s)) {
                output=output+s+" ";
            }
        }
        return output;
    }
    public String description(String source) {
        return source+" daki büyük ünlü uyumuna uymayan kelimeler çýkarýldý.";
    }
    public String author() {
        return "Tuðba Ay";
    }
}
