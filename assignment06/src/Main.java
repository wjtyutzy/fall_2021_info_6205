
import java.util.*;

public class Main {

    public static void main(String[] args) {


    }

    //Question 1: You are given two binary trees root1 and root2. Return the merged tree.

    //time o(n)  space o(n)
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return null;
        } else if (root1 == null || root2 == null) {
            return root1 == null ? root2 : root1;
        }

        int v1 = root1.value;
        int v2 = root2.value;
        TreeNode root = new TreeNode(v1 + v2);
        root.left = mergeTrees(root1.left, root2.left);
        root.right = mergeTrees(root1.right, root2.right);
        return root;
    }


    //Question 2: Consider all the leaves of a binary tree, from left to right order, the values
    //of those leaves form a leaf value sequence.
    //Two binary trees are considered leaf-similar if their leaf value sequence is the same.
    //Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.

    //time o(n) space o(n)
    public boolean isSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> r1 = new ArrayList<>();
        List<Integer> r2 = new ArrayList<>();
        getLeavesSq(root1, r1);
        getLeavesSq(root2, r2);
        return r1.equals(r2);
    }

    private void getLeavesSq(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            res.add(root.value);
        }
        getLeavesSq(root.left, res);
        getLeavesSq(root.right, res);
    }


    //Question 3: Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf
    //path such that adding up all the values along the path equals targetSum.

    //time o(n)  space o(n)
    public boolean pathSumT(TreeNode root, int t) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.value == t;
        }
        return pathSumT(root.left, t - root.value) || pathSumT(root.right, t - root.value);
    }


    //Question 4: Given a binary tree, determine if it is height balanced.For this problem, a height-balanced binary tree
    //is defined as: a binary tree in which the left and right subtrees of every node differ in height by no more than 1.

    //time o(n) space o(n) call stack
    public boolean isHeightBan(TreeNode root) {
        if (root == null) {
            return true;
        }
        //we need to know h, we need to know if it is balanced
        Pair p = recur(root);
        return p.isB;
    }

    private Pair recur(TreeNode root) {
        //corner case;
        if (root == null) {
            return new Pair(0, true);
        }

        Pair left = recur(root.left);
        Pair right = recur(root.right);

        if (left.isB && right.isB) {
            return new Pair(Math.max(left.height, right.height) + 1, Math.abs(left.height - right.height) <= 1);
        }
        return new Pair(Math.max(left.height, right.height) + 1, false);
    }


    //Question 5: Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with
    //the same structure and node values of subRoot and false otherwise.

    //time o(n + m) space o( max(n,m)) call stack
    public boolean hasSubtree(TreeNode root, TreeNode sub) {
        String substr = treeToStr(sub, "NOT EXIST");
        containStr = false;

        treeToStr(root, substr);

        return containStr;
    }

    private boolean containStr;

    private String treeToStr(TreeNode root, String verify) {
        if (root == null) {
            return "#";
        }
        String left = treeToStr(root.left, verify);
        String right = treeToStr(root.right, verify);
        String rootStr = left + right + root.value;
        if (rootStr.equals(verify)) {
            containStr = true;
        }
        return rootStr;
    }


    //Question 6: Given the root of a binary tree, check whether it is a mirror of itself(i.e., symmetric around its center).

    //time o(n) space o(n) call stack
    public boolean isMirror(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isM(root.left, root.right);
    }

    private boolean isM(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        }

        if (left.value != right.value) {
            return false;
        }

        return isM(left.right, right.left) && isM(right.right, left.left);
    }


    //Question 7: Given the root of a binary tree, flatten the tree into a "linked list":

    //time o(n) space o(n) call stack
    public TreeNode flattenTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode head = root;
        TreeNode left = flattenTree(root.left);
        TreeNode right = flattenTree(root.right);

        root.right = left;
        root.left = null;

        while (root.right != null) {
            root = root.right;
        }
        root.right = right;
        return head;
    }


    //Question 8: Given the root of a binary tree, return the vertical order traversal of its nodes' values.
    //(i.e., from top to bottom, column by column).
    //If two nodes are in the same row and column, the order should be from left to right.

    //time o(n)  space o(n)
    public List<List<Integer>> vertical(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        int leftmost = 0; //will be fine it is root's idx;
        Queue<Pair1> q = new LinkedList<>();
        q.offer(new Pair1(root, 0));

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Pair1 cur = q.poll();
                int idx = cur.idx;
                leftmost = Math.min(leftmost, idx);
                TreeNode tmp = cur.node;

                if (!map.containsKey(idx)) {
                    map.put(idx, new ArrayList<>());
                }
                map.get(idx).add(tmp.value);

                if (tmp.left != null) q.offer(new Pair1(tmp.left, idx - 1));
                if (tmp.right != null) q.offer(new Pair1(tmp.right, idx + 1));
            }
        }
        int j = leftmost;
        while (map.get(j) != null) {
            res.add(map.get(j));
            j++;
        }

        return res;
    }


}
