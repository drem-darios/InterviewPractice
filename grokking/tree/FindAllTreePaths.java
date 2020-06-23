package grokking.tree;

import grokking.models.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// DFS
class FindAllTreePaths {
    public static List<List<Integer>> findPaths(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        findPaths(result, new Stack<Integer>(), root, sum);
        return result;
    }

    private static void findPaths(List<List<Integer>> result, Stack<Integer> currentResult, TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        currentResult.add(root.val);

        if (root.val == sum && root.left == null && root.right == null) {
            result.add(new ArrayList<>(currentResult));
            currentResult.pop();
            return;
        } else {
            findPaths(result, currentResult, root.left, sum - root.val);
            findPaths(result, currentResult, root.right, sum - root.val);
        }

        currentResult.pop();
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        int sum = 23;
        List<List<Integer>> result = FindAllTreePaths.findPaths(root, sum);
        System.out.println("Tree paths with sum " + sum + ": " + result);
    }
}
