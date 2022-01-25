import java.io.*;
import java.util.*;

public class Main {

	public static void solution(String str){
		int n = str.length();
		for(int i = 0; i  < str.length(); i ++) {
		    for(int j = i; j < n; j ++) {
		        String temp = str.substring(i, j + 1);
		        if(isPallindrome(temp)) System.out.println(temp);
		    }
		}
	}
	
	public static boolean isPallindrome(String str) {
	    int s = 0, e = str.length() - 1;
	    while(s < e) {
	        if(str.charAt(s ++) != str.charAt(e --)) return false;
	    }
	    return true;
	}

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		String str = scn.next();
		solution(str);
	}
}