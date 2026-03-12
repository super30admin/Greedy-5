# Time Complexity : O(W*B log(W*B))
# Space Complexity : O(W*B)
# Did this code successfully run on Leetcode : No
# Any problem you faced while coding this : No

# Explanation:
# Compute all worker-bike distances.
# Store (distance, worker, bike) in heap.
# Assign bikes greedily by smallest distance while ensuring
# each worker and bike are used only once.

import heapq

class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        
        heap = []
        
        for i, (wx,wy) in enumerate(workers):
            for j, (bx,by) in enumerate(bikes):
                dist = abs(wx-bx) + abs(wy-by)
                heapq.heappush(heap, (dist, i, j))
        
        res = [-1]*len(workers)
        used_bikes = set()
        assigned = set()
        
        while heap:
            dist, w, b = heapq.heappop(heap)
            
            if w not in assigned and b not in used_bikes:
                res[w] = b
                assigned.add(w)
                used_bikes.add(b)
        
        return res