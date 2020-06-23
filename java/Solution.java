// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
import java.util.*;

// CLASS BEGINS, THIS CLASS IS REQUIRED
public class Solution
{
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    int numberAmazonTreasureTrucks(int rows, int column,
                                   List<List<Integer> > grid)
    {
        // Setup a map of row number to all contingent parks in that row
        Map<Integer, Set<Integer>> rowParks = new HashMap();
        for (int i = 0; i < rows; i++) {
            Set<Integer> parks = new HashSet<>();
            for (int j = 0; j < column; j++) {
                if (grid.get(i).get(j) == 1) {
                    parks.add(grid.get(i).get(j));
                }
            }
            // Stores all the contingent parks in a row
            rowParks.put(i, parks);
        }

        // Compare two rows to see if they have contingent parks

        for (int i = 0; i <= rows; i++) {
            Set<Integer> upperParks = rowParks.get(i);
            Set<Integer> lowerParks = rowParks.get(i+1);
            // Check if parks are adjacent
        }

        return 0;
    }
    // METHOD SIGNATURE ENDS
}