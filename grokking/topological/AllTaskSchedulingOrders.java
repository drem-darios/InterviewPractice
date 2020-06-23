package grokking.topological;

import java.util.*;

class AllTaskSchedulingOrders {
    public static void printOrders(int tasks, int[][] prerequisites) {
        if (tasks <= 0) {
            return;
        }
        // Initialize
        Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();
        Map<Integer, Integer> inDegreeCount = new HashMap<>();

        // Build graph
        for (int[] prerequisite: prerequisites) {
            int startVertex = prerequisite[0];
            int endVertex = prerequisite[1];
            if (adjacencyList.get(startVertex) == null) {
                adjacencyList.put(startVertex, new HashSet<>());
            }
            adjacencyList.get(startVertex).add(endVertex);
            if (inDegreeCount.get(startVertex) == null) {
                inDegreeCount.put(startVertex, 0);
            }
            if (inDegreeCount.get(endVertex) == null) {
                inDegreeCount.put(endVertex, 0);
            }

            inDegreeCount.put(endVertex, inDegreeCount.get(endVertex) + 1);
        }

        Queue<Integer> sources = new LinkedList<>();
        // Find sources
        for (Integer vertex: adjacencyList.keySet()) {
            if (inDegreeCount.get(vertex) == 0) {
                sources.add(vertex);
            }
        }
        List<Integer> sortedOrder = new ArrayList<>();
        printAllTopologicalSorts(adjacencyList, inDegreeCount, sources, sortedOrder);
    }

    public static void printAllTopologicalSorts(Map<Integer, Set<Integer>> adjacencyList, Map<Integer, Integer> inDegreeCount, Queue<Integer> sources, List<Integer> sortedOrder) {
        if (!sources.isEmpty()) {
            // Sort
            for (Integer vertex : sources) {
                sortedOrder.add(vertex);
                Queue<Integer> sourcesForNextCall = cloneQueue(sources);
                // only remove the current source, all other sources should remain in the queue for the next call
                sourcesForNextCall.remove(vertex);

                if (adjacencyList.get(vertex) != null) {
                    for (Integer child : adjacencyList.get(vertex)) {
                        inDegreeCount.put(child, inDegreeCount.get(child) - 1);
                        if (inDegreeCount.get(child) == 0) {
                            sourcesForNextCall.add(child);
                        }
                    }
                }

                // recursive call to print other orderings from the remaining (and new) sources
                printAllTopologicalSorts(adjacencyList, inDegreeCount, sourcesForNextCall, sortedOrder);

                // backtrack, remove the vertex from the sorted order and put all of its children back to consider
                // the next source instead of the current vertex
                sortedOrder.remove(vertex);
                if (adjacencyList.get(vertex) != null) {
                    for (int child : adjacencyList.get(vertex)) {
                        inDegreeCount.put(child, inDegreeCount.get(child) + 1);
                    }
                }

            }
        }

        // if sortedOrder doesn't contain all tasks, either we've a cyclic dependency between tasks, or
        // we have not processed all the tasks in this recursive call
        if (sortedOrder.size() == inDegreeCount.size()){
            System.out.println(sortedOrder);
        }
    }

    // makes a deep copy of the queue
    private static Queue<Integer> cloneQueue(Queue<Integer> queue) {
        Queue<Integer> clone = new LinkedList<>();
        for (Integer num : queue)
            clone.add(num);
        return clone;
    }

    public static void main(String[] args) {

        AllTaskSchedulingOrders.printOrders(3, new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 } });
        System.out.println();

        AllTaskSchedulingOrders.printOrders(4,
                new int[][] { new int[] { 3, 2 }, new int[] { 3, 0 }, new int[] { 2, 0 }, new int[] { 2, 1 } });
        System.out.println();

        AllTaskSchedulingOrders.printOrders(6, new int[][] { new int[] { 2, 5 }, new int[] { 0, 5 }, new int[] { 0, 4 },
                new int[] { 1, 4 }, new int[] { 3, 2 }, new int[] { 1, 3 } });
        System.out.println();
    }
}
