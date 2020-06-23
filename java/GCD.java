import java.util.List;

// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
// CLASS BEGINS, THIS CLASS IS REQUIRED
class GCD
{
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public int generalizedGCD(int num, int[] arr)
    {
        if (arr.length == 0) {
            return 0;
        }

        if (arr.length == 1) {
            return arr[0];
        }
        int result = arr[0];
        for (int i = 1; i < num; i++) {
            result = gcd(arr[i], result);
        }

        return result;
    }

    public int gcd(int first, int second) {
        if (first <= 0)
            return second;
        if (second <= 0)
            return first;

        if (first == second)
            return first;

        if (first > second)
            return gcd(first - second, second);
        return gcd(first, second - first);
    }

//    public int gcd(int first, int second) {
//        if (second == 0)
//            return first;
//        return gcd(second, first % second);
//    }

    public static void main (String[] args) {
        GCD gcd = new GCD();
        int result = gcd.generalizedGCD(5, new int[]{10,20,30,40,50,60,70,80});
        System.out.println(result);
    }
}