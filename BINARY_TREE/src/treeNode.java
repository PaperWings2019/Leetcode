import java.util.ArrayList;
import java.util.List;

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
}

