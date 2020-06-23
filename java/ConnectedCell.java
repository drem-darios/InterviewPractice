// Find largest region of connected 1s (DFS problem)

public class ConnectedCell {

    public static int getBiggestRegion(int[][] matrix) {
        int maxRegion = 0;

        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                int size = getRegionSize(matrix, row, column);
                maxRegion = Math.max(size, maxRegion);
            }
        }

        return maxRegion;
    }

    private static int getRegionSize(int[][] matrix, int row, int column) {
        // DFS
        if (row < 0 || row >= matrix.length || column < 0 || column >= matrix[row].length) {
            return 0;
        }

        if (matrix[row][column] == 0) {
            return 0;
        }
        // Destroy/reuse matrix. If not ok with this, we can just clone the matrix
        matrix[row][column] = 0; // Clear this out (flood fill)

        int size = 1; // count myself
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = column - 1; c <= column + 1; c++) {
                if (r != row || c != column) {
                    size += getRegionSize(matrix, r, c);
                }

            }
        }

        return size;
    }

    public static void main(String[] args) {
//        int[][] input = new int[][]{{0,0,1,1,0,1,0,0,1,0},{1,1,0,1,1,0,1,1,1,0},{1,0,1,1,1,0,0,1,1,0},{0,1,1,0,0,0,0,1,0,1},{0,0,0,0,0,0,1,1,1,0},{0,1,0,1,0,1,0,1,1,1},{1,0,1,0,1,1,0,0,0,1},{1,1,1,1,1,1,0,0,0,0},{1,1,1,0,0,1,0,1,0,1},{1,1,1,0,1,1,0,1,1,0}}; //Should be 5

        int[][] input = new int[][]{{0,0,0,1,1,0,0},{0,1,0,0,1,1,0},{1,1,0,1,0,0,1},{0,0,0,0,0,1,0},{1,1,0,0,0,0,0}, {0,0,0,1,0,0,0}}; // should be 2
        System.out.println("Found " + ConnectedCell.getBiggestRegion(input) + " as biggest region");
    }
}
