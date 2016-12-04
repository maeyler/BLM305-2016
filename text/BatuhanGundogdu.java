package text;

public class BatuhanGundogdu implements Processor {

  public String process(String input) {

    String[] st = input.split("\\p{Space}|\\p{Punct}");
    char[] ch;
    double avg = 0, i = 0, j=0, sum=0;

    for (String s : st) {
            i++;
            ch = s.toLowerCase().toCharArray();
            for (char c : ch) {
                j++;
            }
            sum = sum + (double)(ch.length);
            avg= (sum) / i;
        }
        return "'"+input+"'\n\n"+avg+" is the avarage length of the words in this text.";
  }

  public String description(String source) {
    return "Avarage length of the words in the "+source+".";
  }

  public String author() {
    return "Batuhan Gundogdu";
  }
}
