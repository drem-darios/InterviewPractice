public class SortedMerge {

    public static void main(String args[]) {
        int[] inputA = {9,25,57,77,88,99,0,0,0,0,0,0}; // inputA has enough space for inputB
        int[] inputB = {10,24,55,78,89,98};
        sortedMerge(inputA, inputB);
        for (int i = 0; i < inputA.length; i++) {
            System.out.print(inputA[i] + " ");
        }
    }

    public static void sortedMerge(int[] inputA, int[] inputB) {
        int mid = inputA.length - 1;
        for (int i = inputB.length - 1; i >= 0; i--) {
            inputA[mid] = inputB[i]; // Fill inputA with inputB
            mid--; // Move towards the midpoint
        }
        sort(inputA, 0, mid+1, inputA.length - 1);
    }

    private static void sort(int[] input, int low, int mid, int high) {
        int current = 0;
        int midIndex = mid;
        int[] result = new int[input.length]; // O(n) memory

        while (low <= midIndex && mid <= high) {
            if (input[low] > input[mid]) {
                result[current] = input[mid];
                mid++;
            } else {
                result[current] = input[low];
                low++;
            }

            current++;
        }

        while (low < midIndex) { // Fill in the result with the rest of low end
            result[current] = input[low];
            low++;
            current++;
        }

        while(mid <= high) {  // Fil in the result with the rest of high end
            result[current] = input[mid];
            mid++;
            current++;
        }

        for (int i = 0; i < input.length; i++) {  // Put results back into original array
            input[i] = result[i];
        }
    }
}
