public class AVLTree {
    TreeNode root;

    AVLTree() {
        root = null;
    }

    AVLTree(int[] data) {
        for(int i : data) root = addNode(root, i);
    }

    public static void updateHeightAndBalFact(TreeNode root) {
        int lh = root.left == null ? -1 : root.left.height;
        int rh = root.right == null ? -1 : root.right.height;

        root.height = Math.max(lh, rh) + 1;
        root.balFact = lh - rh;
    }

    public static TreeNode rightRotate(TreeNode A) {
        TreeNode B = A.left, rightOfB = B.right;
        B.right = A;
        A.left = rightOfB;
        updateHeightAndBalFact(A);
        updateHeightAndBalFact(B);
        return B;
    }
    public static TreeNode leftRotate(TreeNode A) {
        TreeNode B = A.right, leftOfB = B.left;
        B.left = A;
        A.right = leftOfB;
        updateHeightAndBalFact(A);
        updateHeightAndBalFact(B);
        return B;
    }

    public static TreeNode identifyAndBalance(TreeNode root) {
        updateHeightAndBalFact(root);
        if(root.balFact == 2) { //ll, lr
            if(root.left.balFact == 1) { //ll
                root = rightRotate(root);
            } else { //lr
                root.left = leftRotate(root.left);
                root = rightRotate(root);
            }
            
        } else if(root.balFact == -2) { // rl, rr
            if(root.right.balFact == 1) { //rl
                root.right = rightRotate(root.right);
                root = leftRotate(root);
            } else { //rr
                root = leftRotate(root);
            }
        }
        return root;
    }

    public static TreeNode addNode(TreeNode root, int val) {
        if(root == null) return new TreeNode(val);
        if(root.val > val) {
            root.left = addNode(root.left, val);
        } else {
            root.right = addNode(root.right, val);
        }
        return identifyAndBalance(root);
    }
}