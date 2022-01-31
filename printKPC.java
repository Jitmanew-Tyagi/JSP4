import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        String str = scn.nextLine();
        printKPC(str, "");
    }
    static String[] giveWords = {".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz"};
    public static void printKPC(String str, String asf) {
        if(str.length() == 0) {
            System.out.println(asf);
            return;
        }   
        String word = giveWords[str.charAt(0) - '0'];
        for(int i = 0; i < word.length(); i ++) {
            printKPC(str.substring(1), asf + word.charAt(i));
        }
    }

}