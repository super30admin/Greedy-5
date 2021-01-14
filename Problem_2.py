"""
Time Complexity : O(mnlog(mn)) for heap solution and O(mn) for array solution. Though heap solution is running almost 4 times 
faster than array solution in python
Space Complexity : O(mn)- for the array storage or the heap
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No

Your code here along with comments explaining your approach:
Here, I have implemented both heap solution and the array solution. Firstly, we need to calculate the distance of all the workers
to all the bikes. Now, its upto us that we want to make a list of lists of size 2000, or we weant to make a heap with the distances ad
the worker and bike info. Afterwards, we just need to iterate through the array/ heap, allo cate bikes and workers and brealk whenever
all the workers have been assigned bikes.
"""
from heapq import heapify, heappush, heappop


class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        if not workers or not bikes:
            return []
        n = len(workers)
        m = len(bikes)
        grid = [None]*2001
        bikesAssigned = [False]*m
        result = [-1]*n
        count = 0
        for i in range(n):
            for j in range(m):
                distance = abs(workers[i][0]-bikes[j][0]) + \
                    abs(workers[i][1]-bikes[j][1])
                if not grid[distance]:
                    grid[distance] = []
                grid[distance].append([i, j])
        for i in range(2001):
            if grid[i]:
                listDistance = grid[i]
                for ld in listDistance:
                    if result[ld[0]] == -1 and not bikesAssigned[ld[1]]:
                        bikesAssigned[ld[1]] = True
                        result[ld[0]] = ld[1]
                        count += 1
                if count == n:
                    break
        return result


class Solution1:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        n = len(workers)
        m = len(bikes)
        distances = [[] for _ in range(n)]

        for i, [wx, wy] in enumerate(workers):
            for j, [bx, by] in enumerate(bikes):
                manhattan_distance = abs(wx - bx) + abs(wy - by)
                distances[i].append((manhattan_distance, i, j))
            heapify(distances[i])

        min_distance = []
        for i in range(n):
            manhattan_distance, wi, bj = heappop(distances[i])
            min_distance.append((manhattan_distance, wi, bj))
        heapify(min_distance)
        result = [-1] * n
        count = 0
        allocated = set()
        while count < n:
            manhattan_distance, wi, bj = heappop(min_distance)
            if bj in allocated:
                heappush(min_distance, heappop(distances[wi]))
            else:
                allocated.add(bj)
                result[wi] = bj
                count += 1
        return result
