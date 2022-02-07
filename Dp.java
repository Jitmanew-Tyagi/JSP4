import java.io.*;
import java.util.*;

//746 --> Todo

public class Dp{

 // 1. Faith
 // 2. Recursive Tree --> ArrayList Vala 
 // 3. Recursive Code
 // 4. Memoization
 // 5. Observation 
 // 6. Tabulation  
 // 7. Optimaztion

    public static void display(int[] dp) {
        for(int i : dp) System.out.print(i + " ");
        System.out.println();
    }

    public static void display2D(int[][] dp) {
        for(int[] i : dp) {
            for(int j : i) System.out.print(j + "\t");
            System.out.println();
        }
        System.out.println();
    }

    public static int fibo(int n) {
        if(n <= 1) return n;
        return fibo(n - 1) + fibo(n - 2);
    }

    public static int fiboMem(int n, int[] dp) {
        if(n <= 1) return dp[n] = n;
        if(dp[n] != -1) return dp[n];
        return dp[n] =  fiboMem(n - 1, dp) + fiboMem(n - 2, dp);
    }
    
    public static int fiboTab(int N, int[] dp) {
        for(int n = 0; n <= N; n ++) {
            if(n <= 1) {
                dp[n] = n;
                continue;
            } 
            // if(dp[n] != -1) return dp[n];
            dp[n] = dp[n - 1] + dp[n - 2]; //fiboMem(n - 1, dp) + fiboMem(n - 2, dp);
        }
        return dp[N];
    }

    public static int mazePath(int sr, int sc, int er, int ec) {
        if(sr == er && sc == ec) return 1;
        int[][] dirn = {{0, 1}, {1, 0}, {1, 1}};
        int ans = 0;
        for(int[] dir : dirn) {
            int x = sr + dir[0];
            int y = sc + dir[1];
            if(x <= er && y <= ec) {
                ans += mazePath(x, y, er, ec);
            }
        }
        return ans;
    }
    public static int mazePathMemo(int sr, int sc, int er, int ec, int[][] dp) {
        if(sr == er && sc == ec) return dp[sr][sc] = 1;
        if(dp[sr][sc] != 0) return dp[sr][sc];
        int[][] dirn = {{0, 1}, {1, 0}, {1, 1}};
        int ans = 0;
        for(int[] dir : dirn) {
            int x = sr + dir[0];
            int y = sc + dir[1];
            if(x <= er && y <= ec) {
                ans += mazePathMemo(x, y, er, ec, dp);
            }
        }
        return dp[sr][sc] = ans;
    }

    public static int mazePathTab(int SR, int SC, int er, int ec, int[][] dp) {

        for(int sr = er; sr >= SR; sr --) {
            for(int sc = ec; sc >= SC; sc --) {
                if(sr == er && sc == ec)  {
                    dp[sr][sc] = 1;
                    continue;
                }
                
                // if(dp[sr][sc] != 0) return dp[sr][sc];
                int[][] dirn = {{0, 1}, {1, 0}, {1, 1}};
                int ans = 0;
                for(int[] dir : dirn) {
                    int x = sr + dir[0];
                    int y = sc + dir[1];
                    if(x <= er && y <= ec) {
                        ans += dp[x][y];
                    }
                }
                dp[sr][sc] = ans;
            }
        }
        return dp[SR][SC];
    }

    public static void main(String[] args) throws Exception {
        // write your code here
        int[][] dp = new int[7][7];
        // Arrays.fill(dp, -1);
        // System.out.println(fiboMem(7, dp));
        // System.out.println(fiboTab(7, dp));
        System.out.println(mazePathTab(0, 0, 6, 6, dp));
        display2D(dp);
    }
}