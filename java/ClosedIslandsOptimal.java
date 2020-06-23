import java.util.*;

// Weekly Contest 162
// https://leetcode.com/contest/weekly-contest-162/
public class ClosedIslandsOptimal {

    public int closedIsland(int[][] grid) {
        int count = 0;
        for (int r = 0 ; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 0 && dfs(grid, r, c)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean dfs(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || c >= grid[0].length || r >= grid.length) {
            return false;
        }
        if (grid[r][c] == 1) {
            return true;
        }
        grid[r][c] = 1;
        return dfs(grid, r+1,c) &
                dfs(grid, r-1,c) &
                dfs(grid, r,c+1) &
                dfs(grid, r,c-1);
    }

    public static void main(String[] args) {
        ClosedIslandsOptimal closedIslands = new ClosedIslandsOptimal();
//        int[][] input = new int[][]{{0,0,1,0,0},{0,1,0,1,0},{0,1,1,1,0}}; // should be 1
//        int[][] input = new int[][]{{0,1,1,1,0},{1,0,1,0,1},{1,0,1,0,1},{1,0,0,0,1},{0,1,1,1,0}}; // should be 1
        int[][] input = new int[][]{{0,0,1,1,0,1,0,0,1,0},{1,1,0,1,1,0,1,1,1,0},{1,0,1,1,1,0,0,1,1,0},{0,1,1,0,0,0,0,1,0,1},{0,0,0,0,0,0,1,1,1,0},{0,1,0,1,0,1,0,1,1,1},{1,0,1,0,1,1,0,0,0,1},{1,1,1,1,1,1,0,0,0,0},{1,1,1,0,0,1,0,1,0,1},{1,1,1,0,1,1,0,1,1,0}}; //Should be 5
//        int[][] input = new int[][]{{1,1,1,1,1,1,1,0},{1,0,0,0,0,1,1,0},{1,0,1,0,1,1,1,0},{1,0,0,0,0,1,0,1},{1,1,1,1,1,1,1,0}}; // should be 2
        System.out.println("Found " + closedIslands.closedIsland(input) + " closed islands");
    }
}
