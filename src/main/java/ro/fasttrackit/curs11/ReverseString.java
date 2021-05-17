package ro.fasttrackit.curs11;

public class ReverseString {
    public String reverse(String word) {
        if (word == null) {
            return null;
        }

        String result = "";
        for (int i = word.length() - 1; i >= 0; i--) {
            result += word.charAt(i);
        }
        return result;
    }
}
