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
