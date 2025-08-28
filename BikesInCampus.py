from collections import defaultdict
class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        m, n = len(workers), len(bikes)
        distMap = defaultdict(list)
        assignedBikes = [False for i in range(n)]
        assignedWorkers = [False for i in range(m)]

        minDist = float("inf")
        maxDist = -1
        for w in range(m):
            for b in range(n):
                dist = abs(workers[w][0] - bikes[b][0]) + abs(workers[w][1] - bikes[b][1])
                distMap[dist].append([w, b])
                minDist = min(minDist, dist)
                maxDist = max(maxDist, dist)
        
        res = [-1 for i in range(m)]
        for dist in range(minDist, maxDist + 1):
            if dist not in distMap:
                continue
            currDistList = distMap[dist] 
            for w, b in currDistList:
                if assignedWorkers[w] or assignedBikes[b]:
                    continue
                assignedBikes[b] = True
                assignedWorkers[w] = True
                res[w] = b
        return res
