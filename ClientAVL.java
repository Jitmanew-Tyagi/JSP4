public class ClientAVL {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree(new int[] {1, 3, 2, 7, 5, 9, 4});
        display(tree.root);
    }

    public static void display(TreeNode root) {
        if(root == null) {
            System.out.println(-1);
            return;
        } 
        System.out.println(root.val);
        display(root.left);
        display(root.right);
    }
}