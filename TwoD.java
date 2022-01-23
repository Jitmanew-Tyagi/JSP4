import java.io.*;
import java.util.*;

public class TwoD{

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner scn = new Scanner(System.in);
        int[][] arr = new int[2][3];
        for(int i = 0; i  < arr.length; i ++) {
            for(int j = 0; j < arr[0].length; j ++) {
                arr[i][j] = scn.nextInt();
            }
        }
        wave(arr);
    }

    public static void wave(int[][] arr) {
        int c = 0;
        while(c < arr[0].length) {
            if(c % 2 == 0) {
                for(int r = 0; r < arr.length; r++) System.out.print(arr[r][c]+" ");
            } else {
                for(int r = arr.length - 1; r >= 0; r--) System.out.print(arr[r][c]+ " ");
            }
            System.out.println();
            c ++;
        }
    } 
}