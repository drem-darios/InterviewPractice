import java.util.*;

public class PermutationChecker {

    public static void main(String args[]) {
        System.out.println(isPermutation("abc", "bca"));
    }

    public static boolean isPermutation(String firstInput, String secondInput) {
        if (firstInput.length() != secondInput.length()) {
            return false;
        }

        int[] chars = new int[256];
        for (int i = 0; i < firstInput.length(); i++) {
            int index = (int)firstInput.charAt(i);
            chars[index]++;
        }

        for (int i = 0; i < secondInput.length(); i++) {
            int index = (int)secondInput.charAt(i);
            chars[index]--;
            if (chars[index] < 0) {
                return false;
            }
        }

        return true;
    }

    private static String sortChars(String input) {
        char[] inputChars = input.toCharArray();
        Arrays.sort(inputChars);
        return new String(inputChars);
    }
}
