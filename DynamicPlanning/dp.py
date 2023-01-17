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