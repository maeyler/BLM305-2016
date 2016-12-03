package text;

public class HavvagulOzturk implements Processor {
    
    public String process(String input) {
       char old= 'a';
       char newchar = 'e';
        String output = "";
       for(int i = 0; i < input.length(); i++){
           char ch = input.charAt(i);
           if(ch == old){
               output =  output + newchar;
           }else{
               output = output + ch;
           }
       }
       return output;
    }
    public String description(String source) {
        return source+" a harfi e harfi ile deðitirildi";
    }
    public String author() {
        return "Havvagul Ozturk";
    }
}