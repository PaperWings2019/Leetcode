import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class treeNode {
    int val;
    treeNode left;
    treeNode right;
    treeNode() {}
    treeNode(int val) {
        this.val = val;
    }
    treeNode(int val, treeNode left, treeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class tree {
    public List<Integer> traversal(treeNode tn) {
        List<Integer> done = new ArrayList<>();
        preorderTraversal(tn, done);
        System.out.println(done);
        done = new ArrayList<>();
        inorderTraversal(tn, done);
        System.out.println(done);
        done = new ArrayList<>();
        postorderTraversal(tn, done);
        System.out.println(done);
        return done;
    }
    public void preorderTraversal(treeNode tn, List<Integer> done) {
        if (tn == null) {
            return;
        }
        done.add(tn.val);
        preorderTraversal(tn.left, done);
        preorderTraversal(tn.right, done);
    }
    public void inorderTraversal(treeNode tn, List<Integer> done) {
        if (tn == null) {
            return;
        }
        inorderTraversal(tn.left, done);
        done.add(tn.val);
        inorderTraversal(tn.right, done);
    }
    public void postorderTraversal(treeNode tn, List<Integer> done) {
        if (tn == null) {
            return;
        }
        postorderTraversal(tn.left, done);
        postorderTraversal(tn.right, done);
        done.add(tn.val);
    }

    public List<Integer> iter_preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stk = new Stack<>();
        if (root != null) stk.push(root);
        while (!stk.isEmpty()) {
            TreeNode node = stk.peek();
            stk.pop();
            if (node != null) {
                if (node.right != null) stk.push(node.right);
                if (node.left != null) stk.push(node.left);
                stk.push(node);
                stk.push(null);
            } else {
                result.add(stk.pop().val);
            }
        }
        return result;
    }

    public List<Integer> iter_inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stk = new Stack<>();
        if (root != null) stk.push(root);
        while (!stk.isEmpty()) {
            TreeNode node = stk.peek();
            stk.pop();
            if (node != null) {
                if (node.right != null) stk.push(node.right);
                stk.push(node);
                stk.push(null);
                if (node.left != null) stk.push(node.left);
            } else {
                result.add(stk.pop().val);
            }
        }
        return result;
    }

    public List<Integer> iter_postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stk = new Stack<>();
        if (root != null) stk.push(root);
        while (!stk.isEmpty()) {
            TreeNode node = stk.peek();
            stk.pop();
            if (node != null) {
                stk.push(node);
                stk.push(null);
                if (node.right != null) stk.push(node.right);
                if (node.left != null) stk.push(node.left);
            } else {
                result.add(stk.pop().val);
            }
        }
        return result;
    }
}

