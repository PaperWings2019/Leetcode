def lemonadeChange(self, bills: List[int]) -> bool:
    five = ten = 0
    for i in range(len(bills)):
        if bills[i] == 5: five += 1
        if bills[i] == 10:
            if five > 0:
                five -= 1
                ten += 1
            else:
                return False
        if bills[i] == 20:
            if five > 0 and ten > 0:
                five -= 1
                ten -= 1
            elif five > 2:
                five -= 3
            else:
                return False
    return True


def largestSumAfterKNegations(self, A: List[int], K: int) -> int:
    A = sorted(A, key=abs, reverse=True)  # 将A按绝对值从大到小排列
    for i in range(len(A)):
        if K > 0 and A[i] < 0:
            A[i] *= -1
            K -= 1
    if K > 0:
        A[-1] *= (-1) ** K  # 取A最后一个数只需要写-1
    return sum(A)


def candy(self, ratings: List[int]) -> int:
    n = len(ratings)
    candyVec = [1] * n
    for i in range(n):
        if i > 0 and ratings[i] > ratings[i - 1]:
            candyVec[i] = candyVec[i - 1] + 1
    for i in range(n - 1, -1, -1):
        if i < n - 1 and ratings[i] > ratings[i + 1]:
            candyVec[i] = max(candyVec[i], candyVec[i + 1] + 1)
    return sum(candyVec)


def reconstructQueue(self, people: List[List[int]]) -> List[List[int]]:
    n = len(people)
    for i in range(n):
        for j in range(n):
            if j > 0 and people[j][0] > people[j - 1][0]:
                t = people[j]
                people[j] = people[j - 1]
                people[j - 1] = t
            elif j > 0 and people[j][0] == people[j - 1][0]:
                if people[j][1] < people[j - 1][1]:
                    t = people[j]
                    people[j] = people[j - 1]
                    people[j - 1] = t
    print(people)
    queue = []
    for i in range(len(people)):
        queue.insert(people[i][1], people[i])
        # print(queue)
    return queue


def findMinArrowShots(self, points: List[List[int]]) -> int:
    n = len(points)
    if n == 0: return 0
    result = 1
    points.sort(key = lambda x: x[0])
    for i in range(1, n):
        if points[i][0] > points[i - 1][1]:
            result += 1
        else:
            points[i][1] = min(points[i][1], points[i - 1][1])
    return result


def eraseOverlapIntervals(self, intervals: List[List[int]]) -> int:
    n = len(intervals)
    if n == 0: return 0
    result = 0
    intervals.sort(key=lambda x: x[1])
    for i in range(1, n):
        if intervals[i][0] < intervals[i - 1][1]:
            intervals[i][1] = intervals[i - 1][1]
            result += 1
    return result


def partitionLabels(self, s: str) -> List[int]:
    dic = {}
    n = len(s)
    for i in range(n):
        dic[ord(s[i]) - ord('a')] = i
    result = []
    left = 0
    right = 0
    for i in range(n):
        right = max(right, dic[ord(s[i]) - ord('a')])
        if i == right:
            result.append(right - left + 1)
            left = right + 1
    return result


def merge(self, intervals: List[List[int]]) -> List[List[int]]:
    result = []
    n = len(intervals)
    if n == 0: return result
    intervals.sort(key = lambda x: x[0])
    start = intervals[0][0]
    end = intervals[0][1]
    for i in range(1, n):
        if intervals[i][0] <= intervals[i - 1][1]:
            end = max(intervals[i][1], intervals[i - 1][1])
            intervals[i][1] = end
        else:
            result.append([start, end])
            start = intervals[i][0]
            end = intervals[i][1]
    else:
        result.append([start, end])
        return result


def monotoneIncreasingDigits(self, n: int) -> int:
    a = list(str(n))
    for i in range(len(a)-1,0,-1):
        if int(a[i]) < int(a[i-1]):
            a[i-1] = str(int(a[i-1]) - 1)
            a[i:] = '9' * (len(a) - i)
        print(a)
    return int("".join(a))


def maxProfit(self, prices: List[int], fee: int) -> int:
    n = len(prices)
    result = 0
    minPrice = prices[0]
    for i in range(1, n):
        if prices[i] < minPrice: minPrice = prices[i]
        elif prices[i] > minPrice + fee:
            result += prices[i] - minPrice - fee
            minPrice = prices[i] - fee
    return result

def minCameraCover(self, root: Optional[TreeNode]) -> int:
    result = 0

    def traversal(root):
        nonlocal result
        if root is None:
            # 2 represents monitored
            return 2
        left = traversal(root.left)
        right = traversal(root.right)

        if left == 2 and right == 2:
            # 0 means not monitored
            return 0
        if left == 0 or right == 0:
            result += 1
            # 1 means camera installed
            return 1
        if left == 1 or right == 1:
            return 2

    if traversal(root) == 0:
        result += 1
    return result
