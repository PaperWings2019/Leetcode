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