def unequalTriplets(self, nums: List[int]) -> int:
    #    /*
    #     * TASK:   see: https://leetcode.cn/problems/number-of-unequal-triplets-in-array/
    #     *
    #     * METHOD: use triple loops to find them
    #     *
    #     * NOTE:   None
    #     *
    #     * TIME/
    #     * SPACE:  O(N^3) / O(1)
    #     */
    # nums.sort()
    n = len(nums)
    result = 0
    for i in range(n - 2):
        for j in range(i + 1, n - 1):
            if nums[j] == nums[i]:
                continue
            for k in range(j + 1, n):
                if nums[k] == nums[j] or nums[k] == nums[i]:
                    continue
                result += 1
    print(result)
    return result
