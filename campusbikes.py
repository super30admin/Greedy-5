# Time Complexity : O(M*N+(max_d-min_d)*k) where M is the number of workers and N is the number of bikes
# Space Complexity : O(M*N) for the distance map
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Your code here along with comments explaining your approach:
# I am calculating the Manhattan distance between each worker and bike and storing them in a distance map.
# Then I am iterating through the distance map in increasing order of distance and assigning bikes to workers
# if both are unassigned. I continue this until all workers are assigned bikes.
# I am using a defaultdict to store the distance map and two boolean arrays to keep track of assigned workers and bikes.
# Finally, I return the result array containing the bike assigned to each worker.

from typing import List
from collections import defaultdict
class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        distmap = defaultdict(list)
        min_d = float('inf')
        max_d = float('-inf')
        w = len(workers)
        b = len(bikes)
        def caldist(workers,bikes):
            return abs(workers[0]-bikes[0]) + abs(workers[1] - bikes[1])

        for i in range(w):
            for j in range(b):
                dist = caldist(workers[i],bikes[j])
                distmap[dist].append((i,j))
                min_d = min(min_d,dist)
                max_d = max(max_d,dist)
        res = [-1] * w
        workerassigned = [False] * w
        bikerassigned = [False] * b
        count = 0
        for d in range(min_d,max_d+1):
            if d not in distmap:
                continue
            for work,bike in distmap[d]:
                if not workerassigned[work] and not bikerassigned[bike]:
                    res[work] = bike
                    workerassigned[work] = True
                    bikerassigned[bike] = True
                    count += 1

                    if count == w:
                        return res
        return res

