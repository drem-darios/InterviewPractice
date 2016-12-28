public class IsUniqueNoDataStructures
{
    public static void main(String[] args)
    {
            String input = args[0];
            System.out.println(input);
            System.out.println(hasUniqueChars(input));
    }

    public static boolean hasUniqueChars(String input) {
            for (int i = 0; i < input.length() - 1; i++) { // O(n^2)
                    char start = input.charAt(i);
                    for (int j = i+1; j < input.length(); j++) {
                            if (start == input.charAt(j)) {
                                    return false;
                            }
                    }
            }
            return true;
    }

}

