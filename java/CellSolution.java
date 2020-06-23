import java.util.*;
public class CellSolution
{
    public List<Integer> cellCompete(int[] states, int days)
    {
        List<Integer> solution = new ArrayList<>();
        List<Integer> nextDay = new ArrayList<>();
        for(int i = 0; i < states.length; i++) {
            solution.add(states[i]);
            nextDay.add(states[i]);
        }

        for (int i = 0; i < days; i++) {
            for (int j = 0; j < states.length; j++) {
                // If both sides are active or inactive,
                // this cell becomes inactive
                if (j == 0) {
                    // First in list so only check right side
                    if (solution.get(j+1) == 0) {
                        nextDay.set(j, 0);
                    } else {
                        nextDay.set(j, 1);
                    }
                }

                else if (j == states.length - 1) {
                    // Last in list so only check left side
                    if (solution.get(j-1) == 0) {
                        nextDay.set(j, 0);
                    } else {
                        nextDay.set(j, 1);
                    }
                } else {
                    if (solution.get(j-1) == 0 && solution.get(j+1) == 0) {
                        nextDay.set(j, 0);
                    } else if (solution.get(j-1) == 1 && solution.get(j+1) == 1) {
                        nextDay.set(j, 0);
                    } else {
                        nextDay.set(j, 1);
                    }
                }
            }
            solution = new ArrayList();
            solution.addAll(nextDay);
        }
        return solution;
    }

    public static void main (String[] args) {
        CellSolution solution = new CellSolution();
        List<Integer> result = solution.cellCompete(new int[]{0,1,0,0,1,0,1,0}, 2);
        result.stream().forEach(System.out::print);
    }
}