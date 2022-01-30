import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        String s = scn.nextLine();
        System.out.println(getKPC(s));
    }
    static String[] arr = {".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz"};
    public static ArrayList<String> getKPC(String str) {
        if(str.length() == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> faith = getKPC(str.substring(1));
        ArrayList<String> ans = new ArrayList<>();
        String word = arr[str.charAt(0) - '0']; 
        for(int i = 0; i < word.length(); i ++) {
            for(String s : faith) {
                ans.add(word.charAt(i) + s);
            }
        }
        return ans;
    }

}