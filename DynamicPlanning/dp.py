def uniquePathsWithObstacles(self, obstacleGrid: List[List[int]]) -> int:
    # dp[i][j] means the number of ways to get position i, j
    # dp[i][j] = dp[i - 1][j] + dp[i][j - 1] if obstacleGrid[i][j] == 0
    # dp[i][j] = 0 if obstacleGrid[i][j] == 1
    # dp[0][0] = 0
    if obstacleGrid[0][0] == 1:
        return 0
    m = len(obstacleGrid)
    n = len(obstacleGrid[0])
    dp = [[0 for i in range(n)] for i in range(m)]
    dp[0][0] = 1
    for i in range(m):
        for j in range(n):
            if obstacleGrid[i][j] == 1:
                dp[i][j] = 0
            elif i == 0 and j != 0:
                dp[i][j] = dp[i][j - 1]
            elif j == 0 and i != 0:
                dp[i][j] = dp[i - 1][j]
            elif i != 0 and j != 0:
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
            # print(dp)
    return dp[m - 1][n - 1]


def integerBreak(self, n: int) -> int:
    # dp[i] means when it is i the largest multiplication value
    # dp[i] = max(dp[i], max(j * (i - j), j * dp[i - j]))
    # dp[2] = 1
    dp = [1 for i in range(n + 1)]
    for i in range(3, n + 1):
        for j in range(1, i // 2 + 1):
            dp[i] = max(dp[i], max(j * (i - j), j * dp[i - j]))
    return dp[n]


def numTrees(self, n: int) -> int:
    # dp[i] means when there are i nodes to constitute a binary search tree, how many ways are there
    # dp[i] += dp[j - 1] + dp[i - j]
    # dp[0] = 1
    dp = [0 for i in range(n + 1)]
    dp[0] = 1
    for i in range(1, n + 1):
        for j in range(1, i + 1):
            dp[i] += dp[j - 1] * dp[i - j]
    return dp[n]


def canPartition(self, nums: List[int]) -> bool:
    dp = [0 for i in range(10001)]
    target = sum(nums)
    if target % 2 == 1: return False
    target //= 2
    for i in range(len(nums)):
        for j in range(target, nums[i] - 1, -1):
            dp[j] = max(dp[j], dp[j - nums[i]] + nums[i])
    return dp[target] == target


def lastStoneWeightII(self, stones: List[int]) -> int:
    totalWeight = sum(stones)
    target = totalWeight // 2
    dp = [0] * (target + 1)
    for i in range(len(stones)):
        for j in range(target, stones[i] - 1, -1):
            dp[j] = max(dp[j], dp[j - stones[i]] + stones[i])
    return totalWeight - dp[target] - dp[target]


def findTargetSumWays(self, nums: List[int], target: int) -> int:
    if (sum(nums) + target) % 2 == 1:
        return 0
    if abs(target) > sum(nums):
        return 0
    left = int((sum(nums) + target) / 2)
    dp = [0] * (left + 1)
    dp[0] = 1
    for i in range(len(nums)):
        for j in range(left, nums[i] - 1, -1):
            dp[j] += dp[j - nums[i]]
    return dp[left]