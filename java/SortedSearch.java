/**
 * Uses a binary search to see if a number is in a set of sorted numbers
 */
public class SortedSearch {
    public static int countNumbers(int[] sortedArray, int lessThan) {
        int count = 0;
        int left = 0;
        int right = sortedArray.length - 1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if (sortedArray[mid] < lessThan) {
                count = mid + 1;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }


        return count;
    }


    public static void main(String[] args) {
        System.out.println(SortedSearch.countNumbers(new int[] { 1, 3, 5, 7 }, 4));
    }
}