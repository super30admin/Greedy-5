# time complexity - O(wb)
# space complexity - O(w+b+wb)
# Did this solution run on leetcode? - yes
# Maintain a sorted TreeMap of mahattan distance with the worker and the bike.
# traverse the TreeMap.
# assign the bike to the worker if the bike has not been previously assigned and the worker does not hold any bikes previously. (use two visited arrays to keep track of these)
# return the assign result.
from sortedcontainers import SortedDict

class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        workersLen = len(workers)
        bikeLen = len(bikes)
        # array of reserved bikes
        bikeAssigned = [False for _ in range(bikeLen)]
        # array of workers who have already been assigned a bike
        workerBikeAssign = [False for _ in range(workersLen)]
        
        workerBikeMap = SortedDict()
        for i in range(workersLen):
            for j in range(bikeLen):
                currWorkerLoc = workers[i]
                currBikeLoc = bikes[j]
                
                # calculate the Manhattan distance
                manhattanDist = abs(currWorkerLoc[0]-currBikeLoc[0]) + abs(currWorkerLoc[1]-currBikeLoc[1])
                
                if manhattanDist not in workerBikeMap:
                    workerBikeMap[manhattanDist] = []
                
                workerBikeMap[manhattanDist].append((i, j)) # you want to assign the bike to smallest worker index.
        
        result = [-1 for i in range(workersLen)]
        # traverse the workerBikeMap hash table.
        for dist in workerBikeMap:
            for currWorker, currBike in workerBikeMap[dist]:
                # if both worker and bike are available, assign the bike to the worker.
                if not bikeAssigned[currBike] and not workerBikeAssign[currWorker]:
                    result[currWorker] = currBike
                    # mark the bike and worker as True
                    bikeAssigned[currBike] = True
                    workerBikeAssign[currWorker] = True
                
        
        return result
        