package grokking.tree;

import grokking.models.TreeNode;

import java.util.*;

;

class LevelOrderTraversal {
    public static List<List<Integer>> traverse(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        Queue<TreeNode> level = new LinkedList<>();
        Queue<TreeNode> nextLevel = new LinkedList<>();
        List<Integer> levelResult = new ArrayList<>();
        level.add(root);
        while(!level.isEmpty()) {
            TreeNode currentNode = level.remove();
            levelResult.add(currentNode.val);
            if (currentNode.left != null) {
                nextLevel.add(currentNode.left);
            }
            if (currentNode.right != null) {
                nextLevel.add(currentNode.right);
            }

            if(level.isEmpty()) {
                level.addAll(nextLevel);
                result.add(levelResult);
                nextLevel = new LinkedList<>();
                levelResult = new ArrayList<>();
            }

        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        List<List<Integer>> result = LevelOrderTraversal.traverse(root);
        System.out.println("Level order traversal: " + result);
    }
}

