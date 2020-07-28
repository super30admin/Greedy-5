#Time ComplexitY:O(mnlog(mn))
#Space complexity:O(n)
#Ran successfully on Leetcode:Yes
#Approach:
#1. Initailize array for workers, bikes, result. map for dist-->pair(worker,bike) map.
#2.Sort the hashMap in acc to distance whihch is calculated taking abs of x and y cordinates of workers and bikes.
#3.Iterate through values of the hashMap, and check if worker and bikes are already True or not, if not make them true and append the worker num,ber to result array.
class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        if not bikes or not workers or len(bikes)==0 or len(workers)==0:
            return []
        assigned=[False for _ in range(len(workers))]
        occupied=[False for _ in range(len(bikes))]
        distMap={}
        result=[-1 for _ in range(len(workers))] 
        for i in range(len(workers)):
            for j in range(len(bikes)):
                dist=self.getDist(workers[i],bikes[j])
                if dist not in distMap:
                    distMap[dist] = []
                distMap[dist].append((i,j))
        
        distMap = sorted(distMap.items())
        for distance in distMap:
            # print(distance)
            pairs = distance[1]
    
            
            for pair in pairs:
                worker = pair[0]
                bike = pair[1]
                if  not assigned[worker] and not  occupied[bike]:
                    assigned[worker]=True
                    occupied[bike]=True
                    result[worker]=bike
        return result
                    
                
    def getDist(self, worker, bike):
        return abs(worker[0] - bike[0]) + abs(worker[1] - bike[1])
