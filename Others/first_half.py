import time

# Multiplication using multiplication by 2 - O(n)/O(1) - O(logn)/O(1)
def mul(x, y):
    res = 0
    while y != 0:
        y -= 1
        res += x
    print(res)
    return res

def mul_i(x, y):
    res = 0
    while y != 1:
        if (y % 2 == 1):
            y = y - 1
            res += x
        y /= 2
        x *= 2
    res += x
    print(res)
    return res
        
# Greatest common divisor - O(n)/O(1) - O(?)/O(1) - O(?)/O(1)

def GCD(x, y):
    start = time.time()
    m = min(x, y)
    for i in range(m, 0, -1):
        if x % i == y % i == 0:
            print(i)
            print(time.time() - start)
            return i

def GCD_r(x, y):
    start = time.time()
    while (x != y):
        if x >= y:
            x -= y
        else:
            y -= x
    print(x)
    print(time.time() - start)
    return x

def GCD_d(x, y):
    start = time.time()
    m = max(x, y)
    n = min(x, y)
    m = m % n
    if m == 0:
        print(n)
        print(time.time() - start)
        return n
    else:
        return GCD_d(m, n)

# Fibonacci sequence - recursive

def fibs(n):
    if n <= 2:
        return 1
    else:
        return fibs(n - 1) + fibs(n - 2)

# mem = [0] * 300
# mem[0] = 1
# mem[1] = 1
# def fibs_mem(n):
#     if mem[n - 1] != 0:
#         return mem[n - 1]
#     else:
#         i = fibs_mem(n - 1)
#         mem[n - 2] = i
#         j = fibs_mem(n - 2)
#         mem[n - 3] = j
#         return i + j


# Insertion sort - O(n ^ 2)/O(1)

def insertionSort(nums):
    n = len(nums)
    for i in range(1, n):
        t = nums[i]
        j = i
        while j >= 0 and nums[j] >= t:
            nums[j] = nums[j - 1]
            j -= 1
        nums[j + 1] = t
    return nums        

# Binary tree traversal - preorder traversal - inorder traversal - postorder traversal

# Finding minimum spanning trees - Kruskal’s algorithm - Prim’s algorithm


adj_matrix = [[0, 1, 999, 4, 999, 999, 999], 
              [0, 0, 2, 6, 4, 999, 999],
              [0, 0, 0, 999, 5, 6, 999],
              [0, 0, 0, 0, 3, 999, 4],
              [0, 0, 0, 0, 0, 8, 7],
              [0, 0, 0, 0, 0, 0, 3],
              [0, 0, 0, 0, 0, 0, 0]]

for i in range(7):
    for j in range(0, i):
        adj_matrix[i][j] = adj_matrix[j][i]

# print(adj_matrix)

def kruskal(m):
    arcs_comp = []
    while arcs_comp != [[0, 1, 2, 3, 4, 5, 6]]:
        min = 99
        for i in range(0, 7): # avoid diagonal
            icomp = find(i, arcs_comp)
            for j in range(i + 1, 7):
                jcomp = find(j, arcs_comp)
                if icomp != jcomp and m[i][j] < min:
                    min = m[i][j]
                    t_icomp = icomp
                    t_jcomp = jcomp
        merg(t_icomp, t_jcomp, arcs_comp)
        print(arcs_comp)
    return arcs_comp

def find(k, arcs_comp):
    for comp in arcs_comp:
        if k in comp:
            return comp
    return [k]

def merg(ic, jc, arcs_comp):
    t = ic + jc
    t.sort()
    arcs_comp.append(t)
    if ic in arcs_comp:
        arcs_comp.remove(ic)
    if jc in arcs_comp:
        arcs_comp.remove(jc)

def prim(m):
    nearest = [999] * 7 # nearest[i] represents i's nearest node to the comp
    minDist = [999] * 7 # minimum distance of i to the comp
    for i in range(1, 7):
        nearest[i] = 0
        minDist[i] = m[0][i]
    for _ in range(6):
        min = 99
        j = -1
        for i in range(1, 7):
            if 0 < minDist[i] < min:
                min = minDist[i]
                j = i
        minDist[j] = -1
        for node in range(1, 7):
            if m[node][j] < minDist[node]:
                minDist[node] = m[node][j]
                nearest[node] = j
    print(minDist)
    print(nearest)

# Minumum distance to the designated node - Dijkstra’s algorithm

adj_matrix2 = [[0, 50, 30, 100, 10],
               [999, 0, 999, 999, 999],
               [999, 5, 0, 50, 999],
               [999, 20, 999, 0, 999],
               [999, 999, 999, 10, 0]] # directed graph

def dijkstra(m, node):
    node -= 1
    dist = [0] * 5 # minimum distance to the component for node i
    nodes = [0] * 5
    nodes[node] = 1
    for i in range(5):
        dist[i] = m[node][i]
    for _ in range(3):
        mini = 99
        min_n = -1
        for i in range(5):
            if 0 < dist[i] < mini and nodes[i] == 0:
                mini = dist[i]
                min_n = i
        nodes[min_n] = 1
        for i in range(5):
            if dist[min_n] + m[min_n][i] < dist[i] and nodes[i] == 0:
                dist[i] = dist[min_n] + m[min_n][i]
    print(dist)

# difference between prim and dijkstra:
# 1. the purpose of the algorithm
# 2. both have a D, but definition is different
# 3. the way to update the D is different

# binary serach

def binary_search(nums, target):
    low = 0
    high = len(nums)
    while low < high:
        mid = (low + high) // 2
        if nums[mid] == target:
            return mid
        elif nums[mid] < target:
            low = mid + 1
        elif nums[mid] > target:
            high = mid - 1
    return low + 1

# MergeSort

def mergesort(nums):
    return merge_sort(nums, 0, len(nums) - 1)

def merge_sort(nums, low, high):
    if low == high:
        return [nums[low]]
    mid = (low + high) // 2
    U = merge_sort(nums, low, mid)
    L = merge_sort(nums, mid + 1, high)
    return merge(U, L)

def merge(l1, l2):
    n1 = len(l1)
    n2 = len(l2)
    res = []
    i = 0
    j = 0
    while i < n1 and j < n2:
        if l1[i] < l2[j]:
            res.append(l1[i])
            i += 1
        else:
            res.append(l2[j])
            j += 1
    if i < n1:
        res += l1[i:]
    if j < n2:
        res += l2[j:] 
    return res
            
import random
# quick sort

def quick_sort(nums, low, high):
    if low == high or low > high:
        return
    p = random.randint(low, high)
    pivot = nums[p]
    i = low
    j = low
    while j <= high:
        if nums[j] < pivot:
            temp = nums[i]
            nums[i] = nums[j]
            nums[j] =temp
            if i == p:
                p = j  # record if the pivot is swapped
            i += 1
        j += 1
    temp = nums[i]
    nums[i] = nums[p]
    nums[p] = temp
    quick_sort(nums, low, i - 1)
    quick_sort(nums, i + 1, high)


def quicksort(nums):
    quick_sort(nums, 0, len(nums) - 1)
    return nums

# Calculating binomial coefficients

def binomial(n, t):
    bio_arr = [0] * (n + 1)
    bio_arr[0] = 1
    c = 0
    while c < n:
        for i in range(n, 0, -1):
            bio_arr[i] += bio_arr[i - 1]
        c += 1
    print(bio_arr)
    return bio_arr[t]

# Floyd’s algorithm

adj_matrix3 = [[0, 3, 999, 4, 999],
               [999, 0, 4, 2, 1],
               [999, 999, 0, 999, 999],
               [4, 999, 999, 0, 3],
               [999, 999, 2, 2, 0]] # directed graph

def floyd(m):
    size = 5
    p = [[0] * size for _ in range(size)]
    for k in range(size):
        for i in range(size):
            for j in range(size):
                if m[i][k] + m[k][j] < m[i][j]:
                    m[i][j] = m[i][k] + m[k][j]
                    p[i][j] = k + 1 # !careful
    print(m)
    print(p)
# Warshall’s algorithm

def Warshall(m):
    size = 5
    p = [[0] * 5 for _ in range(size)]
    for i in range(size):
        for j in range(size):
            p[i][j] = 1 if m[i][j] != 999 else 0
    for i in range(size):
        for j in range(size):
            if p[i][j] == 1 and i != j:
                for k in range(size):
                    p[i][k] = p[i][k] or p[j][k]
    print(p)

# Depth-first search

adj_matrix4 = [[0, 1, 1, 1, 999, 999, 999, 999], 
              [1, 0, 1, 999, 1, 1, 999, 999],
              [1, 1, 0, 999, 999, 1, 999, 999],
              [1, 999, 999, 0, 999, 999, 1, 1],
              [999, 1, 999, 999, 0, 1, 999, 999],
              [999, 1, 1, 999, 1, 0, 999, 999],
              [999, 999, 999, 1, 999, 999, 0, 1],
              [999, 999, 999, 1, 999, 999, 1, 0]]

def search(m):
    mark = [0] * 8
    for i in range(8):
        if mark[i] != 1:
            Depth_first(m, i, mark)


def Depth_first(m, root, mark):
    stack = list()
    mark[root] = 1
    size = 8
    stack.append(root)
    # print(root + 1)
    while len(stack) != 0:
        u = stack[-1]
        print(stack.pop() + 1)
        for i in range(7, -1, -1):
            if i != u and m[i][u] == 1 and mark[i] == 0:
                stack.append(i)
                mark[i] = 1

# Breadth-first search

def search2(m):
    mark = [0] * 8
    for i in range(8):
        if mark[i] != 1:
            Breadth_first(m, i, mark) # if the graph's nodes are all connected, this loop will enter once


def Breadth_first(m, root, mark):
    queue = list()
    mark[root] = 1
    # print(root + 1)
    queue.append(root)
    while len(queue) != 0:
        u = queue[0]
        print(queue.pop(0))
        for i in range(8):
            if i != u and m[i][u] == 1 and mark[i] == 0:
                queue.append(i)
                mark[i] = 1
        

# GCD(152349845, 123131312354646980)
# GCD_d(152349845, 123131312354646980)
# GCD_r(152349845, 123131312354646980)

# print(insertionSort([23,3242, 24, 35, 7657, 32, 5345, 123, 43634]))

# kruskal(adj_matrix)

# dijkstra(adj_matrix2, 1)

# print(binary_search([1, 2, 4, 5, 6, 7, 8, 8, 9, 11], 3))

# print(mergesort([1, 9,2, 5, 7, 2,5, 6,8]))

# print(quicksort([1, 9,2, 5, 7, 2,5, 6,8]))

# binomial(9, 3)

# floyd(adj_matrix3)

# Warshall(adj_matrix3)

search(adj_matrix4)