class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        if bikes == None or len(bikes) == 0 or workers == None or len(workers) == 0:
            return []
        
        hashmap = {}
        wl, bl = len(workers), len(bikes)
        minDist, maxDist = float('inf'), -float('inf')
        
        for i in range(wl):
            for j in range(bl):
                dist = self.calManhatDist(workers[i], bikes[j])
                minDist = min(minDist, dist)
                maxDist = max(maxDist, dist)
                if dist not in hashmap:
                    hashmap[dist] = list()
                hashmap[dist].append([i, j])
                
        assignedWorkers = [False] * wl
        occupiedBikes = [False] * bl
        result = [0] * wl
        
        for i in range(minDist, maxDist+1):
            if i in hashmap:
                li = hashmap[i]
                for wb in li:
                    worker = wb[0]
                    bike = wb[1]
                    if assignedWorkers[worker] == False and occupiedBikes[bike] == False:
                        assignedWorkers[worker] = True
                        occupiedBikes[bike] = True
                        result[worker] = bike
        return result
        
        
    def calManhatDist(self, w, b):
        return (abs(w[0]-b[0]) + abs(w[1]-b[1]))

# Time Complexity: O(wl * bl)
# Space Complexity: O(wl * bl)
        