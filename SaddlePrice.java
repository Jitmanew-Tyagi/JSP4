import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = n;
        int[][] mat = new int[n][m];
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < m; j ++) {
                mat[i][j] = scn.nextInt();
            }
        }
        getAns(mat);
        
    }
    
    
    
    public static void getAns(int[][] mat) {
        int[] row = new int[mat.length];
        int[] col = new int[mat.length];
        for(int i = 0; i < mat.length; i ++) {
            int min = Integer.MAX_VALUE;
            for(int j = 0; j < mat.length; j ++) {
                min = Math.min(mat[i][j], min);
            }
            row[i] = min;
        }
        for(int i = 0; i < mat.length; i ++) {
            int min = Integer.MIN_VALUE;
            for(int j = 0; j < mat.length; j ++) {
                min = Math.max(mat[j][i], min);
            }
            col[i] = min;
        }
        for(int i = 0; i  < mat.length; i ++) {
            for(int j = 0; j < mat[0].length; j ++) {
                if(row[i] == mat[i][j] && col[j] == mat[i][j]) {
                    System.out.println(mat[i][j]);
                   return;
                }
            }
        }
        System.out.println("Invalid input");
    }
    


}