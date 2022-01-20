import java.util.*;
  
  public class DecimalToAnyBase{
  
  public static void main(String[] args) {
      Scanner scn = new Scanner(System.in);
      int n = scn.nextInt();
      int b = scn.nextInt();
      int dn = getValueInBase(n, b);
      System.out.println(dn);
   }
  
   public static int getValueInBase(int n, int b){
       // write code here
       int ans = 0, it = 0;
       while(n > 0) {
           int rem = n % b;
           n /= b;
           ans += rem * (int)Math.pow(10, it ++);
       }
       return ans;
   }
  }