import java.io.*;
import java.util.*;

public class SubArray{

public static void main(String[] args) throws Exception {
    // write your code here
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int[] arr = new int[n];
    for(int i = 0; i < n; i ++) {
        arr[i] = scn.nextInt();
    }
    for(int st = 0; st < n; st ++) {
        for(int end = st; end < n; end ++) {
            for(int tr = st; tr <= end; tr ++) {
                System.out.print(arr[tr] + "\t");
            }
            System.out.println();
        }
    }
 }

}