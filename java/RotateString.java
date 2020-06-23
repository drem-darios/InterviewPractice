import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

public class RotateString {
    private static int[] result;
    private static int[] input = {1,2,3,4,5,6};
    public static void main(String[] args) {
        System.out.print("Original: ");
        for (int i : input) {
            System.out.print(i + " ");
        }
        System.out.println();
        result = input;
        rotate(3);
        System.out.print("Result: ");
        for(int i : result) {
            System.out.print(i + " " );
        }
    }

    public static void swap(int i, int j) {
        int temp = result[i];
        result[i] = result[j];
        result[j] = temp;
    }

    public static void rotate(int k) {
        reverse(0, k-1);
        reverse(k, result.length-1);
        reverse(0, result.length-1);
    }

    public static void reverse(int start, int end) {
        int mid = (start + end)/2;

        for (int i = start; i <= mid; i++) {
            System.out.println("Swapping: " + i + " " + end);
            swap(i, (start+end)-i);
        }
    }
}