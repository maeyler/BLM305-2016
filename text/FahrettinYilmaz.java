package text;

public class FahrettinYilmaz implements Processor {

    String[] arrWords;  
    String output = "";
	int counter =0;
    public String process(String input) {  // Palindrome kelimeleri bulan metod.
        arrWords = input.split("\\p{Space}|\\p{Punct}");

        for (String s : arrWords) {
            if (isTextPalindrome(s)) {
                System.out.println(s);
                output = output + s + " ";
counter++;
            }
        }

        return output;
    }

    public String description(String source) {
        return source + " Found "+counter+" palindrome words.";
    }

    public String author() {
        return "Fahretti Yýlmaz";
    }

    public static boolean isTextPalindrome(String text) {
        if (text == null) {
            return false;
        }
        int left = 0;
        int right = text.length() - 1;
        while (left < right) {
            if (text.charAt(left++) != text.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPhrasePalindrome(String text) {
        String chars = text.replaceAll("[^a-zA-Z]", "").toLowerCase();
        return isTextPalindrome(chars);
    }

}
