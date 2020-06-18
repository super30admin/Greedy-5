"""
// Time Complexity : O(len(workers)* len(bikes))
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
Algorithm Explanation
Given below - Greedy
"""
class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        """
        Initial configuration - Distance array -> List of (worker,bike) indices for each distance in grid within 2000
        Idea is 
        - Store the (worker,bike) indices for the distances within the grid(2000)
        - Iterate over all the workers 
            Iterate over all the bikers
                - Update the distance array with (w,i) based on manhattan computation
        -Configuration - assigned(workers) -boolean , occupied(bikes) - boolean, result array
        Iterate over the distances
            - For the list of pairings per distance
                - Check if worker is not assigned and bike is not occupied
                    assign the worker
                    occupy the bike
                - Assign the bike index to worker index in the result array
        - return the result
        
        Question - Result still the issue
        
        """
        def manht_distance(worker,bike):
            return abs(worker[0]-bike[0]) + abs(worker[1] - bike[1])
        
        distances = [None]*2000
        for i,w in enumerate(workers):
            for j,b in enumerate(bikes):
                distance = manht_distance(w,b)
                if not distances[distance]:
                    distances[distance] = []
                distances[distance].append([i,j])
        
        assigned = [False]*len(workers)
        occupied = [False]*len(bikes)
        
        result = [None]*len(workers)
        for d in distances:
            if d:
                for w,b in sorted(d):
                    if not assigned[w] and not occupied[b]:
                        assigned[w] = True
                        occupied[b] = True
                        result[w] = b
        return result