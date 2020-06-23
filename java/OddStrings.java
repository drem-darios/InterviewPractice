// Given an integer n,
// return a string with n characters such that each character in such string occurs an odd number of times.
//
public class OddStrings {

    public String generateTheString(int n) {
        StringBuilder s = new StringBuilder();

        if (n % 2 == 1) {
            for (int i = 0; i < n; i++) {
                s.append('a');
            }
        }

        else{
            for (int i = 0; i < n -1; i++) {
                s.append('a');
            }
            s.append('b');
        }

        return s.toString();
    }

    public static void main (String[] args) {
        OddStrings o = new OddStrings();
        System.out.println(o.generateTheString(7));
    }
}
