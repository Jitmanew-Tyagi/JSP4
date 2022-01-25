import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] arr = new int[n][m];
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < m; j ++) {
                arr[i][j] = scn.nextInt();
            }
        }
        int shell = scn.nextInt();
        int rot = scn.nextInt();
        rotate(arr, shell, rot);
    }
    
    public static void rotate(int[][] arr, int shell, int rot) {
        int minr = shell - 1, minc = shell - 1, maxr = arr.length - shell, maxc = arr[0].length - shell;  
        int size = 2 * (maxr - minr) + 2 * (maxc - minc);
        int[] oned = new int[size];
        //Filling oned
        int idx = 0;
        for(int i = minr; i <= maxr; i ++) oned[idx ++] = arr[i][minc];
        minc ++;
        for(int i = minc; i <= maxc; i ++) oned[idx ++] = arr[maxr][i];
        maxr --;
        for(int i = maxr; i >= minr; i --) oned[idx++] = arr[i][maxc];
        maxc --;
        for(int i = maxc; i >= minc; i --) oned[idx++] = arr[minr][i];
        minr ++;
        rotateOneD(oned, rot);
        minr = shell - 1; minc = shell - 1; maxr = arr.length - shell; maxc = arr[0].length - shell;
        idx = 0;
        for(int i = minr; i <= maxr; i ++) arr[i][minc] = oned[idx ++];
        minc ++;
        for(int i = minc; i <= maxc; i ++) arr[maxr][i] = oned[idx ++];
        maxr --;
        for(int i = maxr; i >= minr; i --) arr[i][maxc] = oned[idx++];
        maxc --;
        for(int i = maxc; i >= minc; i --) arr[minr][i] = oned[idx++];
        minr ++;
        display(arr);
    }
    
    public static void rotateOneD(int[] a, int k){
    // write your code here
    int n = a.length;
    k %= n;
    k = k < 0 ? k + n : k;
    reverse(a, 0, n - k - 1);
    reverse(a, n - k, n - 1);
    reverse(a, 0, n - 1);
  }
  
  public static void reverse(int[] a, int l, int r) {
      while(l < r) {
          int temp = a[l];
          a[l] = a[r];
          a[r] = temp; 
          l ++; r --;
      }
  }

    public static void display(int[][] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[0].length; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

}