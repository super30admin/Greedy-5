"""
time - o(m*n) , m- number of workers, n - number of bikes
space - o(n)
"""

class Solution:
    
    def calculateDistance(self, worker, bike):
        return abs(worker[0] - bike[0]) + abs(worker[1] - bike[1])
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        
        if not workers or not bikes:
            return [0]
        res =[0] * len(workers)
        dist_list = [None]*2000
        #print(dist_list)
        
        for w in range(len(workers)):
            for b in range(len(bikes)):
                dist = self.calculateDistance(workers[w], bikes[b])
                
                if dist_list[dist] == None:
                    dist_list[dist] = []
                    
                dist_list[dist].append((w,b))
                
        #print(dist_list)
        
        worker = [False] * len(workers)
        bike = [False] * len(bikes)
        for d in dist_list:
            if d is None:
                continue
            for pairs in d:
                w = pairs[0]
                b = pairs[1]
                
                if worker[w] or bike[b]:
                    continue
                    
                worker[w] = True
                bike[b] = True
                res[w] = b
        return res
        