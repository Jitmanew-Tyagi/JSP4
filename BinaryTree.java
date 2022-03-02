import java.util.*;
public class BinaryTree {
    public static class Node {
        int data;
        Node left;
        Node right;
        public Node () {

        } 
        public Node(int data) {
            this.data = data;
        }

        public Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static class Pair {
        Node node;
        int state;
        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static Node constructTree(Integer[] data) {
        Node root = null;
        Stack<Pair> st = new Stack<>();
        for(Integer i : data) {
            if(i != null) {
                while(!st.isEmpty() && st.peek().state == 3) st.pop();
                Pair branch = new Pair(new Node(i), 1);
                if(st.isEmpty()) {
                    root = branch.node;
                } else {
                    Pair top = st.peek();
                    if(top.state == 1) {
                        top.node.left = branch.node;
                    } else if(top.state == 2) {
                        top.node.right = branch.node;
                    } else {
                        st.pop();
                    }
                    top.state ++;
                }
                st.push(branch);
            } else {
                st.peek().state ++;
            }
        }
        return root;
    }

    public static void display(Node root) {
        if(root == null) return;
        System.out.println(root.data);
        display(root.left);
        display(root.right);
    }

    public static void main (String[] args) {
        Integer[] data = {10, 20, 40, null, null, 50, 70, null, null, 80, null, null, 30, null, 60, null, null};
        Node root = constructTree(data);
        List<List<Integer>> ans = new ArrayList<>();
        hs.add(root);
        ntrp(root, root.left.right, ans);
        System.out.println(ans);
    }
    public static HashSet<Node> hs = new HashSet<>();
    public static void kLevelDown(Node root, int time, Node blocker, List<List<Integer>> ans) {
        if(root == null || root == blocker || hs.contains(root)) return;
        if(time == ans.size()) ans.add(new ArrayList<Integer>());
        ans.get(time).add(root.data);

        kLevelDown(root.left, time + 1, blocker, ans);
        kLevelDown(root.right, time + 1, blocker, ans);
    }
   
    public static int ntrp(Node root, Node fire, List<List<Integer>> ans) {
        if(root == null) return -1;
        if(root == fire) {
            kLevelDown(root, 0, null, ans);
            return 1;
        } 

        int leftTime = ntrp(root.left, fire, ans);
        if(leftTime > -1) {
            kLevelDown(root, leftTime, root.left, ans);
            return leftTime + 1;
        }
        int rightTime = ntrp(root.right, fire, ans);
        if(rightTime > -1) {
            kLevelDown(root, rightTime, root.right, ans);
            return rightTime + 1;
        }
        return -1;
    }
    
    public static Node getRightMost(Node node, Node curr) {
        while(node.right != null && node.right != curr) {
          node = node.right;
        }
        return node;
      }
    
      public static ArrayList<Integer> morrisInTraversal(Node node) {
          Node curr = node;
          ArrayList<Integer> ans = new ArrayList<>();
          while(curr != null) {
            if(curr.left != null) {
              Node rm = getRightMost(curr.left, curr);
              if(rm.right == null) { //1st time visit, create thread
                rm.right = curr;
                curr = curr.left;
              } else { //2nd time visiting, already inorder, thread cut
                ans.add(curr.val);
                rm.right = null;
                curr = curr.right;
              }
            } else {
              ans.add(curr.val);
              curr = curr.right;
            }
          }
          return ans;
      }
}






