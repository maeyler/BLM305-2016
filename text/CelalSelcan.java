package text;

public class CelalSelcan implements Processor {
    public String process(String input) {
        String[] arr=input.split(" ");
        String returnval="";
        for(int i=0;i<arr.length;i++){
            String firstchar=arr[i].substring(0,1);
            if(i==arr.length-1){
                returnval+=firstchar.toUpperCase()+arr[i].substring(1,arr[i].length());
            }else{
                returnval+=firstchar.toUpperCase()+arr[i].substring(1,arr[i].length())+" ";
            }
        }
        return returnval;
    }
    public String description(String source) {
        return source+" converted to upper first character of words";
    }
    public String author() {
        return "Celal SELCAN";
    }
}
