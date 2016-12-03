package text;

public class OmerYazici implements Processor {
    public String process(String input) {
        String str = input.substring(0,1).toUpperCase()+input.substring(1);
        return str;
    }
    public String description(String source) {
        return source+" first character to uppercase ";
    }
    public String author() {
        return "Ömer Yazýcý";
    }
}
