import java.util.*;

public class PermutationChecker {

    public static void main(String args[]) {
        System.out.println(isPermutation("abc", "bca"));
    }

    public static boolean isPermutation(String firstInput, String secondInput) {
        if (firstInput.length() != secondInput.length()) {
            return false; // if lengths dont match they cant be permutations
        }

        int[] chars = new int[256];
        for (int i = 0; i < firstInput.length(); i++) {
            int index = (int)firstInput.charAt(i);
            chars[index]++;  // Count number of characters seen in first input
        }

        for (int i = 0; i < secondInput.length(); i++) {
            int index = (int)secondInput.charAt(i);
            chars[index]--; // Subtract when matching character is found
            if (chars[index] < 0) {
                return false; // odd number of characters will result in -1
            }
        }

        return true;
    }
}
