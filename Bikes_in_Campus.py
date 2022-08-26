# Time Complexity : O(mn);
# Space Complexity : O(mn);
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
#
#
import sys
from collections import defaultdict


class Solution:
    def distance(self, x, y):
        return abs(x[0] - y[0]) + abs(x[1] - y[1])

    def assignBikes(self, workers, bikes):
        dictu = defaultdict(list)
        mini = sys.maxsize
        maxi = -sys.maxsize
        for i in range(len(workers)):
            for j in range(len(bikes)):
                distance = self.distance(workers[i], bikes[j])
                dictu[distance].append([i, j])
                mini = min(mini, distance)
                maxi = max(maxi, distance)
        bikesV = [True] * len(bikes)
        workersV = [True] * len(workers)
        result = [0] * len(workers)
        count = 0
        for i in range(mini, maxi+1):
            if i in dictu:
                for j in dictu[i]:
                    if workersV[j[0]] and bikesV[j[1]]:
                        result[j[0]] = j[1]
                        count += 1
                        bikesV[j[1]] = False
                        workersV[j[0]] = False
                    if count == len(workers):
                        return result


print(Solution().assignBikes([[0, 0], [2, 1]], [[1, 2], [3, 3]]))

# TC: O((m*n)log(m*n)); SC: O(m*n)
# import heapq
#
#
# class Solution:
#     def distance(self, x, y):
#         return abs(x[0] - y[0]) + abs(x[1] - y[1])
#
#     def assignBikes(self, workers, bikes):
#         heap = []
#         for i in range(len(workers)):
#             for j in range(len(bikes)):
#                 distance = self.distance(workers[i], bikes[j])
#                 heapq.heappush(heap, (distance, [i, j]))
#         bikesV = [True] * len(bikes)
#         workersV = [True] * len(workers)
#         result = [0] * len(workers)
#         count = 0
#         while count != len(workers):
#             temp, pop = heapq.heappop(heap)
#             if workersV[pop[0]] and bikesV[pop[1]]:
#                 result[pop[0]] = pop[1]
#                 count += 1
#                 bikesV[pop[1]] = False
#                 workersV[pop[0]] = False
#         return result
#
#
# print(Solution().assignBikes([[0, 0], [2, 1]], [[1, 2], [3, 3]]))
