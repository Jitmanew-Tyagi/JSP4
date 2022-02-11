import java.io.*;
import java.util.*;

//746 --> Todo

public class Dp{

 // 1. Faith
 // 2. Recursive Tree
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


    public int memo(String s, HashMap<String, Integer> dp) {
            if(s.length() == 0) {
                dp.put(s, 1);
                return 1;
            }
            if(dp.containsKey(s)) return dp.get(s);
            int n = s.charAt(0) - '0';
            if(n == 0) {
                dp.put(s, 0);
                return 0;
            }
            int ans = 0;
            ans += memo(s.substring(1), dp);
            if(s.length() > 1) {
                n = (n * 10) + (s.charAt(1) - '0');
                if(n <= 26) {
                    ans += memo(s.substring(2), dp);
                }
            }
            dp.put(s, ans);
            return ans;
        }
        
        public int arrRec(String s, int idx, int[] dp) {
            if(idx == s.length()) return dp[idx] = 1;
            int n = s.charAt(idx) - '0';
            if(dp[idx] != -1) return dp[idx];
            if(n == 0) return dp[idx] = 0;
            int ans = 0;
            ans += arrRec(s, idx + 1, dp);
            if(idx < s.length() - 1) {
                n = n * 10 + (s.charAt(idx + 1) - '0');
                if(n <= 26){
                    ans += arrRec(s, idx + 2, dp);
                }
            }
            return dp[idx] = ans;
        } 
        
         public int arrRecTab(String s, int IDX, int[] dp) {
            
            for(int idx = s.length(); idx >= IDX; idx --) {
                if(idx == s.length()){
                    dp[idx] = 1;
                    continue;
                }
                int n = s.charAt(idx) - '0';
                // if(dp[idx] != -1) return dp[idx];
                if(n == 0) {
                    dp[idx] = 0;
                    continue;
                }
                int ans = 0;
                ans += dp[idx + 1];// arrRec(s, idx + 1, dp);
                if(idx < s.length() - 1) {
                   n = n * 10 + (s.charAt(idx + 1) - '0');
                   if(n <= 26){
                       ans += dp[idx + 2];//arrRec(s, idx + 2, dp);
                   }
                }
                dp[idx] = ans;
            }
            return dp[IDX];
        }


        
        public static int numDecodings(String s) {
            long[] dp = new long[s.length() + 1];
            Arrays.fill(dp, -1);
            return (int)rec(s, 0, dp);
        }
        
        public static long rec(String s, int idx, long[] dp) {
            if(idx == s.length()) {
                return dp[idx] = 1;
            }
            if(s.charAt(idx) == '0') {
                return dp[idx] = 0;
            }
            if(dp[idx] != -1) return dp[idx];
            long ans = 0;
            char a = s.charAt(idx);
            if(a == '*'){
                ans += 9 * rec(s, idx + 1, dp); //single digit call
                if(idx < s.length() - 1) {
                    char b = s.charAt(idx + 1);
                    if(b >= '0' && b <= '6') {
                        ans += 2 * rec(s, idx + 2, dp);
                    } else if(b >= '7' && b <= '9') {
                        ans += 1 * rec(s, idx + 2, dp);
                    } else {
                        ans += 15 * rec(s, idx + 2, dp);
                    }
                }
            } else {
                ans += rec(s, idx + 1, dp);
                if(idx < s.length() - 1) {
                    char b = s.charAt(idx + 1);
                    if(b == '*') {
                        if(a == '1') {
                            ans += 9 * rec(s, idx + 2, dp);
                        } else if(a == '2') {
                            ans += 6 * rec(s, idx + 2, dp);
                        }
                    } else {
                        if(Integer.parseInt(s.substring(idx, idx + 2)) <= 26) {
                            ans += 1 * rec(s, idx + 2, dp);
                        }
                    }
                }
            }
            return dp[idx] = ans % ((int) 1e9 + 7);
        }
        // public static long rec(String s, int idx) {
        //     if(idx == s.length()) {
        //         return 1;
        //     }
        //     if(s.charAt(idx) == '0') {
        //         return 0;
        //     }
        //     if(dp[idx] != -1) return dp[idx];
        //     long ans = 0;
        //     char a = s.charAt(idx);
        //     if(a == '*'){
        //         ans += 9 * rec(s, idx + 1, dp); //single digit call
        //         if(idx < s.length() - 1) {
        //             char b = s.charAt(idx + 1);
        //             if(b >= '0' && b <= '6') {
        //                 ans += 2 * rec(s, idx + 2);
        //             } else if(b >= '7' && b <= '9') {
        //                 ans += 1 * rec(s, idx + 2);
        //             } else {
        //                 ans += 15 * rec(s, idx + 2);
        //             }
        //         }
        //     } else {
        //         ans += rec(s, idx + 1);
        //         if(idx < s.length() - 1) {
        //             char b = s.charAt(idx + 1);
        //             if(b == '*') {
        //                 if(a == '1') {
        //                     ans += 9 * rec(s, idx + 2);
        //                 } else if(a == '2') {
        //                     ans += 6 * rec(s, idx + 2);
        //                 }
        //             } else {
        //                 if(Integer.parseInt(s.substring(idx, idx + 2)) <= 26) {
        //                     ans += 1 * rec(s, idx + 2);
        //                 }
        //             }
        //         }
        //     }
        //     return dp[idx] = ans % ((int) 1e9 + 7);
        // }

        public long rec(String s, int IDX, long[] dp) {
            for(int idx = s.length(); idx >= 0; idx --) {
                if(idx == s.length()) {
                    dp[idx] = 1;
                    continue;
                }
                if(s.charAt(idx) == '0') {
                    dp[idx] = 0;
                    continue;
                }
                long ans = 0;
                char a = s.charAt(idx);
                if(a == '*'){
                    ans += 9 * dp[idx + 1];//rec(s, idx + 1, dp); //single digit call
                    if(idx < s.length() - 1) {
                        char b = s.charAt(idx + 1);
                        if(b >= '0' && b <= '6') {
                            ans += 2 * dp[idx + 2];//rec(s, idx + 2, dp);
                        } else if(b >= '7' && b <= '9') {
                            ans += 1 * dp[idx + 2];//rec(s, idx + 2, dp);
                        } else {
                            ans += 15 * dp[idx + 2];//rec(s, idx + 2, dp);
                        }
                    }
                } else {
                    ans += dp[idx + 1];//rec(s, idx + 1, dp);
                    if(idx < s.length() - 1) {
                        char b = s.charAt(idx + 1);
                        if(b == '*') {
                            if(a == '1') {
                                ans += 9 * dp[idx + 2];//rec(s, idx + 2, dp);
                            } else if(a == '2') {
                                ans += 6 * dp[idx + 2];//rec(s, idx + 2, dp);
                            }
                        } else {
                            if(Integer.parseInt(s.substring(idx, idx + 2)) <= 26) {
                                ans += 1 * dp[idx + 2];//rec(s, idx + 2, dp);
                            }
                        }
                    }
                }
                dp[idx] = ans % ((int) 1e9 + 7);
            }
            return dp[IDX];
            // if(dp[idx] != -1) return dp[idx];
        }
        
    public static void main(String[] args) throws Exception {
        // write your code here
        // int[] dp = new int[9];
        // Arrays.fill(dp, -1);
        // System.out.println(fiboMem(7, dp));
        // System.out.println(fiboTab(7, dp));
        // System.out.println(arrRec("11234451", 0, dp));
        // long[] dp = new long[s.length() + 1];
        // Arrays.fill(dp, -1);
        //  rec(s, 0, dp);
        // display(dp);
    }
}
