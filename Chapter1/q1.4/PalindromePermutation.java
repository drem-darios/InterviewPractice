import java.util.*;

public class PalindromePermutation {

    public static void main(String args[]) {
            System.out.println(isPalindromePermutation("Tact Coa"));
    }

    public static boolean isPalindromePermutation(String input) {
            Map<Character, Integer> charCount = new HashMap<Character, Integer>(); // Store count of characters
            int oddCount = 0;
            for (int i = 0; i < input.length(); i++) {
                char c = Character.toLowerCase(input.charAt(i)); // Ignore case
                if (c == ' ') {
                    continue; // igonre spaces
                }
                Integer count = charCount.get(c);
                if (count == null) {
                    charCount.put(c, 1);
                    oddCount++; // First time seeing this character means count is odd
                } else {
                    count++; // Increase times we have seen this character
                    charCount.put(c, count);
                    if (count %2 == 0) {
                        oddCount--; // if a pair is found, decrease the number of odds
                    } else {
                        oddCount++; // if pair is not found, increase number of odds
                    }
                }
            }

            return oddCount <= 1; // There can be at most 1 odd
    }
}
