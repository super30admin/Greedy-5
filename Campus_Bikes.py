#from collections import heapq 
class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        
        if not workers:
            return []
        
        worker_array = [-1]* len(workers)
        bike_array = [False] * len(bikes)
        
        distances = [[] for _ in range(2001)]
        
        for i in range(len(workers)):
            for j in range(len(bikes)):
                #print("Hello")        
                dis = self.manhatten_distance(workers[i], bikes[j])
                distances[dis].append([i,j])                
                
        
        count = len(worker_array)
        
        for i in range(len(distances)):
            for dis in distances[i]:
                worker, bike = dis[0], dis[1]
                if worker_array[worker] == -1:
                    if not bike_array[bike]:
                        worker_array[worker] = bike
                        bike_array[bike] = True
                        count -=1
            if count == 0:
                return worker_array
    
    
    def manhatten_distance(self,worker, bike):
        
        return abs(worker[0] - bike[0]) + abs(worker[1] - bike[1])
                           
                           
                           
                           
