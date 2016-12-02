package text;

public class EbrarSahin implements Processor{
    @Override
    public String process(String input) {
        String temp="";
        for (int i = input.length()-1; i >= 0; i--) {
            temp+=input.charAt(i);
        }
        return temp;
    }

    @Override
    public String description(String source) {
        return source+ " kelimesi tersine çevrildi.";
    }

    @Override
    public String author() {
        return "Ebrar Sahin";
    }
    public static void main(String[] args) {
        EbrarSahin es = new EbrarSahin();
        System.out.println(es.process("Ebrar"));
    }
}
