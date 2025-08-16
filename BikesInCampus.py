from typing import collections, List

class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        # hashmap for dist to list of workers and bikes
        disttolist = collections.defaultdict(list)
        mindist,maxdist = float('inf'), float('-inf')
        # cal and store manhattan distances
        for i in range(len(workers)):
            for j in range(len(bikes)):
                mandist = self.calcmandist(workers[i], bikes[j])
                disttolist[mandist].append((i,j))
                mindist = min(mindist,mandist)
                maxdist = max(maxdist, mandist)
        # loop from mindist to maxdist and if the workers are not assigned to bikes assign them and keep count
        res = [-1]*len(workers)
        count = 0
        workersassigned = [False]*len(workers)
        bikersassigned = [False]*len(bikes)
        for dist in range(mindist, maxdist+1):
            if dist not in disttolist:
                continue
            for worker, biker in disttolist.get(dist):
                if not workersassigned[worker] and not bikersassigned[biker]:
                    workersassigned[worker] = True
                    bikersassigned[biker] = True
                    res[worker] = biker
                    count+=1
                    if count == len(workers):
                        return res
        return res

    def calcmandist(self,w, b):
        return abs(w[0]-b[0]) + abs(w[1]-b[1])

# TLE
# class Solution:
#     def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
#         # heap
#         pq = []
#         # cal and store manhattan distances in a min heap
#         for i in range(len(workers)):
#             for j in range(len(bikes)):
#                 mandist = self.calcmandist(workers[i], bikes[j])
#                 # dist, worker,bikes
#                 heapq.heappush(pq, (mandist,i,j))
#         # loop from mindist to maxdist and if the workers are not assigned to bikes assign them and keep count
#         res = [-1]*len(workers)
#         count = 0
#         workersassigned = [False]*len(workers)
#         bikersassigned = [False]*len(bikes)
#         while pq:
#             dist, worker, biker = heapq.heappop(pq)
#             if not workersassigned[worker] and not bikersassigned[biker]:
#                 workersassigned[worker] = True
#                 bikersassigned[biker] = True
#                 res[worker] = biker
#                 count+=1
#                 if count == len(workers):
#                     return res
#         return res

#     def calcmandist(self,w, b):
#         return abs(w[0]-b[0]) + abs(w[1]-b[1])

        

        