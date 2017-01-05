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
        int mid = inputA.length - inputB.length;
        int currentB = inputB.length - 1;
        int currentA = mid - 1;

        for (int i = inputA.length - 1; i > 0; i--) {
            if (inputB[currentB] > inputA[currentA]) {
                inputA[i] = inputB[currentB];
                currentB--;
            } else {
                inputA[i] = inputA[currentA];
                currentA--;
            }
        }
    }
}
