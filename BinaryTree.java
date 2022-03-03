import java.util.*;
public class BinaryTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode () {

        } 
        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static class Pair {
        TreeNode node;
        int state;
        Pair(TreeNode node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static TreeNode constructTree(Integer[] val) {
        TreeNode root = null;
        Stack<Pair> st = new Stack<>();
        for(Integer i : val) {
            if(i != null) {
                while(!st.isEmpty() && st.peek().state == 3) st.pop();
                Pair branch = new Pair(new TreeNode(i), 1);
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

    public static void display(TreeNode root) {
        if(root == null) return;
        System.out.println(root.val);
        display(root.left);
        display(root.right);
    }

    public static HashSet<TreeNode> hs = new HashSet<>();
    public static void kLevelDown(TreeNode root, int time, TreeNode blocker, List<List<Integer>> ans) {
        if(root == null || root == blocker || hs.contains(root)) return;
        if(time == ans.size()) ans.add(new ArrayList<Integer>());
        ans.get(time).add(root.val);
        
        kLevelDown(root.left, time + 1, blocker, ans);
        kLevelDown(root.right, time + 1, blocker, ans);
    }
    
    public static int ntrp(TreeNode root, TreeNode fire, List<List<Integer>> ans) {
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
    
    public static TreeNode getRightMost(TreeNode node, TreeNode curr) {
        while(node.right != null && node.right != curr) {
            node = node.right;
        }
        return node;
    }
    
    public static ArrayList<Integer> morrisInTraversal(TreeNode node) {
        TreeNode curr = node;
        ArrayList<Integer> ans = new ArrayList<>();
        while(curr != null) {
            if(curr.left != null) {
                TreeNode rm = getRightMost(curr.left, curr);
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

    public static TreeNode bstToDLL(TreeNode root) {
        TreeNode dummy = new TreeNode(-1);
        TreeNode prev = dummy, curr = root;
        while(curr != null) {
            if(curr.left != null) {
                TreeNode rm = getRightMost(curr.left, curr);
                if(rm.right == null) {
                    rm.right = curr;
                    curr = curr.left;
                } else {
                    rm.right = null;
                    prev.right = curr;
                    curr.left = prev;
                    prev = curr;
                    curr = curr.right;
                }
            } else {
    
                prev.right = curr;
                curr.left = prev;
                prev = curr;
                curr = curr.right;
            }
        }
        TreeNode head = dummy.right;
        dummy.right = head.left = null;
        return head;
    }


    public static TreeNode mergeTwoSortedDLL(TreeNode first, TreeNode second) {
        TreeNode dummy = new TreeNode(-1);
        TreeNode prev = dummy, c1 = first, c2 = second;
        while(c1 != null && c2 != null) {
            if(c1.val <= c2.val) {
                c1.left = prev;
                prev.right = c1;
                prev = c1;
                c1 = c1.right;
            } else {
                c2.left = prev;
                prev.right = c2;
                prev = c2;
                c2 = c2.right;
            }
        }
        if(c1 != null) {
            c1.left = prev;
            prev.right = c1;
            prev = c1;
            c1 = c1.right;
        } else {
            c2.left = prev;
            prev.right = c2;
            prev = c2;
            c2 = c2.right;
        }
        TreeNode head = dummy.right;
        head.left = dummy.right = null;
        return head;
    }
    
    public static TreeNode mergeSort(TreeNode head) {
        if(head == null || head.right == null) return head;
        TreeNode mid = getMid(head);
        TreeNode fwdHead = mid.right;
        mid.right = fwdHead.left = null;
        return mergeTwoSortedDLL(mergeSort(head), mergeSort(fwdHead)); 
    }

    public static TreeNode getMid(TreeNode head) {
        TreeNode slow = head, fast = head;
        while(fast.right != null && fast.right.right != null) {
            fast = fast.right.right;
            slow = slow.right;
        }
        return slow;
    }
  
    // left : prev, right : next
    public static TreeNode SortedDLLToBST(TreeNode head) {
      if (head == null || head.right == null) return head;
      TreeNode mid = getMid(head);
      TreeNode prev = mid.left, fwd = mid.right;
      TreeNode root = mid;
      mid.left = mid.right = fwd.left = null;
      if(prev != null) prev.right = null;
      TreeNode lsh = prev == null ? null : head, rsh = fwd; 
      root.left = SortedDLLToBST(lsh);
      root.right = SortedDLLToBST(rsh);
      return root;
    }

    public static void main (String[] args) {
        Integer[] val = {14, 2, 100, null, null, 3, null, null, 60, 0, null, null, 7, null, null};
        TreeNode root = constructTree(val);
        TreeNode head = bstToDLL(root);
        head = mergeSort(head);
        root = SortedDLLToBST(head);
        // TreeNode x = head;
        // while(x != null) {
        //     System.out.print(x.val + " ");
        //     x = x.right;
        // }
        ArrayList<Integer> ans = morrisInTraversal(root);
        System.out.println(ans);
        // display(root);
    }
}
