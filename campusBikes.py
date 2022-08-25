# Approach: HashMap
# TC: O(mn)
# SC: O(mn)
class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        hmap = {}
        m, n = len(workers), len(bikes)
        mini, maxi = float("inf"), float("-inf")
        
        for i in range(m):
            for j in range(n):
                dist = self.calculateDistance(workers[i], bikes[j])
                mini = min(mini, dist)
                maxi = max(maxi, dist)
                if dist not in hmap:
                    hmap[dist] = []
                hmap[dist].append([i,j])
        assignedWorkers = [False]*m
        assignedBikes = [False]*n
        result = [0]*m
        count = 0
        
        
        for i in range(mini, maxi+1, 1):
            if i not in hmap or not hmap[i]:
                continue
            for k0,k1 in hmap[i]:
                if assignedWorkers[k0] == False and assignedBikes[k1] == False:
                    assignedWorkers[k0] = True
                    assignedBikes[k1] = True
                    result[k0] = k1
                    count += 1
        return result
    
    def calculateDistance(self, worker, bike):
        dist = abs(worker[0]-bike[0]) + abs(worker[1]-bike[1])
        return dist
    