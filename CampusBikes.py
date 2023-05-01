#All Tc could not be tested as premium problem

from collections import OrderedDict

class Solution:
    
    #Here we compute worker-bike distance for every worker and store the it in sorted map dist:(worker,bike). We the iterate over this 
    #map for all distances which are in sorted order as OrderedDict is used. For each entry of worker-bike in map we 
    #check if both worker and bike are free, if yes then we assign them together.
    #time complexity - O(m*nlogmn) where m-bikes length, n-workers length as distances are sorted in orderedDict(treemap)
    #space complexity - O(m*n) - for storing worker-tobike dostance for every worker
    def assignBikesToWorkers(self, bikes, workers):
        map = OrderedDict()
        n = len(workers)
        m = len(bikes)
        
        for i in range(n):
            for j in range(m):
                dist = abs(workers[i][0]-bikes[j][0]) + abs(workers[i][1]-bikes[j][1])
                if dist not in map:
                    map[dist] = []
                map[dist].append((i,j))
        
        w = [False]*n
        b = [False]*m
        
        res = [0]*n #for every workers index what bike index assigned
        count = 0
        
        for dist in map:
            vals = map[dist]
            for v in vals:
                wIdx = v[0]
                bIdx = v[1]
                if not w[wIdx] and not b[bIdx]:
                    w[wIdx] = True
                    b[bIdx] = True
                    res[wIdx] = bIdx
                    count+=1
                if count==n:
                    break
        return res
                
    

s = Solution()
workers = [[0,0],[2,1]]
bikes = [[1,2],[3,3]]
res = s.assignBikesToWorkers(bikes, workers)
print(res)
workers = [[0,0],[1,1],[2,0]]
bikes = [[1,0],[2,2],[2,1]]
res = s.assignBikesToWorkers(bikes, workers)
print(res)


#-----------------------------------------------------------OR--------------------------------------------------------------


class Solution:
    
    #Here we compute worker-bike distance for every worker and store the it in map dist:(worker,bike). We the iterate over this 
    #map from min dist to max dist as  all distances are not sorted. For each entry of worker-bike in map we 
    #check if both worker and bike are free, if yes then we assign them together.
    #time complexity - O(m*n)+o(k) where m-bikes length, n-workers length, k-max distance obtained - Here we iterate uptill max distance obtained
    #space complexity - O(m*n) - for storing worker-tobike dostance for every worker
    def assignBikesToWorkers(self, bikes, workers):
        map = {}
        n = len(workers)
        m = len(bikes)
        
        minD = math.inf
        maxD = -math.inf
        
        for i in range(n):
            for j in range(m):
                dist = abs(workers[i][0]-bikes[j][0]) + abs(workers[i][1]-bikes[j][1])
                if dist not in map:
                    map[dist] = []
                map[dist].append((i,j))
                minD = min(minD, dist)
                maxD = max(maxD, dist)

                
        w = [False]*n
        b = [False]*m
        
        res = [0]*n #for every workers index what bike index assigned
        count = 0
        
        for dist in range(minD, maxD+1):
            if dist not in map:
                continue
            vals = map[dist]
            for v in vals:
                wIdx = v[0]
                bIdx = v[1]
                if not w[wIdx] and not b[bIdx]:
                    w[wIdx] = True
                    b[bIdx] = True
                    res[wIdx] = bIdx
                    count+=1
                if count==n:
                    break
        return res
                
    

s = Solution()
workers = [[0,0],[2,1]]
bikes = [[1,2],[3,3]]
res = s.assignBikesToWorkers(bikes, workers)
print(res)
workers = [[0,0],[1,1],[2,0]]
bikes = [[1,0],[2,2],[2,1]]
res = s.assignBikesToWorkers(bikes, workers)
print(res)

