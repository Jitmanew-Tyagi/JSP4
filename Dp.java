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

        // Decode Ways 2

        // public long rec(String s, int IDX, long[] dp) {
        //     for(int idx = s.length(); idx >= 0; idx --) {
        //         if(idx == s.length()) {
        //             dp[idx] = 1;
        //             continue;
        //         }
        //         if(s.charAt(idx) == '0') {
        //             dp[idx] = 0;
        //             continue;
        //         }
        //         long ans = 0;
        //         char a = s.charAt(idx);
        //         if(a == '*'){
        //             ans += 9 * dp[idx + 1];//rec(s, idx + 1, dp); //single digit call
        //             if(idx < s.length() - 1) {
        //                 char b = s.charAt(idx + 1);
        //                 if(b >= '0' && b <= '6') {
        //                     ans += 2 * dp[idx + 2];//rec(s, idx + 2, dp);
        //                 } else if(b >= '7' && b <= '9') {
        //                     ans += 1 * dp[idx + 2];//rec(s, idx + 2, dp);
        //                 } else {
        //                     ans += 15 * dp[idx + 2];//rec(s, idx + 2, dp);
        //                 }
        //             }
        //         } else {
        //             ans += dp[idx + 1];//rec(s, idx + 1, dp);
        //             if(idx < s.length() - 1) {
        //                 char b = s.charAt(idx + 1);
        //                 if(b == '*') {
        //                     if(a == '1') {
        //                         ans += 9 * dp[idx + 2];//rec(s, idx + 2, dp);
        //                     } else if(a == '2') {
        //                         ans += 6 * dp[idx + 2];//rec(s, idx + 2, dp);
        //                     }
        //                 } else {
        //                     if(Integer.parseInt(s.substring(idx, idx + 2)) <= 26) {
        //                         ans += 1 * dp[idx + 2];//rec(s, idx + 2, dp);
        //                     }
        //                 }
        //             }
        //         }
        //         dp[idx] = ans % ((int) 1e9 + 7);
        //     }
        //     return dp[IDX];
        //     // if(dp[idx] != -1) return dp[idx];
        // }

        // Friends pairing
        // public static int rec(int n) {
        //     if(n == 0) {
        //         return 1;
        //     }
        //     int ans = 0;
        //     ans += rec(n - 1);
        //     if(n > 1) ans += rec(n - 2) * (n - 1);
        //     return ans;
        // }
    
        // public static int memo(int n, int[] dp) {
        //     if(n == 0) {
        //         return dp[n] = 1;
        //     }
        //     if(dp[n] != 0) return dp[n];
        //     int ans = 0;
        //     ans += memo(n - 1, dp);
        //     if(n > 1) ans += memo(n - 2, dp) * (n - 1);
        //     return dp[n] = ans;
        // }
    
        // public static int tab(int N, int[] dp) {
        //     for(int n = 0; n <= N; n ++) {
        //         if(n == 0) {
        //             dp[n] = 1;
        //             continue;
                // }
                // if(dp[n] != 0) return dp[n];
        //         int ans = 0;
        //         ans += dp[n - 1];//memo(n - 1, dp);
        //         if(n > 1) ans += dp[n - 2] * (n - 1);//memo(n - 2, dp) * (n - 1);
        //         dp[n] = ans;
        //     }
        //     return dp[N];
        // }

        public static int kSubsets(int N, int K, int[][] dp) {
            for(int n = 0; n <= N; n ++) {
                for(int k = 0; k <= K; k ++) {
                    if(n <= 0 || n < k || k <= 0) {
                        dp[n][k] = 0;
                        continue;
                    }
                    if(n == k || k == 1) {
                        dp[n][k] = 1;
                        continue;
                    } 
                    // if(dp[n][k] != 0) return dp[n][k];
                    int ans = 0;
                    ans += dp[n - 1][k - 1];//kSubsets(n - 1, k - 1, dp);
                    ans += dp[n - 1][k] * k;//kSubsets(n - 1, k, dp) * k;
                    dp[n][k] = ans;
                }
            }
            return dp[N][K];
        }

    public static int gmr(int[][] mine, int r, int c, int[][] dp) {
        if(c == mine[0].length - 1) {
            return dp[r][c] = mine[r][c];
        }
        if(dp[r][c] != -1) return dp[r][c];
        int ans = 0;
        int[][] dirn = {{-1, 1}, {0, 1}, {1, 1}};
        for(int[] dir : dirn) {
            int x = r + dir[0];
            int y = c + dir[1];
            if(x >= 0 && y < mine[0].length && x < mine.length) {
                ans = Math.max(ans, gmr(mine, x, y, dp));
            }
        }
        return dp[r][c] = ans + mine[r][c];
    }

    public static void rhipep(String s, int idx, String ans) {
        if(idx == s.length()) {
            System.out.println(ans);
            return;
        }
        if(idx < s.length() - 1 && s.substring(idx, idx + 2).equals("hi")) {
            rhipep(s, idx + 2, ans + "pep");
        } else {
            rhipep(s, idx + 1, ans + s.charAt(idx));
        }
    } 


    // Leetcode 516

    public static int rec(String s, int si, int ei) {
        
        if(si >= ei) {
            if(si == ei) return 1;
            return 0;
        }
        
        int ans = 0;
        if(s.charAt(si) == s.charAt(ei)) {
            int cans = rec(s, si + 1, ei -1) + 2;
            ans = cans;
        } else {
            int canstwo = Math.max(rec(s, si + 1, ei), rec(s, si, ei - 1));
            ans = canstwo;
        }
        return ans;
    }
    
    public static int mem(String s, int si, int ei, int[][] dp) {
        
        if(si >= ei) {
            if(si == ei) return dp[si][ei] = 1;
            return dp[si][si] = 0;
        }
        if(dp[si][ei] != -1) return dp[si][ei];
        int ans = 0;
        if(s.charAt(si) == s.charAt(ei)) {
            int cans = mem(s, si + 1, ei -1, dp) + 2;
            ans = cans;
        } else {
            int canstwo = Math.max(mem(s, si + 1, ei, dp), mem(s, si, ei - 1, dp));
            ans = canstwo;
        }
        return dp[si][ei] = ans;
    }

    public static int tab(String s, int SI, int EI, int[][] dp) {
        
        int n = EI;
        for(int gap = 0; gap <= n; gap ++) {
            for(int si = 0, ei = gap; si <= n && ei <= n; si ++, ei ++) {
                if(si >= ei) {
                    if(si == ei) {
                        dp[si][ei] = 1;
                        continue;
                    }
                    dp[si][ei] = 0;
                    continue;
                }
                
                // if(dp[si][ei] != -1) return dp[si][ei];
                int ans = 0;
                if(s.charAt(si) == s.charAt(ei)) {
                    int cans = dp[si + 1][ei - 1] + 2;//mem(s, si + 1, ei -1, dp) + 2;
                    ans = cans;
                } else {
                    int canstwo = Math.max(dp[si + 1][ei], dp[si][ei - 1]);
                    ans = canstwo;
                }
                dp[si][ei] = ans;
            }
        }
        return dp[SI][EI];
    }

    // Leetcode 5

    // public static String longestPalindrome(String s) {
    //     String[][] dp = new String[s.length()][s.length()];
    //     return rec(s, 0, s.length() - 1, dp);
    // }
    
    // public String rec(String str, int s, int e, String[][] dp) {
    //     if(s >= e) {
    //         return dp[s][e] = s == e ? str.substring(s, e + 1): "";
    //     }
    //     if(dp[s][e] != null) return dp[s][e];
    //     if(str.charAt(s) == str.charAt(e) && rec(str, s + 1, e - 1, dp).length() == (e - s - 1)) {
    //         return dp[s][e] = str.substring(s, e + 1);
    //     } else {
    //         String a = rec(str, s + 1, e, dp);
    //         String b = rec(str, s, e - 1, dp);
    //         return dp[s][e] = a.length() > b.length() ? a : b;
    //     }
    // }

    // public static boolean targetSum(int[] arr, int tar, int idx) {
    //     //base
    //     if(tar == 0){
        if(idx != -1) dp[tar][idx] = 1;
            return true;
        } 
        if(idx != -1 && dp[tar][idx] != -1) return dp[tar][idx] == 1;
        for(int i = idx + 1; i < arr.length; i ++) {
            if(tar - arr[i] >= 0) {
                boolean ans = rec(arr, tar - arr[i], i, dp);
                if(ans) return ans;
            }
        }
        if(idx != -1) dp[tar][idx] = 0;
        return false;
    // }
    

    public static void main(String[] args) throws Exception {
        // write your code here
        // Scanner scn = new Scanner(System.in);
        // Arrays.fill(dp, -1);
        // System.out.println(fiboMem(7, dp));
        // System.out.println(fiboTab(7, dp));
        // System.out.println(arrRec("11234451", 0, dp));
        // long[] dp = new long[s.length() + 1];
        // Arrays.fill(dp, -1);
        //  rec(s, 0, dp);
        // System.out.println(kSubsets(4, 2, new int[5][3]));
        // int n = scn.nextInt();
        // int m = scn.nextInt();
        // int[][] dp = new int[6][6];
        // for(int[] d : dp) Arrays.fill(d, -1);
        // int[][] mine = new int[n][m];
        // for(int i = 0; i < n; i ++) {
        //     for(int j = 0; j < m; j ++) mine[i][j] = scn.nextInt();
        // }
        // int maxGold = 0;
        
        // for(int i = 0; i < n; i ++) maxGold = Math.max(maxGold, gmr(mine, i, 0, dp));
        // System.out.println(maxGold);
        // display(dp);
        // rhipep("abhicdhihiab", 0, "");
        // tab("abbcda", 0, 5, dp);
        // display2D(dp);
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i ++) arr[i] = scn.nextInt();
        int tar = scn.nextInt();
        System.out.println(targetSum(arr, tar, -1));
    }
}
