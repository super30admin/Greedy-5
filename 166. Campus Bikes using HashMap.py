from typing import (
    List,
)
# from sortedcontainers import sortedDict
# from sortedcontainers import SortedDict


class Solution:
    """
    @param workers: workers' location
    @param bikes: bikes' location
    @return: assign bikes
    """

    def assign_bikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        # write your code here
        if workers is None or len(workers) == 0 or bikes is None or len(bikes) == 0:
            return []

        m = len(workers)
        n = len(bikes)
        # sd = SortedDict({})
        hashMap = dict()
        minimum = float('inf')
        maximum = float("-inf")

        for i in range(m):
            for j in range(n):
                worker = workers[i]
                bike = bikes[j]
                dist = self.calculateDist(worker, bike)
                minimum = min(minimum, dist)
                maximum = max(maximum, dist)
                li = hashMap.get(dist, [])
                li.append([i, j])
                hashMap[dist] = li
        assigned = [False] * m
        alloted = [False] * n
        result = [0] * m
        count = 0

        for dist in range(minimum, maximum + 1):
            li = hashMap.get(dist)
            if not li:
                continue
            for wb in li:
                currW = wb[0]
                currB = wb[1]
                if not assigned[currW] and not alloted[currB]:
                    assigned[currW] = True
                    alloted[currB] = True
                    result[currW] = currB
                    count += 1
                if count == m:
                    return result
        return result

    def calculateDist(self, worker, bike):
        dist = abs(worker[0] - bike[0]) + abs(worker[1] - bike[1])
        return dist

# Greedy
# Time Complexity:(mn)
# Space Complexity: O(n)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
