from typing import Optional, List


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


def buildTree(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:
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



