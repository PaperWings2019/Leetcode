import java.util.*;

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

 class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
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

    public int minDepth(TreeNode root) {
        /*
         * TASK:   find the minimum depth of the tree
         *
         * METHOD: using queue to traverse the tree layer by layer, once find a leave, return depth + 1;
         *         otherwise keep traversing and increment the depth
         *
         * NOTE:   None
         *
         * TIME/
         * SPACE:  O(depth) / O(2 ^ depth)
         */
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        int depth = 0;
        if (root != null) {
            q.offer(root);
        }
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i ++) {
                TreeNode node = q.poll();
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
                if (node.left == node.right && node.left == null) {
                    return depth + 1;
                }
            }
            depth += 1;
        }
        return depth;
    }

    public int maxDepth(TreeNode root) {
        /*
         * TASK:   find the maximum depth of the tree
         *
         * METHOD: using queue to keep traversing and increment the depth, return the depth at the end
         *
         * NOTE:   None
         *
         * TIME/
         * SPACE:  O(depth) / O(2 ^ depth)
         */
        int depth = 0;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        if (root != null) {
            q.offer(root);
        }
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i ++) {
                TreeNode node = q.poll();
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            depth += 1;
        }
        return depth;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        /*
         * TASK:   connect each node in each layer ( not full binary tree)
         *
         * METHOD: imagine each layer is like a linked nodes, so for each layer we create a dummy node (pre)
         *         and with the help of the cur (located at the previous layer), we can connect the nodes
         *         by moving the pre forwards until cur is equal to null ( reaches the end of previous layer)
         *
         * NOTE:   None
         *
         * TIME/
         * SPACE:  O(2 ^ depth) / O(depth)
         */
        if (root == null) return null;
        Node pre = new Node();
        Node cur = root;
        while (cur != null) {
            if (cur.left != null) {
                pre.next = cur.left;
                pre = pre.next;
            }
            if (cur.right != null) {
                pre.next = cur.right;
                pre = pre.next;
            }
            cur = cur.next;
        }
        connect(root.left);
        connect(root.right);
        return root;
    }

    public Node connect2(Node root) {
        /*
         * TASK:   connect each node in each layer ( must be full binary tree)
         *
         * METHOD: using magic of recursive method.
         *
         * NOTE:   None
         *
         * TIME/
         * SPACE:  O(2 ^ depth) / O(depth)
         */
        if (root == null) return null;
        if (root.left != null) {
            root.left.next = root.right;
            if (root.next != null) {
                root.right.next = root.next.left;
            }
        }
        connect2(root.left);
        connect2(root.right);
        return root;
    }

    public List<Integer> largestValues(TreeNode root) {
        /*
         * TASK:   find the maximum number in each layer of a tree
         *
         * METHOD: using queue to traverse the tree and keep record of each maximum number in each layer
         *
         * NOTE:   None
         *
         * TIME/
         * SPACE:  O(2 ^ depth) / O(2 ^ depth)
         */
        List<Integer> result = new ArrayList<Integer>();
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        if (root != null) q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i ++) {
                TreeNode node = q.poll();
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
                max = Math.max(node.val, max);
            }
            result.add(max);
        }
        return result;
    }

    class Node2 {
        public int val;
        public List<Node2> children;

        public Node2() {}

        public Node2(int _val) {
            val = _val;
        }

        public Node2(int _val, List<Node2> _children) {
            val = _val;
            children = _children;
        }
    }

    public List<List<Integer>> levelOrder(Node2 root) {
        /*
         * TASK:   traverse a tree with N branches
         *
         * METHOD: using queue to traverse the tree
         *
         * NOTE:   None
         *
         * TIME/
         * SPACE:  O(2 ^ depth) / O(2 ^ depth)
         */
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Queue<Node2> q = new LinkedList<Node2>();
        if (root != null) q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> layer = new ArrayList<Integer>();
            for (int i = 0; i < size; i ++) {
                Node2 node = q.poll();
                for (Node2 child: node.children) {
                    if (child != null) q.offer(child);
                }
                layer.add(node.val);
            }
            result.add(layer);
        }
        return result;
    }

    public List<Double> averageOfLevels(TreeNode root) {
        /*
         * TASK:   find the average of each layer
         *
         * METHOD: using queue to traverse the tree
         *
         * NOTE:   None
         *
         * TIME/
         * SPACE:  O(2 ^ depth) / O(2 ^ depth)
         */
        List<Double> result_l = new ArrayList<Double>();
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        if (root != null) q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            double sum = 0;
            for (int i = 0; i < size; i ++) {
                TreeNode node = q.poll();
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
                sum += Double.valueOf(node.val);
            }
            result_l.add(sum / Double.valueOf(size));
        }
        return result_l;
    }

    public List<Integer> rightSideView(TreeNode root) {
        /*
         * TASK:   find the "view" from the right side of tree, return a list
         *
         * METHOD: using queue to traverse the tree, but only keep record of the last node in each layer
         *
         * NOTE:   None
         *
         * TIME/
         * SPACE:  O(2 ^ depth) / O(2 ^ depth)
         */
        List<Integer> result = new ArrayList<Integer>();
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        if (root != null) q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            TreeNode node = null;
            for (int i = 0; i < size; i ++) {
                node = q.poll();
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
            result.add(node.val);
        }
        return result;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        /*
         * TASK:   traverse the tree
         *
         * METHOD: using teh queue to traverse
         *
         * NOTE:   None
         *
         * TIME/
         * SPACE:  O(2 ^ depth) / O(2 ^ depth)
         */
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root != null) q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size(); // ! important
            List<Integer> layer = new ArrayList<>();
            for (int i = 0; i < size; i++) { // ! important
                TreeNode node = q.poll();
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
                layer.add(node.val);
            }
            result.add(layer);
        }
        return result;
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        /*
         * TASK:   traverse the tree from bottom layer to top layer
         *
         * METHOD: using teh queue to traverse, but reverse the final result list
         *
         * NOTE:   None
         *
         * TIME/
         * SPACE:  O(2 ^ depth) / O(2 ^ depth)
         */
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        if (root != null) q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> layer = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
                layer.add(node.val);
            }
            result.add(layer);
        }
        Collections.reverse(result);
        return result;
    }

    public TreeNode invertTree(TreeNode root) {
        /*
         * TASK:   reverse the left node and right node of each node in a tree
         *
         * METHOD: 1. (this one) use recursive method to traverse the tree
         *         2. use stack to simulate the recursive process
         *
         * NOTE:   None
         *
         * TIME/
         * SPACE:  O(2 ^ depth) / O(2 ^ depth)
         */
        if (root == null) return null;
        swap(root);
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    void swap(TreeNode r) {
        TreeNode t = r.right;
        r.right = r.left;
        r.left = t;
        return;
    }

    class Node3 {
        public int val;
        public List<Node3> children;

        public Node3() {}

        public Node3(int _val) {
            val = _val;
        }

        public Node3(int _val, List<Node3> _children) {
            val = _val;
            children = _children;
        }
    };

    public List<Integer> preorder_N(Node3 root) {
        /*
         * TASK:   traverse the N-nary tree with preorder
         *
         * METHOD: 1. use recursive method to traverse the tree
         *         2. (this one) use stack to simulate the recursive process
         *
         * NOTE:   None
         *
         * TIME/
         * SPACE:  O(2 ^ depth) / O(2 ^ depth)
         */
        Stack<Node3> stk = new Stack<Node3>();
        List<Integer> result = new ArrayList<Integer>();
        if (root != null) stk.push(root);
        while (!stk.isEmpty()) {
            Node3 node = stk.pop();
            if (node != null) {
                for (int i = node.children.size() - 1; i >= 0; i --) {
                    if (node.children.get(i) != null) {
                        stk.push(node.children.get(i));
                    }
                }
                stk.push(node);
                stk.push(null);
            } else {
                result.add(stk.pop().val);
            }
        }
        return result;
    }

    public List<Integer> postorder(Node3 root) {
        /*
         * TASK:   traverse the N-nary tree with postorder
         *
         * METHOD: 1. use recursive method to traverse the tree
         *         2. (this one) use stack to simulate the recursive process
         *
         * NOTE:   None
         *
         * TIME/
         * SPACE:  O(2 ^ depth) / O(2 ^ depth)
         */
        Stack<Node3> stk = new Stack<Node3>();
        List<Integer> result = new ArrayList<Integer>();
        if (root != null) stk.push(root);
        while (!stk.isEmpty()) {
            Node3 node = stk.pop();
            if (node != null) {
                stk.push(node);
                stk.push(null);
                for (int i = node.children.size() - 1; i >= 0; i --) {
                    if (node.children.get(i) != null) {
                        stk.push(node.children.get(i));
                    }
                }
            } else {
                result.add(stk.pop().val);
            }
        }
        return result;
    }
}

