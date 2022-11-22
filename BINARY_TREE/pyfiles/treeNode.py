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
