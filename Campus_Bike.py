# Time Complexity : O(W * B * log(W * B)), where W is the number of workers and B is the number of bikes.
# Space Complexity : O(W * B)
import heapq
from typing import List

class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        def manhattan_distance(p1, p2):
            return abs(p1[0] - p2[0]) + abs(p1[1] - p2[1])
        
        distances = []  # List to store distances (worker_index, bike_index, distance)
        assigned_bikes = set()
        result = [-1] * len(workers)
        
        for i, worker in enumerate(workers):
            for j, bike in enumerate(bikes):
                distance = manhattan_distance(worker, bike)
                distances.append((i, j, distance))
        
        distances.sort(key=lambda x: (x[2], x[0], x[1]))  # Sort distances in ascending order
        
        for i, j, distance in distances:
            if result[i] == -1 and j not in assigned_bikes:
                result[i] = j
                assigned_bikes.add(j)
        
        return result

