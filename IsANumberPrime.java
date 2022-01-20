import java.util.*;
public class IsANumberPrime{
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int t = scn.nextInt();
        for(int i = 0; i < t; i ++) {
            int n = scn.nextInt();
            boolean isPrime = true;
            for(int d = 2; d <= n / 2; d ++) {
                if(n % d == 0) {
                    isPrime = false;
                    break;
                }
            }
            System.out.println(isPrime ? "prime" : "not prime");
        }
    }
}