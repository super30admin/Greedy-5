# Time Complexity : O(NM)(number of bikes * number of workers)
# Space Complexity : O(M) (bikeAssignedLength + 2000)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No


# Your code here along with comments explaining your approach

class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        if not workers or not bikes:
            return [res]
        
        liArr = [0 for i in range(2000)]
                
        mn = float('inf')
        mx = float('-inf')
        
        for i in range(len(workers)):
            for j in range(len(bikes)):
                dist = self.distance(workers[i], bikes[j])
                
                if not liArr[dist]:
                    liArr[dist] = []
                liArr[dist].append([i,j])
                mn = min(mn, dist)
                mx = max(mx, dist)
                
        res = [-1 for i in range(len(workers))]
        
        assignedBikes = [False for i in range(len(bikes))]
        count = 0
        
        for dist in range(mn, mx+1):
            li = liArr[dist]
            if li:
                for wb in li:
                    worker = wb[0]
                    bike = wb[1]
                    
                    if res[worker] == -1 and not assignedBikes[bike]:
                        res[worker] = bike
                        assignedBikes[bike] = True
                        count += 1
                    if count == len(workers):
                        return res
    def distance(self, worker, bike):
        return abs(worker[0] - bike[0]) + abs(worker[1] - bike[1])