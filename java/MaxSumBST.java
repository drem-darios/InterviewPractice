public class MaxSumBST {

    public static int maxSumBST(TreeNode root) {
        // Build BST
        return maxSumBSTHelper(root, 0);
    }

    public static int maxSumBSTHelper(TreeNode root, int sum) {
        if (root == null) {
            return sum;
        }
        if (root.left == null && root.right == null) {
            return Math.max(sum, root.val);
        }

        int leftSum = maxSumBSTHelper(root.left, sum);
        int rightSum = maxSumBSTHelper(root.right, sum);

        if(root.left == null && root.val < 0){
            return Math.max(sum, rightSum + root.val);
        }

        if(root.right == null && root.val > 0){
            return Math.max(sum, leftSum + root.val);
        }

        return Math.max(sum, root.val + leftSum + rightSum);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    public static void main(String[] args) {
        Integer[] input = new Integer[] {1,4,3};
        TreeNode root = new TreeNode(input[0]);
        for (int i = 1; i < input.length; i++) {

        }
        System.out.println(MaxSumBST.maxSumBST(root));
    }
}
