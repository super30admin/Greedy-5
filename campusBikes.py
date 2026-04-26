from typing import (
    List,
)

class Solution:
    """
    @param workers: workers' location
    @param bikes: bikes' location
    @return: assign bikes
    """
    def assign_bikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        # write your code here
        hmap = dict() # distance : List[(worker_i, bike_i)]
        m = len(workers)
        n = len(bikes)
        minD = float('inf')
        maxD = 0
        for i in range(m):
            wx, wy = workers[i]
            for j in range(n):
                bx, by = bikes[j]
                dist = abs(wx-bx)+abs(wy-by)
                minD = min(minD, dist)
                maxD = max(maxD, dist)
                if dist not in hmap:
                    hmap[dist] = []
                hmap[dist].append((i,j))
        
        wAssigned = [False]*m
        bAssigned = [False]*n

        res = [0]*m
        count = 0
        for dist in range(minD, maxD+1):
            if dist in hmap:
                for w,b in hmap[dist]:
                    if not wAssigned[w] and not bAssigned[b]:
                        wAssigned[w] = True
                        bAssigned[b] = True
                        res[w] = b
                        count+=1
                        if count==m:
                            return res
        
        return res



