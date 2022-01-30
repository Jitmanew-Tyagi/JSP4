import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i ++) arr[i] = scn.nextInt();
        int ele = scn.nextInt();
        System.out.println(firstIndex(arr, 0, ele));
    }

    public static int firstIndex(int[] arr, int idx, int x){
       //base
       if(idx == arr.length) return -1;
       if(arr[idx] == x) return idx;
       int faith = firstIndex(arr, idx + 1, x);
       return faith;
    }

}