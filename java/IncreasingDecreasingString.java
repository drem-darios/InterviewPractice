import java.util.*;
import java.util.stream.Collectors;

public class IncreasingDecreasingString {

    public String sortString(String s) {
        // Convert String to Character List
        List<Character> characters = s.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        // Sort the input O(logN)
        Collections.sort(characters);
        StringBuilder result = new StringBuilder();

        // Iterator over entire input array
        while(!characters.isEmpty()) {
            Set<Character> seenChars = new HashSet();
            // Pick the smallest character from s and append it to the result
            char smallestChar = characters.remove(0);
            result.append(smallestChar);
            seenChars.add(smallestChar);

            Iterator<Character> sortedIterator = characters.iterator();
            while (sortedIterator.hasNext()) {
                char c = sortedIterator.next();
                if (!seenChars.contains(c)) {
                    result.append(c);
                    seenChars.add(c);
                    // Remove this character from input
                    sortedIterator.remove();
                }
            }
            if (result.length() == s.length()) {
                break;
            }

            seenChars = new HashSet();
            char largest = characters.remove(characters.size() - 1);
            result.append(largest);
            seenChars.add(largest);

            // Pick the largest character from s and append it to the result
            ListIterator<Character> reverseIterator = characters.listIterator(characters.size());
            while (reverseIterator.hasPrevious()) {
                char c = reverseIterator.previous();
                if (!seenChars.contains(c)) {
                    result.append(c);
                    seenChars.add(c);
                    reverseIterator.remove();
                }
            }
            if (result.length() == s.length()) {
                break;
            }
        }

        return result.toString();
    }


    public static void main(String[] args) {
        IncreasingDecreasingString increasingDecreasingString = new IncreasingDecreasingString();
        System.out.println(increasingDecreasingString.sortString("rat")); // art
        System.out.println(increasingDecreasingString.sortString("leetcode")); // cdelotee
        System.out.println(increasingDecreasingString.sortString("aaaabbbbcccc")); // abccbaabccba
    }

}
