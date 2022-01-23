import java.io.*;
import java.util.*;

public class DiagonalTrav{

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner scn = new Scanner(System.in);
        int[][] arr = new int[3][4];
        for(int i = 0; i  < arr.length; i ++) {
            for(int j = 0; j < arr[0].length; j ++) {
                arr[i][j] = scn.nextInt();
            }
        }
        dt(arr);
    }

    public static void dt(int[][] arr) {
        int c = 0;
        for(int r = 0; r < arr.length; r ++) {
            int tr = r, tc = c;
            while(tr >= 0 && tc < arr[0].length) {
                System.out.print(arr[tr][tc] + " ");
                tr --;
                tc ++;
            }
            System.out.println();
        }
        
        for(int col = 1; col < arr[0].length; col ++) {
            int tr = arr.length - 1, tc = col;
            while(tc < arr[0].length && tr >= 0) {
                System.out.print(arr[tr][tc] + " ");
                tr --;
                tc ++;
            }
            System.out.println();
        }
    } 
}