#Time complexity: O(mn)
#Space complexity: O(mn)
from typing import (
    List,
)

class Solution:
    """
    @param workers: workers' location
    @param bikes: bikes' location
    @return: assign bikes
    """
    def assign_bikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        # write your code here
        lst=[None]*2000
        for i in range(len(workers)):
            for j in range(len(bikes)):
                dist=self.calculateDist(workers[i],bikes[j])
                if lst[dist]==None:
                    lst[dist]=[]
                lst[dist].append((i,j))
        assignedWorker=[False]*len(workers)
        assignedBike=[False]*len(bikes)
        res=[0]*len(workers)
        for i in range(2000):
            if lst[i]==None:
                continue
            for j in range(len(lst[i])):
                worker=lst[i][j][0]
                bike =lst[i][j][1]
                if not assignedWorker[worker] and not assignedBike[bike]:
                    assignedBike[bike]=True
                    assignedWorker[worker]=True
                    res[worker]=bike
        return res


    def calculateDist(self,worker,bike):
        return abs(worker[1]-bike[1]) + abs(worker[0]-bike[0])

