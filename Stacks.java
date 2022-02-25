import java.io.*;
import java.util.*;

public class Stacks {

    public static void main(String[] args) throws Exception {
       
        Stack<Integer> st = new Stack<>();
        for(int i = 1; i <= 5; i ++) {
            st.push(i);
        }
        

        // System.out.println(st.size());
        // System.out.println(st.isEmpty());
        // System.out.println(st.pop());
        // System.out.println(st.pop());
        // System.out.println(st.pop());
        // System.out.println(st.pop());
        int x = st.pop();
        System.out.println(x);
    }


    //leetcode 84
        
    public int largestRectangleArea(int[] heights) {
        return getMaxArea(heights);
    }
    
    public int getMaxArea(int[] heights) {
        int[] left = nsl(heights);
        int[] right = nsr(heights);
        int maxArea = 0;
        for(int i = 0; i < heights.length; i ++) {
            maxArea = Math.max(maxArea, (right[i] - left[i] - 1) * heights[i]);
        }
        return maxArea;
    }
    
    public int[] nsl(int[] arr) {
        int[] ans = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < arr.length; i ++) {
            while(!st.isEmpty() && arr[st.peek()] >= arr[i]) st.pop();
            ans[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        return ans;
    }
    
    public int[] nsr(int[] arr) {
        int[] ans = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        for(int i = arr.length - 1; i >= 0; i --) {
            while(!st.isEmpty() && arr[st.peek()] >= arr[i]) st.pop();
            ans[i] = st.isEmpty() ? arr.length : st.peek();
            st.push(i);
        }
        return ans;
    }

    // celebrity problem
    
    public static void findCelebrity(int[][] arr) {
        // if a celebrity is there print it''s index (not position), if there is not then
        // print "none"
        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < arr.length; i ++) st.push(i);
        while(st.size() > 1) {
            int a = st.pop();
            int b = st.pop();
            if(arr[a][b] == 1) st.push(b);
            else{st.push(a);}
        }
        System.out.println(check(st.peek(), arr) ? st.pop() : "none");
    }

    public static boolean check(int x, int[][] arr) {
        for(int i = 0; i < arr.length; i ++) {
            if(arr[x][i] == 1) return false;
            if(x != i && arr[i][x] == 0) return false;
        }
        return true;
    }

}