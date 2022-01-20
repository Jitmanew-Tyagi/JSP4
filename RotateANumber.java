import java.util.*;
   
   public class RotateANumber{
   
   public static void main(String[] args) {
     // write your code here 
     Scanner scn = new Scanner(System.in);
     int n = scn.nextInt();
     int k = scn.nextInt();
     int nod = (int)Math.log10(n) + 1; //nod -> no of digits
     k %= nod;
     if(k < 0) k += nod;
     
     // k < nod && k +ve
     for(int i = 0; i < k; i ++) {
         int ld = n % 10;
         n /= 10;
         n += ld*(int)Math.pow(10, nod - 1);
     }
     System.out.println(n);
    }
   }