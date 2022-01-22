import java.io.*;
import java.util.*;

public class BS{

    public static void main(String[] args) throws Exception {
        // write your code here
        int[] arr = {1, 12, 13, 14, 17, 18, 22, 31, 44};
        int ele = 45;
        int ans = binarySearch(arr, ele);
        System.out.println(ans);
    }

    public static int binarySearch(int[] arr, int ele) {
        int l = 0, r = arr.length - 1;
        while(l <= r) {
            int mid = (l + r) / 2;
            if(arr[mid] == ele) return mid;
            else if(arr[mid] < ele) l = mid + 1;
            else r = mid - 1;
        }
        return -1;
    }
}