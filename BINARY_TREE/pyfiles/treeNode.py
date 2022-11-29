from math import inf
from typing import Optional, List


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


def findBottomLeftValue(self, root: Optional[TreeNode]) -> int:
    #    /*
    #     * TASK:   find the bottom and leftmost value in a tree
    #     *
    #     * METHOD: using breadth first search and update the val with the first value in every layer
    #     *
    #     * NOTE:   None
    #     *
    #     * TIME/
    #     * SPACE:  O(N) / O(logn)
    #     */
    q = list()
    val = -inf
    if root is not None:
        q.append(root)
    while q != list():
        size = len(q)
        for i in range(0, size):
            node = q.pop(0)
            if i == 0:
                val = node.val
            if node.left: q.append(node.left)
            if node.right: q.append(node.right)
    return val


def sumOfLeftLeaves(self, root: Optional[TreeNode]) -> int:
    #    /*
    #     * TASK:   find the sum of all left leaves in a tree
    #     *
    #     * METHOD: using traverse, pass a bool value named 'left_flag', only true when traversing a left child
    #     *         pass an argument sum to keep track of accumulated sum, when encounter a left leave, return
    #               the accumulated sum
    #     *
    #     * NOTE:   None
    #     *
    #     * TIME/
    #     * SPACE:  O(N) / O(logn)
    #     */
    return self.traverse(root, 0, False)


def traverse(self, root, sum, left_flag) -> int:
    if root.left is None and root.right is None and left_flag:
        return sum + root.val
    left = right = 0
    if root.left is not None: left = self.traverse(root.left, sum, True)
    if root.right is not None: right = self.traverse(root.right, sum, False)
    return left + right


def closestNodes(self, root: Optional[TreeNode], queries: List[int]) -> List[List[int]]:
    #    /*
    #     * TASK:   see: https://leetcode.cn/problems/closest-nodes-queries-in-a-binary-search-tree/
    #     *
    #     * METHOD: using inorder traverse to get the ordered traversed tree, and use that to binary search
    #               each query value
    #     *
    #     * NOTE:   None
    #     *
    #     * TIME/
    #     * SPACE:  O(NlogN) / O(N)
    #     */
    stack = list()
    traversed = list()
    if root is not None:
        stack.append(root)
    while len(stack) != 0:
        node = stack.pop()
        if node is not None:
            if node.right is not None: stack.append(node.right)
            stack.append(node)
            stack.append(None)
            if node.left is not None: stack.append(node.left)
        else:
            traversed.append(stack.pop().val)
    # print(traversed)
    n = len(queries)
    res = [[-1, -1] for i in range(n)]
    for i in range(n):
        # print(queries[i])
        # print(res)
        left = 0
        right = len(traversed) - 1
        while left <= right:
            mid = (left + right) // 2
            if traversed[mid] == queries[i]:
                res[i][1] = traversed[mid]
                res[i][0] = traversed[mid]
                break
            elif traversed[mid] > queries[i]:
                res[i][1] = traversed[mid]
                right = mid - 1

            elif traversed[mid] < queries[i]:
                res[i][0] = traversed[mid]
                left = mid + 1
    return res


def buildTree(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:
    #    /*
    #     * TASK:   build a tree from preorder and inorder traversed list
    #     *
    #     * METHOD: remember that the first node in the preorder is the 'root' node, and the right of that node
    #               in the inorder list is the right child of root, the left of that node in the inorder list
    #               is the left child of root
    #
    #     * NOTE:   first construct left child, then construct right child
    #     *
    #     * TIME/
    #     * SPACE:  O(N) / O(N)
    #     */
    def helper(in_left: int, in_right: int) -> TreeNode:
        if in_left > in_right:
            return None
        val = preorder.pop(0)
        root = TreeNode(val)
        index = ind_map[val]
        root.left = helper(in_left, index - 1)
        root.right = helper(index + 1, in_right)
        return root

    ind_map = {val: ind for ind, val in enumerate(inorder)}
    return helper(0, len(inorder) - 1)


def buildTree2(self, inorder: List[int], postorder: List[int]) -> Optional[TreeNode]:
    #    /*
    #     * TASK:   build a tree from postorder and inorder traversed list
    #     *
    #     * METHOD: remember that the final node in the postorder is the 'root' node, and the right of that node
    #               in the inorder list is the right child of root, the left of that node in the inorder list
    #               is the left child of root
    #
    #     * NOTE:   first construct right child, then construct left child
    #     *
    #     * TIME/
    #     * SPACE:  O(N) / O(N)
    #     */
    def helper(in_left: int, in_right: int) -> TreeNode:
        if in_left > in_right:
            return None
        # print(postorder)
        val = postorder.pop()
        root = TreeNode(val)
        index = ind_map[val]
        root.right = helper(index + 1, in_right)
        root.left = helper(in_left, index - 1)
        return root

    ind_map = {val: ind for ind, val in enumerate(inorder)}
    return helper(0, len(inorder) - 1)


def mergeTrees(root1: Optional[TreeNode], root2: Optional[TreeNode]) -> Optional[TreeNode]:
    #    /*
    #     * TASK:   merge two trees on the same position (add the numbers on the same position)
    #     *
    #     * METHOD: see code
    #
    #     * NOTE:   when one of the node is null return the other
    #     *
    #     * TIME/
    #     * SPACE:  O(N) / O(logN)
    #     */
    if root1 is None and root2 is None:
        return
    left = right = 0
    if root2 is not None:
        right = root2.val
    else:
        return root1
    if root1 is not None:
        left = root1.val
        root1.val = left + right
    else:
        return root2
    root1.left = mergeTrees(root1.left, root2.left)
    root1.right = mergeTrees(root1.right, root2.right)
    return root1


def constructMaximumBinaryTree(nums: List[int]) -> Optional[TreeNode]:
    #    /*
    #     * TASK:   given an array, find the largest number as root, right of the number as right child,
    #                 left of the number as the left child to construct a tree
    #     *
    #     * METHOD: see code
    #
    #     * NOTE:   to operate on the original array, use left and right to delimit the range
    #     *
    #     * TIME/
    #     * SPACE:  O(N) / O(logN)
    #     */
    return traverse2(nums, 0, len(nums) - 1)


def traverse2(nums: List[int], left: int, right: int) -> TreeNode:
    #    /*
    #     * TASK:   judge if it is a binary search tree (all the nodes on the left is less than current val
    #               all the nodes on the right is greater than current val, and children subtrees are also BST)
    #     *
    #     * METHOD: use inorder traverse, judge if the traversed array is monotonically increasing
    #
    #     * NOTE:   None
    #     *
    #     * TIME/
    #     * SPACE:  O(N) / O(logN)
    #     */
    if left > right:
        return None
    max_index = left
    for i in range(left, right + 1):
        if nums[i] > nums[max_index]:
            max_index = i
    node = TreeNode(nums[max_index])
    node.left = traverse2(nums, left, max_index - 1)
    node.right = traverse2(nums, max_index + 1, right)
    return node


def isValidBST(self, root: Optional[TreeNode]) -> bool:
    stack = list()
    res = list()
    if root is not None:
        stack.append(root)
    while len(stack) != 0:
        node = stack.pop()
        if node is not None:
            if node.right is not None: stack.append(node.right)
            stack.append(node)
            stack.append(None)
            if node.left is not None: stack.append(node.left)
        else:
            res.append(stack.pop().val)
    n = len(res)
    for i in range(n):
        if i > 0 and res[i - 1] >= res[i]:
            return False
    return True


def searchBST(self, root: Optional[TreeNode], val: int) -> Optional[TreeNode]:
    #    /*
    #     * TASK:   find the subtree whose root is val in a BST
    #     *
    #     * METHOD: use characteristics of BST to find the answer
    #
    #     * NOTE:   None
    #     *
    #     * TIME/
    #     * SPACE:  O(logN) / O(logN)
    #     */
    if root is None:
        return None
    if val == root.val:
        return root
    elif val < root.val:
        return self.searchBST(root.left, val)
    elif val > root.val:
        return self.searchBST(root.right, val)


def minDiffInBST(self, root: Optional[TreeNode]) -> int:
    #    /*
    #     * TASK:   find the minimum difference between any node in a BST
    #     *
    #     * METHOD: use inorder traverse and find the minimum diff in the array
    #
    #     * NOTE:   None
    #     *
    #     * TIME/
    #     * SPACE:  O(N) / O(logN)
    #     */
    stack = list()
    res = list()
    if root != None: stack.append(root)
    while len(stack) != 0:
        node = stack.pop()
        if node != None:
            if node.right != None: stack.append(node.right)
            stack.append(node)
            stack.append(None)
            if node.left != None: stack.append(node.left)
        else:
            res.append(stack.pop().val)
    print(res)
    n = len(res)
    m = inf
    for i in range(n):
        if i > 0:
            m = min(m, res[i] - res[i - 1])
    return m


def findMode(self, root: Optional[TreeNode]) -> List[int]:
    #    /*
    #     * TASK:   find the mode(s) in a BST
    #     *
    #     * METHOD: use inorder traverse and find the mode(s) in the array
    #
    #     * NOTE:   None
    #     *
    #     * TIME/
    #     * SPACE:  O(N) / O(logN)
    #     */
    stack = list()
    res = list()
    if root is not None: stack.append(root)
    while len(stack) != 0:
        node = stack.pop()
        if node is not None:
            if node.right is not None: stack.append(node.right)
            stack.append(node)
            stack.append(None)
            if node.left is not None: stack.append(node.left)
        else:
            res.append(stack.pop().val)
    n = len(res)
    freq = {}
    for i in range(n):
        if res[i] in freq.keys():
            freq[res[i]] += 1
        else:
            freq[res[i]] = 0
    m = max(list(freq.values()))
    ans = list()
    for num, freq in freq.items():
        if freq == m:
            ans.append(num)
    return ans


def insertIntoBST(self, root: Optional[TreeNode], val: int) -> Optional[TreeNode]:
    #    /*
    #     * TASK:   insert a node in a BST tree
    #     *
    #     * METHOD: search the BST until encounter null node, place the node there
    #
    #     * NOTE:   None
    #     *
    #     * TIME/
    #     * SPACE:  O(logN) / O(logN)
    #     */
    if root is None: return TreeNode(val)
    cur = parent = root
    while cur is not None:
        parent = cur
        if val < cur.val:
            cur = cur.left
        else:
            cur = cur.right
    if val < parent.val:
        parent.left = TreeNode(val)
    else:
        parent.right = TreeNode(val)
    return root


def deleteNode(self, root: Optional[TreeNode], key: int) -> Optional[TreeNode]:
    #    /*
    #     * TASK:   delete a node in a BST tree
    #     *
    #     * METHOD: traverse the BST while noticing five circumstances: 1. if current node is null, return null
    #               2. left and right are both null, return null (remove this node) 3. left is null, return right
    #               4. right is null, return left 5. left and right both are not null, first find the left most node
    #               in the right subtree of root, and assign the left subtree of root to that node's left child. the
    #               reason is that this left most node is the node whose value is least larger than root, so the left
    #               subtree can be moved to be the node's left child safely. after the assignment, only return
    #               root.right
    #
    #     * NOTE:   None
    #     *
    #     * TIME/
    #     * SPACE:  O(logN) / O(logN)
    #     */
    if root is None: return None
    if root.val == key:
        if root.left is None and root.right is None:
            return None
        elif root.left is None:
            return root.right
        elif root.right is None:
            return root.left
        else:
            cur = root.right
            while cur.left is not None:
                cur = cur.left
            cur.left = root.left
            root = root.right
            return root
    if key < root.val: root.left = self.deleteNode(root.left, key)
    if key > root.val: root.right = self.deleteNode(root.right, key)
    return root


def convertBST(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
    #    /*
    #     * TASK:   see link: https://leetcode.cn/problems/convert-bst-to-greater-tree/submissions/385930604/
    #     *
    #     * METHOD: traverse the BST tree with the order - right - mid - left, while maintaining a 'sum' at the same
    #               time
    #
    #     * NOTE:   None
    #     *
    #     * TIME/
    #     * SPACE:  O(N) / O(logN)
    #     */
    sum1 = 0
    stack = list()
    if root is not None: stack.append(root)
    while len(stack) != 0:
        node = stack.pop()
        if node is not None:
            if node.left is not None: stack.append(node.left)
            stack.append(node)
            stack.append(None)
            if node.right is not None: stack.append(node.right)
        else:
            r = stack.pop()
            sum1 += r.val
            r.val = sum1
    return root


def sortedArrayToBST(self, nums: List[int]) -> Optional[TreeNode]:
    #    /*
    #     * TASK:   build a BST from a sorted array, with balance
    #     *
    #     * METHOD: always get the mid of the array as the root, and right of mid as right child, left of the mid as
    #               the left child
    #
    #     * NOTE:   None
    #     *
    #     * TIME/
    #     * SPACE:  O(N) / O(logN)
    #     */
    return self.traverse1(nums, 0, len(nums) - 1)


def traverse1(self, nums, low, high) -> Optional[TreeNode]:
    if low <= high:
        mid = (low + high) // 2
        root = TreeNode(nums[mid])
        root.left = self.traverse1(nums, low, mid - 1)
        root.right = self.traverse1(nums, mid + 1, high)
        return root
    else:
        return None


def trimBST(self, root: Optional[TreeNode], low: int, high: int) -> Optional[TreeNode]:
    #    /*
    #     * TASK:   delete nodes that are not in the range in a BST tree
    #     *
    #     * METHOD: traverse the BST tree, if one node is outside the range, two situations to consider: 1. the node is
    #               less than the range, then all the nodes on the left subtree is also outside the range, return the
    #               right subtree; 2. the node is higher than the range, then all the nodes on the right subtree is also
    #               outside range, return left subtree
    #
    #     * NOTE:   None
    #     *
    #     * TIME/
    #     * SPACE:  O(N) / O(logN)
    #     */
    if root is None: return None
    if root.val < low: return self.trimBST(root.right, low, high)
    if root.val > high: return self.trimBST(root.left, low, high)
    root.left = self.trimBST(root.left, low, high)
    root.right = self.trimBST(root.right, low, high)
    return root
