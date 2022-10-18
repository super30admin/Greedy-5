# Time complexity : O(m*n)
# Space complexity : O(m*n)
# Leetcode : Solved and submitted

class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        # find the lengths of the both the lists
        m = len(workers)
        n = len(bikes)
        
        # create a visited array for each workers and bikes
        worker_assigned = [False]*m
        bikes_occupied = [False]*n
        
        # hashmap to keep track of the dist calculated for each worker and bike combination
        hashmap = {}
        
        # finding the min and max distance
        max_val = 0
        min_val = 2000
        res = [-1]*m
        count = 0
        
        # traverse over all the combinations of workers and bikes
        for i in range(m):
            for j in range(n):
                # find the distance and add it to the hashmap
                curr_dist = self.CalculateDist(workers[i], bikes[j])
                if curr_dist not in hashmap:
                    hashmap[curr_dist] = []
                
                # add the tuple of worker and bike indexes to the hashmap with dist as the key
                hashmap[curr_dist].append((i,j))
                max_val = max(max_val, curr_dist)
                min_val = min(min_val, curr_dist)
        
        # traverse over the min till max value of hashmap
        for i in range(min_val, max_val +1):
            
            # if there is no list in the hashmap with that number, then continue
            if i not in hashmap:
                continue
            # fetch the list of tuples of indexes
            li = hashmap[i]
            for wb in li:
                w = wb[0]
                b = wb[1]
                
                # if both the worker and bike are not assigned, then assign the bike to the user
                if not worker_assigned[w] and not bikes_occupied[b]:
                    worker_assigned[w] = True
                    bikes_occupied[b] = True
                    
                    ## app the worker and bike in res
                    res[w] = b
                    count += 1
                    
                    # if all workers are assigned, then return the res
                    if count == m:
                        return res
        
    # function to calculate the dist between two points
    def CalculateDist(self, worker, bike):
        return abs(worker[0] - bike[0]) + abs(worker[1] - bike[1])
