'''
Solution:
1.  Iterate all bikes for each worker and place in the sorted Map according to distances till all pairs are placed.
2.  Now, as the map is sorted, iterate through each pair of worker and bike in order and allocate a pair if not
    allocated previously.
3.  Instead of sorted Map, an array can also be used as total distances can be less than or equal to 2000.

Time Complexity:    O(W * B)    #   W - no of workers and B - no of Bikes -- for optimal solution
Space Complexity:   O(W * B)    #   for each pair
--- Passed all testcases successfully on Leetcode
'''


from sortedcontainers import SortedDict


class AssignBikesUsingTreeMap:

    def __computeDist(self, worker: List[int], bike: List[int]) -> int:

        #   compute distance
        return (abs(worker[0] - bike[0]) + abs(worker[1] - bike[1]))

    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:

        distWorkerBikePairMap = SortedDict()

        #   iterate all bikes for each worker
        for w in range(len(workers)):
            for b in range(len(bikes)):

                #   calculate distance
                currentDist = self.__computeDist(workers[w], bikes[b])

                #   if distance not in the sorted Map
                if (currentDist not in distWorkerBikePairMap):
                    distWorkerBikePairMap[currentDist] = []

                #   add worker, bike pair to the distance list in Map
                distWorkerBikePairMap[currentDist].append([w, b])

        #   initializations
        workersAllocated = [False for w in range(len(workers))]
        bikesAllocated = [False for b in range(len(bikes))]
        countAllocated = 0
        finalAllocations = [0 for w in range(len(workers))]

        #   as Map is sorted => iterate through distances
        for distance in distWorkerBikePairMap:

            #   get the current list of pairs
            currentDistanceList = distWorkerBikePairMap[distance]

            #   iterate through each pair
            for eachPair in currentDistanceList:

                #   break when everyone is allocated
                if (countAllocated == len(finalAllocations)):
                    return finalAllocations

                #   get the ids from the pair
                workerID = eachPair[0]
                bikeID = eachPair[1]

                #   if both not allocated => allocate to each other
                if (workersAllocated[workerID] == False and bikesAllocated[bikeID] == False):
                    workersAllocated[workerID] = True
                    bikesAllocated[bikeID] = True
                    finalAllocations[workerID] = bikeID
                    countAllocated += 1

        #   return the final allocations list
        return finalAllocations


class AssignBikesUsingArray:

    def __computeDist(self, worker: List[int], bike: List[int]) -> int:

        return (abs(worker[0] - bike[0]) + abs(worker[1] - bike[1]))

    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:

        #   distances doesn't exceed 2000
        distWorkerBikePairMap = [[] for i in range(2001)]

        #   iterate all bikes for each worker
        for w in range(len(workers)):
            for b in range(len(bikes)):
                currentDist = self.__computeDist(workers[w], bikes[b])

                #   add worker, bike pair to the distance list in array
                distWorkerBikePairMap[currentDist].append([w, b])

        #   initializations
        workersAllocated = [False for w in range(len(workers))]
        bikesAllocated = [False for b in range(len(bikes))]
        countAllocated = 0
        finalAllocations = [0 for w in range(len(workers))]

        for distance in range(len(distWorkerBikePairMap)):

            #   get the current list of pairs
            currentDistanceList = distWorkerBikePairMap[distance]

            #   iterate through each pair
            for eachPair in currentDistanceList:

                #   break when everyone is allocated
                if (countAllocated == len(finalAllocations)):
                    return finalAllocations

                #   get the ids from the pair
                workerID = eachPair[0]
                bikeID = eachPair[1]

                #   if both not allocated => allocate to each other
                if (workersAllocated[workerID] == False and bikesAllocated[bikeID] == False):
                    workersAllocated[workerID] = True
                    bikesAllocated[bikeID] = True
                    finalAllocations[workerID] = bikeID
                    countAllocated += 1

        #   return the final allocations list
        return finalAllocations