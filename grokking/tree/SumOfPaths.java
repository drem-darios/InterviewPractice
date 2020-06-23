package grokking.tree;

import grokking.models.TreeNode;
// DFS
class SumOfPaths {
    public static int findSumOfPathNumbers(TreeNode root) {
        return findRootToLeafPathNumbers(root, new StringBuilder(), 0);
    }

    private static int findRootToLeafPathNumbers(TreeNode currentNode, StringBuilder pathString, Integer sum) {

        if(currentNode == null) {
            return sum;
        }

        pathString.append(currentNode.val);

        if (currentNode.left == null && currentNode.right == null) {
            // System.out.println(pathString.toString());
            int pathResult = Integer.valueOf(pathString.toString());
            pathString.deleteCharAt(pathString.length() - 1);
            return pathResult;
        }

        sum = findRootToLeafPathNumbers(currentNode.left, pathString, sum) + findRootToLeafPathNumbers(currentNode.right, pathString, sum);

        pathString.deleteCharAt(pathString.length() - 1);

        return sum;

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(5);
        System.out.println("Total Sum of Path Numbers: " + SumOfPaths.findSumOfPathNumbers(root));
    }
}
