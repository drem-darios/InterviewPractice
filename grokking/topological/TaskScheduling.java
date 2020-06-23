package grokking.topological;

import java.util.*;

class TaskScheduling {
    public static boolean isSchedulingPossible(int tasks, int[][] prerequisites) {
        if (tasks <= 0)
            return false;

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

        List<Integer> topologicalSortedList = new ArrayList<>();
        // Sort
        while(!sources.isEmpty()) {
            Integer vertex = sources.poll();
            topologicalSortedList.add(vertex);

            if (adjacencyList.get(vertex) != null) {
                for (Integer child: adjacencyList.get(vertex)) {
                    inDegreeCount.put(child, inDegreeCount.get(child) - 1);
                    if (inDegreeCount.get(child) == 0) {
                        sources.add(child);
                    }
                }
            }
        }

        // Return false if sorted response is not equal to number of tasks (cycle)
        return topologicalSortedList.size() == tasks;
    }

    public static void main(String[] args) {

        boolean result = TaskScheduling.isSchedulingPossible(3, new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 } });
        System.out.println("Tasks execution possible: " + result); // true

        result = TaskScheduling.isSchedulingPossible(3,
                new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 }, new int[] { 2, 0 } });
        System.out.println("Tasks execution possible: " + result); // false

        result = TaskScheduling.isSchedulingPossible(6, new int[][] { new int[] { 2, 5 }, new int[] { 0, 5 },
                new int[] { 0, 4 }, new int[] { 1, 4 }, new int[] { 3, 2 }, new int[] { 1, 3 } });
        System.out.println("Tasks execution possible: " + result); // true
    }
}
