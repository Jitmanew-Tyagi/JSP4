import java.io.*;
import java.util.*;

public class SubsetsOfArrays{

public static void main(String[] args) throws Exception {
    // write your code here
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int[] arr = new int[n];
    for(int i = 0; i < n; i ++) {
        arr[i] = scn.nextInt();
    }
    int tans = (int)Math.pow(2, n);
    for(int i = 0; i < tans; i ++) {
        int temp = i;
        String ans = "";
        for(int j = n - 1; j >= 0; j --) {
            int dec = temp % 2;
            ans = dec == 0 ? "-\t" + ans : arr[j] + "\t" + ans;
            temp /= 2;
        }
        System.out.println(ans);
    } //0 -> (2^n) - 1
    
 }

}