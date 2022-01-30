import java.io.*;
import java.util.*;

public class Main {
    public static Scanner scn = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        // write your code here
        int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i ++) arr[i] = scn.nextInt();
        int x = scn.nextInt();
        System.out.println(firstIndex(arr, 0, x));
    }

    public static int firstIndex(int[] arr, int idx, int x){
        if(idx == arr.length) return -1;
        int ans = firstIndex(arr, idx + 1, x);
        if(ans == -1) {
            if(arr[idx] == x) 
                return idx;
            else return -1;
        } 
        else 
            return ans;
    }

}