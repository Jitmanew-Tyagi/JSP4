import java.io.*;
	import java.util.*;

	public class Main {

	    public static void main(String[] args) throws Exception {
            Scanner scn = new Scanner(System.in);
            int r = scn.nextInt();
            int c = scn.nextInt();
            printMazePaths(0, 0, r - 1, c - 1, "");
	    }
	    // sr - source row
	    // sc - source column
	    // dr - destination row
	    // dc - destination column
	    public static void printMazePaths(int sr, int sc, int dr, int dc, String psf) {
	        if(sr > dr || sc > dc) return;
	        if(sr == dr  && sc == dc) {
	            System.out.println(psf);
	            return;
	        }
	        printMazePaths(sr, sc + 1, dr, dc, psf + "h");
	        printMazePaths(sr + 1, sc, dr, dc, psf + "v");
	    }

	}