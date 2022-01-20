import java.io.*;
import java.util.*;

public class SumOfTwoArrays{

public static void main(String[] args) throws Exception {
    // write your code here
    Scanner scn = new Scanner(System.in);
    int n1 = scn.nextInt();
    int[] a = new int[n1];
    for(int i = 0; i < n1; i ++) a[i] = scn.nextInt();
    
    int n2 = scn.nextInt();
    int[] b = new int[n2];
    for(int i = 0; i < n2; i ++) b[i] = scn.nextInt();
    
    sum(a, n1, b, n2);
 }
 
 public static void sum(int[] a, int n1, int[] b, int n2) {
     int p1 = n1 - 1, p2 = n2 - 1, p3, carry = 0;
     int[] ans = new int[n1 > n2 ? n1: n2];
     p3 = ans.length - 1;
     while(p3 >= 0) {
        //  int sum = 0;
         
        //  if(p1 >=0) sum += a[p1];
        //  if(p2 >= 0) sum += b[p2];
        //  sum += carry;
         int sum = (p1 >= 0 ? a[p1] : 0) + (p2 >= 0 ? b[p2] : 0) + carry;
         ans[p3] = sum % 10;
         carry = sum / 10;
         p1 --;
         p2 --;
         p3 --;
     }
     if(carry > 0) System.out.println(carry);
     for(int x : ans) System.out.println(x);
 }

}