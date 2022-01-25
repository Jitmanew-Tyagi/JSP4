import java.io.*;
import java.util.*;

public class Main {

	public static String compression1(String str){
		// write your code here
        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));
        for(int i = 1; i < str.length(); i ++) {
            char p = str.charAt(i - 1);
            char c = str.charAt(i);
            if(p != c) {
                sb.append(c);
            }
        }
		return sb.toString();
	}

	public static String compression2(String str){
        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));
        int count = 1;
        for(int i = 1; i < str.length(); i ++) {
            char p = str.charAt(i - 1);
            char c = str.charAt(i);
            if(p != c) {
                sb.append(count != 1 ? count : "");
                count = 1;
                sb.append(c);
            } else{
                count ++;
            }
        }
        sb.append(count != 1 ? count : "");
		return sb.toString();

	}
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		String str = scn.next();
		System.out.println(compression1(str));
		System.out.println(compression2(str));
        // ArrayList<Integer> arl = new ArrayList<>();
        // System.out.println(arl + " " + arl.size());
        // arl.add(1);
        
        // System.out.println(arl + " " + arl.size());
        // // System.out.println(arl + " " arl.size());
        // arl.add(10);
        // System.out.println(arl + " "  + arl.size());
	}

}
























