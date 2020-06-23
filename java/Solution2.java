// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
import java.util.ArrayList;
import java.util.List;
// CLASS BEGINS, THIS CLASS IS REQUIRED
public class Solution2
{
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    int minimumHours(int rows, int columns, List<List<Integer>> grid)
    {

        List<List<Integer>> solution = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<Integer> gridColumns = grid.get(i);
            List<Integer> solutionColumn = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                solutionColumn.add(gridColumns.get(j));
            }
            solution.add(i, solutionColumn);
        }

        int hours = 0;
        boolean allServersHaveFiles = doAllServersHaveFiles(solution);
        while(!allServersHaveFiles) {
            for (int i = 0; i < grid.size(); i++) {
                List<Integer> solutionColumn = solution.get(i);
                for (int j = 0; j < solutionColumn.size(); j++) {
                    // If no file, check if it needs a file
                    if (grid.get(i).get(j) == 0) {
                        // Check left side
                        if (j-1 > 0 && grid.get(i).get(j-1) == 1) {
                            solutionColumn.set(j, 1);
                        }

                        // Check right side
                        if (j+1 < grid.get(i).size() && grid.get(i).get(j+1) == 1) {
                            solutionColumn.set(j, 1);
                        }

                        // Check above
                        if (i-1 > 0 && grid.get(i-1).get(j) == 1) {
                            solutionColumn.set(j, 1);
                        }

                        // Check below
                        if (i+1 < grid.size() && grid.get(i+1).get(j) == 1) {
                            solutionColumn.set(j, 1);
                        }
                    }
                }
                solution.add(i, solutionColumn);
            }
            allServersHaveFiles = doAllServersHaveFiles(solution);
            grid = new ArrayList();
            grid.addAll(solution);
            hours = hours + 1;
        }

        return hours;
    }

    private boolean doAllServersHaveFiles(List<List<Integer>> solution) {
        boolean result = solution.stream()
                .allMatch((List<Integer> columnSolution) -> columnSolution.stream()
                        .allMatch(serverFileState-> serverFileState == 1));

        return result;
    }
}