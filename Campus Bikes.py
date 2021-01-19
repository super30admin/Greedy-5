# Time:- O(n^2)
# Space:- O(n)
class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        # worker has been assigned a bike or not
        workers_assigned=[False]*len(workers)
        # bike has been assigned or not
        bikes_assigned=[False]*len(bikes)
        res=[0]*len(workers)
        # distance from worker to bike hashmap
        workers_bikes_assigned=collections.defaultdict(list)
        for i in range(len(workers)):
            for j in range(len(bikes)):
                # workers_bikes_assigned[dist]={[[worker,bike]]}
                workers_bikes_assigned[abs(bikes[j][0]-workers[i][0])+abs(bikes[j][1]-workers[i][1])].append([i,j])
        # traverse the sorted hashmap and start assigning bikes to worker
        for dist in sorted(workers_bikes_assigned):
            for worker,bike in workers_bikes_assigned[dist]:
                if not workers_assigned[worker] and not bikes_assigned[bike]:
                    # assign a bike to the worker
                    workers_assigned[worker]=True
                    bikes_assigned[bike]=True
                    res[worker]=bike
        return res