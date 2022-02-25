import java.io.*;
import java.util.*;

public class Main {
  private static class Node {
    int data;
    ArrayList<Node> children = new ArrayList<>();
  }

  public static void display(Node node) {
    String str = node.data + " -> ";
    for (Node child : node.children) {
      str += child.data + ", ";
    }
    str += ".";
    System.out.println(str);

    for (Node child : node.children) {
      display(child);
    }
  }

  public static Node construct(int[] arr) {
    Node root = null;

    Stack<Node> st = new Stack<>();
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == -1) {
        st.pop();
      } else {
        Node t = new Node();
        t.data = arr[i];

        if (st.size() > 0) {
          st.peek().children.add(t);
        } else {
          root = t;
        }

        st.push(t);
      }
    }

    return root;
  }

  public static int size(Node node){
    // write your code here
    if(node == null) return 0;
    int s = 1;
    for(Node child : node.children) s += size(child);
    return s;
  }

  public static void traversals(Node node){
    System.out.println("Node Pre " + node.data);

    for(Node child: node.children){
      System.out.println("Edge Pre " + node.data + "--" + child.data);
      traversals(child);
      System.out.println("Edge Post " + node.data + "--" + child.data);
    }

    System.out.println("Node Post " + node.data);
  }

  public static void levelOrderLinewiseZZ(Node node){
    // write your code here
    Stack<Node> ms = new Stack<>();
    Stack<Node> cs = new Stack<>();
    int level = 1;
    ms.push(node);
    while(!ms.isEmpty()) {
      Node out = ms.pop();
      System.out.print(out.data + " ");
      //children addition
      if(level % 2 == 0) {
        for(int i = out.children.size() - 1; i >= 0; i --) cs.push(out.children.get(i));
      } else {
        for(int i = 0; i <=  out.children.size() - 1; i ++) cs.push(out.children.get(i));
      }
      if(ms.isEmpty()) {
        ms = cs;
        cs = new Stack<>();
        System.out.println();
        level ++;
      }
    }
  }

  public static void mirror(Node node){
    // write your code here
    Collections.reverse(node.children);
    for(Node child : node.children) mirror(child);
  }

  public static boolean areMirror(Node n1, Node n2) {
    if(n1.children.size() != n2.children.size()) return false;
    int ci = 0;
    while(ci < n1.children.size()) {
      if(!areMirror(n1.children.get(ci), n2.children.get((ci ++)))) return false;
    }
    return true;
  }

  // public boolean isSimilar(Node n1, Node n2) {
  //   if(n1.children.size() != n2.children.size()) return false;
  //   int ci = 0;
  //   for() 
  // }  

  public static boolean IsSymmetric(Node node) {
    // write your code here
    Node temp = new Node(node);
    mirror(temp);
    return areMirror(node, temp);
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    String[] values = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(values[i]);
    }

    Node root = construct(arr);
    int sz = size(root);
    System.out.println(sz);
    // display(root);
  }

}