package grokking.topological;

import java.util.*;

class TopologicalSort {
    public static List<Integer> sort(int vertices, int[][] edges) {
        List<Integer> sortedOrder = new ArrayList<>();
        if (vertices <= 0)
            return sortedOrder;
        // Initialize graph from input and populate in-degree map

        // Store graph as adjacency list
        Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();
        Map<Integer, Integer> incomingEdgesCount = new HashMap<>();

        for (int[] edge : edges) {
            int startVertex = edge[0];
            int endVertex = edge[1];

            if (adjacencyList.get(startVertex) == null) {
                adjacencyList.put(startVertex, new HashSet<>());
            }

            adjacencyList.get(startVertex).add(endVertex);
            if (incomingEdgesCount.get(startVertex) == null) {
                incomingEdgesCount.put(startVertex, 0); // Initialize sources
            }

            if (incomingEdgesCount.get(endVertex) == null) {
                incomingEdgesCount.put(endVertex, 0);
            }
            // Increment the count of the incoming edge
            incomingEdgesCount.put(endVertex, incomingEdgesCount.get(endVertex) + 1);
        }

        //Find all sources
        Queue<Integer> sources = new LinkedList<>();
        for (Integer vertex: adjacencyList.keySet()) {
            Integer incomingEdgeCount = incomingEdgesCount.get(vertex);
            if (incomingEdgeCount == 0) {
                sources.add(vertex);
            }
        }

        // Sort
        while (!sources.isEmpty()) {
            Integer sourceVertex = sources.remove();
            sortedOrder.add(sourceVertex);

            Set<Integer> children = adjacencyList.get(sourceVertex);
            if (children != null) {
                for (Integer childVertex: children) {
                    Integer childIncomingEdgeCount = incomingEdgesCount.get(childVertex);
                    childIncomingEdgeCount -=1;
                    incomingEdgesCount.put(childVertex, childIncomingEdgeCount);
                    if (childIncomingEdgeCount == 0) {
                        sources.add(childVertex);
                    }
                }
            }

        }

        if (sortedOrder.size() != vertices) // topological sort is not possible as the graph has a cycle
            return new ArrayList<>();
        return sortedOrder;
    }

    public static void main(String[] args) {
        List<Integer> result = TopologicalSort.sort(4,
                new int[][] { new int[] { 3, 2 }, new int[] { 3, 0 }, new int[] { 2, 0 }, new int[] { 2, 1 } });
        System.out.println(result);

        result = TopologicalSort.sort(5, new int[][]{new int[]{4, 2}, new int[]{4, 3}, new int[]{2, 0},
                new int[]{2, 1}, new int[]{3, 1}});
        System.out.println(result);

        result = TopologicalSort.sort(7, new int[][] { new int[] { 6, 4 }, new int[] { 6, 2 }, new int[] { 5, 3 },
                new int[] { 5, 4 }, new int[] { 3, 0 }, new int[] { 3, 1 }, new int[] { 3, 2 }, new int[] { 4, 1 } });
        System.out.println(result);
    }
}