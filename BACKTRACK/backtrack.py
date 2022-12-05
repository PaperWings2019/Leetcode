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


def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
    #    /*
    #     * TASK:   choose group of numbers in candidates such that the sum is target (all numbers are distinct, no
    #               repetitive groups, each number can be chosen infinite times), return all possible groups
    #     *
    #     * METHOD: use the backtrack method to search for available combinations, use a 'start' index to prevent
    #               choosing previous elements
    #
    #     * NOTE:   1. append res[:] instead of res, because 'res' here is address not content
    #               2. cutting branch: if current sum + i is larger than n, just break. since i grows larger afterwards
    #     *
    #     * TIME/
    #     * SPACE:  O(C(M, k) * k) / O(M) where M is the size of the collection
    #     */
    def backtrack(candidates: list, res: list, ans: list, start: int):
        t = sum(res)
        if t == target:
            ans.append(res[:])
            return
        for i in range(start, len(candidates)):
            num = candidates[i]
            if t + num > target:
                break
            res.append(num)
            backtrack(candidates, res, ans, start)
            res.pop()
            start += 1
    candidates.sort()
    ans = []
    backtrack(candidates, [], ans, 0)
    return ans


def letterCombinations(self, digits: str) -> List[str]:
    if not digits: return []
    m = {"2": ['a', 'b', 'c'], "3": ['d', 'e', 'f'], "4": ["g", "h", "i"], "5": ["j", "k", "l"],
         "6": ["m", "n", "o"],
         "7": ["p", "q", "r", "s"], "8": ["t", "u", "v"], "9": ["w", "x", "y", "z"]}
    digits_arr = list(digits)
    ans = []

    def backtrack(rest_num: list, res: str, ans: list):
        if len(rest_num) == 0:
            ans.append("".join(res))
            return
        for ch in m[rest_num[0]]:
            # print(rest_num)
            res.append(ch)
            t = rest_num.pop(0)
            backtrack(rest_num, res, ans)
            rest_num.insert(0, t)
            res.pop()
            # print(rest_num)

    backtrack(digits_arr, [], ans)
    return ans


def partition(self, s: str) -> List[List[str]]:
    n = len(s)
    def backtrack(startindex, path, res):
        if startindex >= n:
            res.append(path[:])
            return
        for i in range(startindex, n):
            if isPalin(startindex, i):
                path.append(s[startindex:i+1])
            else:
                continue
            backtrack(i + 1, path, res)
            path.pop()
    def isPalin(left, right):
        while left <= right:
            if s[left] != s[right]:
                return False
            left += 1
            right -= 1
        return True
    res = []
    backtrack(0, [], res)
    return res


def combinationSum2(self, candidates: List[int], target: int) -> List[List[int]]:
    def backtrack(candidates, res, ans):
        t = sum(res)
        if t == target:
            ans.append(res[:])
            return
        for i in range(len(candidates)):
            if i > 0 and candidates[i] == candidates[i - 1]:
                continue
            num = candidates[i]
            if num + t > target:
                break
            res.append(num)
            backtrack(candidates[i+1:], res, ans)
            res.pop()
    candidates.sort()
    ans = []
    backtrack(candidates, [], ans)
    return ans


def subsets(self, nums: List[int]) -> List[List[int]]:
    n = len(nums)
    def backtrack(startindex, res, cur):
        res.append(cur[:])
        for i in range(startindex, n):
            # backtrack(i + 1, res, cur)
            cur.append(nums[i])
            backtrack(i + 1, res, cur)
            cur.pop()
    res = []
    backtrack(0, res, [])
    return res


def restoreIpAddresses(self, s: str) -> List[str]:
    def backtrack(restdot, startindex, res, cur):
        if restdot == 0 and startindex == n:
            res.append(cur[0:-1])
            return
        if n - startindex > 3 * restdot:
            return
        for i in range(startindex + 1, n + 1):
            t = s[startindex:i]
            if i >= startindex + 4:
                breakß
            if int(t) == 0:
                backtrack(restdot - 1, i, res, cur + t + '.')
                break
            if int(t) > 255:
                break
            backtrack(restdot - 1, i, res, cur + t + '.')
    n = len(s)
    res = []
    backtrack(4, 0, res, '')
    return res


def findSubsequences(self, nums: List[int]) -> List[List[int]]:
    n = len(nums)
    def backtrack(res, cur, startindex):
        used_num = {}
        if len(cur) >= 2:
            res.append(cur[:])
        for i in range(startindex, n):
            if  (len(cur) >= 1 and nums[i] < cur[-1]) or nums[i] in used_num.keys():
                continue
            used_num[nums[i]] = True
            cur.append(nums[i])
            backtrack(res, cur, i + 1)
            cur.pop()
    res = []
    backtrack(res, [], 0)
    return res


def subsetsWithDup(self, nums: List[int]) -> List[List[int]]:
    n = len(nums)
    nums.sort()
    def backtrack(res, cur, startindex):
        # if startindex == n:
        #     res.append(cur[:])
        #     return
        res.append(cur[:])
        for i in range(startindex, n):
            # backtrack(res, cur, i + 1)
            if i > startindex and nums[i] == nums[i - 1]:
                continue
            cur.append(nums[i])
            backtrack(res, cur, i + 1)
            cur.pop()
    res = []
    backtrack(res, [], 0)
    return res