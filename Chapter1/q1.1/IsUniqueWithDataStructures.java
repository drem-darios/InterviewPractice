import java.util.*;

public class IsUniqueWithDataStructures
{
    public static void main(String[] args)
    {
            String input = args[0];
            System.out.println(input);
            System.out.println(hasUniqueChars(input));
    }

    public static boolean hasUniqueChars(String input) {
            Map<Character, Boolean> charMap = new HashMap<Character, Boolean>();
            for (int i = 0; i < input.length(); i++) {  // O(n)
                    char inputChar = input.charAt(i);
                    Boolean result = charMap.get(inputChar);
                    if (result != null && result) {
                            return false;
                    } else {
                            charMap.put(inputChar, true);
                    }
            }
            return true;
    }
}

