import java.util.*;
    
    public class Main{
    
    public static void main(String[] args) {
      // write your code here  
      Scanner scn = new Scanner(System.in);
      int a = scn.nextInt(); 
      int b = scn.nextInt(); 
      int oa = a, ob = b;
      while(b > 0) {
          int rem = a % b;
          a = b;
          b = rem;
      }
      System.out.println(a);
      System.out.println((oa*ob) / a);
     }
    }