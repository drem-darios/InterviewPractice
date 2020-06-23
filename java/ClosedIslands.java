import java.util.*;
// Weekly Contest 162
// https://leetcode.com/contest/weekly-contest-162/
public class ClosedIslands {

    public int closedIsland(int[][] grid) {
        if (grid == null) {
            return 0;
        }

        // Build collection of islands
        List<Set<Point>> islands = new ArrayList<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Point point = new Point(i, j, grid[i][j]);
                if (point.value == 0) { // possible island
                    List<Integer> islandIndex = getIslandIndex(grid, islands, point);
                    if (islandIndex.size() == 0) { // Not a part of an existing island
                        Set<Point> newIsland = new HashSet<>();
                        newIsland.add(point);
                        islands.add(newIsland);
                    } else if (islandIndex.size() == 1) { // Add point to existing island
                        islands.get(islandIndex.get(0)).add(point);
                    } else {
                        islands.get(islandIndex.get(0)).add(point);
                        // Merge both lists
                        for (int index = 1; index < islandIndex.size(); index++) {
                            int removeIndex = islandIndex.get(index);
                            islands.get(islandIndex.get(0)).addAll(islands.get(removeIndex));
                            islands.remove(removeIndex);
                        }
                    }
                }
            }
        }

        // Find closed islands in collection
        Collection<Set<Point>> closedIslands = getClosedIslands(islands, grid);
        return closedIslands.size();

    }

    private List<Integer> getIslandIndex(int[][] grid, List<Set<Point>> islands, Point point) {
        int leftIndex = point.x - 1;
        int rightIndex = point.x + 1;
        int aboveIndex = point.y - 1;
        int belowIndex = point.y + 1;
        Set<Integer> addedIndexes = new HashSet<>();

        for (int i = 0; i < islands.size(); i++) {
            if (leftIndex >= 0 && grid[leftIndex][point.y] == 0) {
                Point leftPoint = new Point(leftIndex, point.y, 0);
                if (islands.get(i).contains(leftPoint)) {
                    addedIndexes.add(i);
                }
            }

            if (rightIndex < grid.length && grid[rightIndex][point.y] == 0) {
                Point rightPoint = new Point(rightIndex, point.y, 0);
                if (islands.get(i).contains(rightPoint)) {
                    addedIndexes.add(i);
                }
            }

            if (aboveIndex >= 0 && grid[point.x][aboveIndex] == 0) {
                Point abovePoint = new Point(point.x, aboveIndex, 0);
                if (islands.get(i).contains(abovePoint)) {
                    addedIndexes.add(i);
                }
            }

            if (belowIndex < grid[0].length && grid[point.x][belowIndex] == 0) {
                Point belowPoint = new Point(belowIndex, point.y, 0);
                if (islands.get(i).contains(belowPoint)) {
                    addedIndexes.add(i);
                }
            }
        }
        return new ArrayList(addedIndexes);
    }

    private Collection<Set<Point>> getClosedIslands(Collection<Set<Point>> islands, int[][] grid) {

        Collection<Set<Point>> closedIslands = new ArrayList<>();
        for (Set<Point> island: islands) {
            boolean eligible = true;
            for (Point point : island) {
                int leftIndex = point.x - 1;
                int rightIndex = point.x + 1;
                int aboveIndex = point.y - 1;
                int belowIndex = point.y + 1;

                if (leftIndex < 0) {
                    eligible = false;
                    break;
                }

                if (rightIndex >= grid.length) {
                    eligible = false;
                    break;
                }

                if (aboveIndex < 0) {
                    eligible = false;
                    break;
                }

                if (belowIndex >= grid[0].length) {
                    eligible = false;
                    break;
                }
            }
            if (eligible) {
               closedIslands.add(island);
            }
        }

        return closedIslands;
    }

    private class Point {
        public int x;
        public int y;
        public int value;

        public Point(int x, int y, int value){
            this.x = x;
            this.y = y;
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            Point point = (Point)obj;
            return point.x == this.x && point.y == this.y && point.value == this.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, value);
        }
    }

    public static void main(String[] args) {
        ClosedIslands closedIslands = new ClosedIslands();
//        int[][] input = new int[][]{{0,0,1,0,0},{0,1,0,1,0},{0,1,1,1,0}}; // should be 1
//        int[][] input = new int[][]{{0,1,1,1,0},{1,0,1,0,1},{1,0,1,0,1},{1,0,0,0,1},{0,1,1,1,0}}; // should be 1
        int[][] input = new int[][]{{0,0,1,1,0,1,0,0,1,0},{1,1,0,1,1,0,1,1,1,0},{1,0,1,1,1,0,0,1,1,0},{0,1,1,0,0,0,0,1,0,1},{0,0,0,0,0,0,1,1,1,0},{0,1,0,1,0,1,0,1,1,1},{1,0,1,0,1,1,0,0,0,1},{1,1,1,1,1,1,0,0,0,0},{1,1,1,0,0,1,0,1,0,1},{1,1,1,0,1,1,0,1,1,0}}; //Should be 5

//        int[][] input = new int[][]{{1,1,1,1,1,1,1,0},{1,0,0,0,0,1,1,0},{1,0,1,0,1,1,1,0},{1,0,0,0,0,1,0,1},{1,1,1,1,1,1,1,0}}; // should be 2
        System.out.println("Found " + closedIslands.closedIsland(input) + " closed islands");
    }
}
