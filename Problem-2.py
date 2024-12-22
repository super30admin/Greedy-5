#Approcah
# calcultae the distacnce from each work to bikes and store it in the dict.
# iterate from min distance to maxdistance and assign the workers with  bikes

#Complexities
#Time: O(n)
#Space: O(n)

from typing import List

class Solution:
    """
    @param workers: workers' location
    @param bikes: bikes' location
    @return: assign bikes
    """
    def assign_bikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        distanceMap = {}

        minDistance = float("inf")
        maxDistance = float("-inf")
        for i in range(len(workers)):
            for j in range(len(bikes)):
                distance  = self.calculateDistance(workers[i],bikes[j])
                if distance not in distanceMap:
                    distanceMap[distance] = []
                distanceMap[distance].append((i, j))
                minDistance = min(minDistance,distance)
                maxDistance = max(maxDistance,distance)

        workersAssigned = [False]*len(workers)
        bikesAsigned = [False]*len(bikes)
        result = [-1]*len(workers)
        count =0
        for i in range(minDistance,maxDistance+1):
            if count!=len(workers):
                if i in distanceMap:
                    for (w,b) in distanceMap[i]:
                        if workersAssigned[w]==False and bikesAsigned[b]==False:
                            result[w] = b
                            workersAssigned[w]=True
                            bikesAsigned[b]= True
                            count+=1
            else:
                break
        return result


    def calculateDistance(self,p1,p2):
        return abs(p1[0]-p2[0])+ abs(p1[1]-p2[1])

S= Solution()
print(S.assign_bikes([[0,0],[1,1],[2,0]],[[1,0],[2,2],[2,1]]))

