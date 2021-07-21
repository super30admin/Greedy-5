# TC: O(M x N) where M is the length of workers list and N is the length of bikes list. 
# SC: O(D) where D is the height of the treemap that stores distance between all the workers with all the bikes.

from sortedcontainers import SortedDict

class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        if not workers or not bikes or len(workers) == 0 or len(bikes) == 0: 
            return 
        
        if len(workers) == 1 and len(bikes) == 1: 
            return [0]
        
        dist_hmap = SortedDict()
        bool_workers = [False] *len(workers)
        bool_bikes = [False] * len(bikes)
        result = [-1]* len(workers)
        
        for i in range(len(workers)):
            for j in range(len(bikes)): 
                distance = abs(workers[i][0] - bikes[j][0]) + abs(workers[i][1] - bikes[j][1])
                if distance in dist_hmap: 
                    temp_list = dist_hmap.get(distance)
                    temp_list.append((i, j))
                    dist_hmap[distance] = temp_list
                else: 
                    dist = (i,j)
                    dist_hmap[distance] = [dist]
        
        assigned = 0
        for dist in dist_hmap.keys(): 
            temp = dist_hmap.get(dist)
            for x,y in temp: 
                if not bool_workers[x] and not bool_bikes[y]:
                    result[x] = y
                    bool_workers[x] = True
                    bool_bikes[y] = True
                    assigned += 1
                
                if assigned == len(workers): 
                    break
        
        return result
