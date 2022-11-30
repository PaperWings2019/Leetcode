from typing import List


def combine(self, n: int, k: int) -> List[List[int]]:
    #    /*
    #     * TASK:   find all the combinations choosing k numbers from 1 to n
    #     *
    #     * METHOD: use the backtrack method to search for available combinations
    #
    #     * NOTE:   1. append res[:] instead of res, because 'res' here is address not content
    #               2. cutting branch: if the rest of numbers in the for loop is less than required k number, just break
    #                  loop.
    #     *
    #     * TIME/
    #     * SPACE:  O(C(n, k) * k) / O(n)
    #     */
    ans = []
    self.backtrack(n, k, [], ans)
    return ans


def backtrack(self, n: int, k: int, res: List, ans: List):
    if k == 0:
        ans.append(res[:])
        return
    for i in range(n, 0, -1):
        if n < k:
            break
        res.append(i)
        self.backtrack(i - 1, k - 1, res, ans)
        res.pop()


def combinationSum3(self, k: int, n: int) -> List[List[int]]:
    #    /*
    #     * TASK:   choose k numbers from 1 to 9 such that the sum is n, return all possible combinations
    #     *
    #     * METHOD: use the backtrack method to search for available combinations
    #
    #     * NOTE:   1. append res[:] instead of res, because 'res' here is address not content
    #               2. cutting branch: if current sum + i is larger than n, just break. since i grows larger afterwards
    #     *
    #     * TIME/
    #     * SPACE:  O(C(M, k) * k) / O(M) where M is the size of the collection
    #     */
    ans = []
    self.backtrack(k, n, [], ans, 1)
    return ans


def backtrack2(self, k: int, n: int, res: List, ans: List, start: int):
    t = sum(res)
    if k == 0 and t == n:
        ans.append(res[:])
    for i in range(start, 10):
        if t + i > n:
            break
        res.append(i)
        self.backtrack2(k - 1, n, res, ans, i + 1)
        res.pop()