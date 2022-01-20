import java.io.*;
import java.util.*;

public class BarChart{

public static void main(String[] args) throws Exception {
    // write your code here
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int[] arr = new int[n];
    int max = 0;
    for(int i = 0; i < n; i ++) {
        arr[i] = scn.nextInt();
        max = Math.max(max, arr[i]);
    }
    for(int i = max; i >= 1; i --) {
        for(int b : arr) {
            if(b >= i)System.out.print("*\t");
            else System.out.print("\t");
        }
        System.out.println();
    }
 }

}