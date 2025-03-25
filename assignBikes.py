from typing import List
from itertools import product

class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        if workers is None or bikes is None:
            return []
        nw,nb = len(workers),len(bikes)
        details = []
        for wi in range(nw):
            for bi in range(nb):
                dist = abs(workers[wi][0] - bikes[bi][0]) + abs(workers[wi][1] - bikes[bi][1])
                details.append((dist,wi,bi))
        details.sort()
        visitedWorkers = [False]*nw
        visitedBikes = [False]*nb
        assignments = [-1]*nw
        for dist,widx,bidx in details:
            if not visitedWorkers[widx] and not visitedBikes[bidx]:
                visitedWorkers[widx] = True
                visitedBikes[bidx] = True
                assignments[widx] = bidx
        return assignments

s = Solution()
workers = [[0,0],[2,1]]
bikes = [[1,2],[3,3]]
print(s.assignBikes(workers,bikes))
workers = [[0,0],[1,1],[2,0]]
bikes = [[1,0],[2,2],[2,1]]
print(s.assignBikes(workers,bikes))

        