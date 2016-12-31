public class URLify {

    public static void main(String args[]) {
            char[] input = "Mr John Smith    ".toCharArray();
            encode(input, 13);
            System.out.println(input);
    }

    public static void encode(char[] input, int trueLength) {
            int spaces = 0;
            for (int i = 0; i < trueLength; i++) {
                    if (input[i] == ' ') {
                            spaces++; // add to count of spaces in true length of input
                    }
            }

            int index = trueLength + (spaces * 2); // each space requires two additional spaces
            for (int i = trueLength - 1; i >= 0; i--) {
                    char c = input[i];
                    if (c == ' ') { // replace space with %20
                            index--;
                            input[index] = '0';
                            index--;
                            input[index] = '2';
                            index--;
                            input[index] = '%';
                    } else {
                            index--;
                            input[index] = c; // Write character back into array
                    }
            }
    }
}
