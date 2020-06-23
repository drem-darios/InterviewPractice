import java.util.*;
import java.lang.*;

/**
 * Attempt at knapsack problem on codechef
 */
class Knapsack
{
    public static void main (String[] args) throws java.lang.Exception
    {
        Scanner scan = new Scanner(System.in);
        int numItems = scan.nextInt();
        List<Item> items = new ArrayList();

        for (int i = 0; i < numItems; i++) {
            int weight = scan.nextInt();
            int cost = scan.nextInt();

            Item newItem = new Item(weight, cost);
            items.add(newItem);
        }
        int maxCapacity = 9;

        Knapsack.printKnapsackMostExpensiveCost(items, maxCapacity);
    }

    public static void printKnapsackMostExpensiveCost(List<Item> items, int maxCapacity) throws Exception {
        // Set at index i is the Set of items whose cost is at most i;
        int[][] solution = new int[items.size() + 1][maxCapacity + 1];

        for (int row = 0; row < maxCapacity + 1; row++) {
            solution[0][row] = 0;
        }
        for (int column = 0; column < items.size() + 1; column++) {
            solution[column][0] = 0;
        }

        for (int itemIndex = 1; itemIndex <= items.size(); itemIndex++) {
            for (int capacity = 1; capacity <= maxCapacity; capacity++) {
                int maxCostWithoutCurrent = solution[itemIndex - 1][capacity];
                int maxCostWithCurrent = 0;
                Item currentItem = items.get(itemIndex - 1);
                int weightOfCurrent = currentItem.getWeight();

                if (capacity >= weightOfCurrent) { // We check if the knapsack can fit the current item
                    maxCostWithCurrent = currentItem.getCost(); // If so, maxValWithCurr is at least the value of the current item

                    int remainingCapacity = capacity - weightOfCurrent; // remainingCapacity must be at least 0
                    maxCostWithCurrent += solution[itemIndex - 1][remainingCapacity]; // Add the maximum value obtainable with the remaining capacity
                }

                solution[itemIndex][capacity] = Math.max(maxCostWithCurrent, maxCostWithoutCurrent);

            }
        }

        for (int i = 1; i <= maxCapacity; i++) {
            System.out.print(solution[items.size()][i] + " ");
        }

    }

    private static int getMaxCost(Set<Item> items) {
        int cost = 0;
        Iterator<Item> itemIterator = items.iterator();
        while(itemIterator.hasNext()) {
            cost += itemIterator.next().getCost();
        }

        return cost;
    }

    static class Item {
        public static final int MAX_WEIGHT = 2;
        public static final int MAX_COST = (int)Math.pow(10, 9);

        private int weight;
        private int cost;

        public Item(int weight, int cost) throws Exception {
            if (weight > MAX_WEIGHT) {
                throw new Exception("Weight cannot exceed " + MAX_WEIGHT);
            }

            if (cost > MAX_COST) {
                throw new Exception("Cost cannot exceed "  + MAX_COST);
            }

            this.weight = weight;
            this.cost = cost;
        }

        public int getWeight() {
            return weight;
        }

        public int getCost() {
            return cost;
        }
    }
}
