import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        printEncodings(scn.nextLine(), "");
    }

    public static void printEncodings(String str, String ans) {
        if(str.length() == 0) {
            System.out.println(ans);
            return;
        }
        int n = str.charAt(0) - '0';
        if(n == 0) return;
        printEncodings(str.substring(1), ans + (char)('a' + n - 1));
        if(str.length() > 1) {
            n = Integer.parseInt(str.substring(0, 2));
            if(n <= 26) 
                printEncodings(str.substring(2), ans + (char)('a' + n - 1));
        }
    }
}