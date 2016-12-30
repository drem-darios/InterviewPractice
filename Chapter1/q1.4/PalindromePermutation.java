import java.util.*;

public class PalindromePermutation {

    public static void main(String args[]) {
            System.out.println(isPalindromePermutation("Tact Coa"));
    }

    public static boolean isPalindromePermutation(String input) {
            Map<Character, Integer> charCount = new HashMap<Character, Integer>();
            int oddCount = 0;
            for (int i = 0; i < input.length(); i++) {
                char c = Character.toLowerCase(input.charAt(i));
                if (c == ' ') {
                    continue; // igonre spaces
                }
                Integer count = charCount.get(c); // ignore case
                if (count == null) {
                    charCount.put(c, 1);
                    oddCount++;
                } else {
                    count++;
                    charCount.put(c, count);
                    if (count %2 == 0) {
                        oddCount--;
                    } else {
                        oddCount++;
                    }
                }
            }

            return oddCount <= 1;
    }
}
