import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int dr = scn.nextInt();
        int dc = scn.nextInt();
        System.out.println(getMazePaths(0, 0, dr - 1, dc - 1));
    }

    // sr - source row
    // sc - source column
    // dr - destination row
    // dc - destination column
    public static ArrayList<String> getMazePaths(int sr, int sc, int dr, int dc) {
        if(sr > dr || sc > dc) return new ArrayList<>();
        if(sr == dr && sc == dc) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        //horizontal;
        ArrayList<String> ans = new ArrayList<>();
        ArrayList<String> hori = getMazePaths(sr, sc + 1, dr, dc);
        for(String s : hori) ans.add("h" + s);
        ArrayList<String> vert = getMazePaths(sr + 1, sc, dr, dc);
        for(String s : vert) ans.add("v" + s);
        return ans;
    }

}