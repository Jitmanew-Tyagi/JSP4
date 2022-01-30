import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        String str = scn.nextLine();
        System.out.println(gss(str));
    }

    public static ArrayList<String> gss(String str) {
        //base
        if(str.length() == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> faith = gss(str.substring(1));
        ArrayList<String> ans = new ArrayList<>();
        for(String s : faith) 
            ans.add(s); //a denies to be the part of ans
        for(String s : faith) {
            ans.add(str.charAt(0) + s); //a agrees !
        }
        return ans;
        // return ;
    }

}