import java.util.*;

public class IntegerSum {

    public static int digitsManipulations(int n) {
        if (n == 0) {
            return 0;
        }

        String stringValue = String.valueOf(n);
        int product = 1;
        int sum = 0;
        for (int i = 0; i < stringValue.length(); i++) {
            product *= Character.getNumericValue(stringValue.charAt(i));
            sum += Character.getNumericValue(stringValue.charAt(i));
        }

        return product - sum;
    }

    public static int binaryPatternMatching(String pattern, String s) {
        List<Character> vowels = new ArrayList(){};
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('y');

        int patternLength = pattern.length();
        int result = 0;
        for (int i = 0; i <= s.length() - patternLength; i++) {
            // First character is a vowel
            char currentChar = s.charAt(i);
            if (pattern.charAt(0) ==  '0') {
                if (vowels.contains(currentChar)) { // start the window and search for a match
                    boolean foundMatch = true;
                    int windowStart = i;
                    int windowEnd = i + patternLength;
                    int patternIndex = 0;
                    while(windowStart < windowEnd) {
                        if (pattern.charAt(patternIndex) == '0' && !vowels.contains(s.charAt(windowStart))) {
                            foundMatch = false;
                            break; // no need to continue since we did not find a match
                        }
                        if (pattern.charAt(patternIndex) == '1' && vowels.contains(s.charAt(windowStart))) {
                            foundMatch = false;
                            break; // no need to continue since we did not find a match
                        }

                        windowStart++;
                        patternIndex++;

                    }

                    if (foundMatch) {
                        result++;
                    }
                }
            } else { // Not a vowel
                if (!vowels.contains(currentChar)) { // start the window and search for a match
                    boolean foundMatch = true;
                    int windowStart = i;
                    int windowEnd = i + patternLength;
                    int patternIndex = 0;

                    while(windowStart < windowEnd) {
                        if (pattern.charAt(patternIndex) == '0' && !vowels.contains(s.charAt(windowStart))) {
                            foundMatch = false;
                            break; // no need to continue since we did not find a match
                        }
                        if (pattern.charAt(patternIndex) == '1' && vowels.contains(s.charAt(windowStart))) {
                            foundMatch = false;
                            break; // no need to continue since we did not find a match
                        }

                        windowStart++;
                        patternIndex++;
                    }

                    if (foundMatch) {
                        result++;
                    }
                }
            }
        }

        return result;

    }

    public static boolean constructorNames(String className, String methodName) {
        if (className.length() != methodName.length()) {
            return false;
        }
        Map<Character, Integer> classNameCharacterCount = new HashMap<>();
        Map<Character, Integer> methodNameCharacterCount = new HashMap<>();

        for (int i = 0; i < className.length(); i++) {
            char classNameChar = className.charAt(i);
            if (classNameCharacterCount.containsKey(className.charAt(i))) {
                int count = classNameCharacterCount.get(classNameChar);
                count ++;
                classNameCharacterCount.put(classNameChar, count);
            } else {
                classNameCharacterCount.put(classNameChar, 1);
            }
        }

        for (int i = 0; i < methodName.length(); i++) {
            char methodNameChar = methodName.charAt(i);
            if (methodNameCharacterCount.containsKey(methodName.charAt(i))) {
                int count = methodNameCharacterCount.get(methodNameChar);
                count ++;
                methodNameCharacterCount.put(methodNameChar, count);
            } else {
                methodNameCharacterCount.put(methodNameChar, 1);
            }
        }

        boolean result = true;
        for (Character key: classNameCharacterCount.keySet()) {
            if (!methodNameCharacterCount.containsKey(key)) {
                result = false;
            }
        }

        if (result) {
            for (Character key: methodNameCharacterCount.keySet()) {
                if (!classNameCharacterCount.containsKey(key)) {
                    result = false;
                }
            }
        }
        List<Integer> methodNameValues = new ArrayList<Integer>(methodNameCharacterCount.values());
        List<Integer> classNameValues = new ArrayList<Integer>(classNameCharacterCount.values());

        for (int i = 0; i < classNameValues.size() - 1; i++) {
            for (int j = 0; j < classNameValues.size() - 1; j++) {
                Collections.swap(methodNameValues, i, j);
                if (methodNameValues.equals(classNameValues)) {
                    return true;
                }
            }
        }

        return result;

    }

    public static int[] coolFeature(int[] a, int[] b, int[][] query) {
//        If the query is of the form [0, i, x], then b[i] should be assigned the value of x.
//        If the query is of the form [1, x], then find the total number of pairs of indices i and j such that a[i] + b[j] = x.

        int[] result = new int[0];
        if (query.length == 3) {
            for (int i = 0; i < query[1].length; i++) {

            }
        } else {
            int countPairs = 0;
            for (int i = 0; i < a.length; i++) {
                for(int j = 0; j < b.length; j++) {
                    if (a[i] + b[j] == query[0][1]) {
                        countPairs++;
                    }
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
//        System.out.println(IntegerSum.digitsManipulations(123456)); // 720
//        System.out.println(IntegerSum.binaryPatternMatching("010", "amazing")); // 2
//        System.out.println(IntegerSum.constructorNames("abbzccc", "babzzcz"));
//        System.out.println(IntegerSum.constructorNames("abcbdb", "bbbcca")); //false
//        System.out.println(IntegerSum.constructorNames("aaa", "aaaa")); // false
//        System.out.println(IntegerSum.constructorNames("bbaaa", "aaabbb"));
//        System.out.println(IntegerSum.constructorNames("vvzavzza", "bbvvvzzb"));
//        System.out.println(IntegerSum.constructorNames("zxxxzzmm", "mmmxxxzzz")); // false

        int[] a = new int[]{1, 2, 3}, b = new int[]{3, 4};
        int[][] query = new int[][]{{1, 5}, {0, 0, 1}, {1, 5}};

        System.out.println(IntegerSum.coolFeature(a, b, query)); // [2, 1]
    }

}
