"""
Problem2: Bikes in a Campus (https://leetcode.com/problems/campus-bikes/)

"""
class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        """
        Using sorting of list of tuples of (distance, worker , bike)
        TC: O(W*B LOG W*B) W = num of workers; B = num of Bikes
        SC: O(W*B)
        """
        w_len = len(workers)
        b_len = len(bikes)
        bike_visited = [False  for _ in range(b_len)]
        worker_visted = [False for _ in range(w_len)]
        count = 0
        res = [-1  for _ in range(w_len)]
        # list of tuples which will store (distance, workers, bikes)
        distanceWorkerBikePair = []
        
        for worker in workers:
            for bike in bikes:
                dist = self.calcManhattanDistance(worker, bike)
                distanceWorkerBikePair.append((dist, workers.index(worker), bikes.index(bike)))
        # in Python the default behaviour of sort is to sort in order of the attributes         
        distanceWorkerBikePair.sort()
        
        for dist, worker, bike in distanceWorkerBikePair:
            if  worker_visted[worker]==False and  bike_visited[bike] ==False:
                count += 1
                worker_visted[worker] = True
                bike_visited[bike] = True
                res[worker] = bike
                
                if count == w_len:
                    return res
                
        return res
                
        
    def calcManhattanDistance(self, worker, bike):
        return abs(worker[0] - bike[0]) + abs(worker[1] - bike[1])


# Another approch

import  collections
class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        """
        using bucket sort for the distance. The buckets will be created on the bases of distance min-max
        TC: O(W*B)
        SC: (W*B)
        """
        w_len = len(workers)
        b_len = len(bikes)
        distanceWorkerBikePair = defaultdict(list)
        min_dist = 1000
        bike_visited = [False  for _ in range(b_len)]
        worker_visted = [False for _ in range(w_len)]
        count = 0
        res = [-1  for _ in range(w_len)]
        
        for worker, w_loc in enumerate(workers):
            for bike, b_loc in enumerate(bikes):
                dist = self.calcManhattanDistance(w_loc, b_loc)
                distanceWorkerBikePair[dist].append((worker, bike))
                min_dist = min(min_dist, dist)
                
        curr_dist = min_dist
        while count < w_len:
            for w, b in distanceWorkerBikePair[curr_dist]:
                if  worker_visted[w]==False and  bike_visited[b] ==False:
                    count += 1
                    worker_visted[w] = True
                    bike_visited[b] = True
                    res[w] = b
            curr_dist += 1
            
        return res
        
        
    def calcManhattanDistance(self, worker, bike):
        return abs(worker[0] - bike[0]) + abs(worker[1] - bike[1])
