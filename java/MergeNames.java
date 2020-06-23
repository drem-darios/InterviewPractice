import java.util.HashSet;
import java.util.Set;

/**
 * Merges the list of names into a set of unique names
 */
public class MergeNames {

    public static String[] uniqueNames(String[] names1, String[] names2) {
        Set<String> mergedNames = new HashSet();

        for (String name: names1) {
            mergedNames.add(name);
        }
        for (String name: names2) {
            mergedNames.add(name);
        }
        return mergedNames.toArray(new String[mergedNames.size()]);
    }

    public static void main(String[] args) {
        String[] names1 = new String[] {"Ava", "Emma", "Olivia"};
        String[] names2 = new String[] {"Olivia", "Sophia", "Emma"};
        System.out.println(String.join(", ", MergeNames.uniqueNames(names1, names2))); // should print Ava, Emma, Olivia, Sophia
    }
}