# TIME COMPLEXITY: O(W*B) -> W is number of workers, B is number of bikes
# SPACE COMPLEXITY: O(W*B)
# Approach - Use a map to keep track of distances and worker/bike pairs
# This solution does not require sorting the keys or using a sorted/ordered map
from collections import defaultdict
class Solution(object):
    def assignBikes(self, workers, bikes):
        """
        :type workers: List[List[int]]
        :type bikes: List[List[int]]
        :rtype: List[int]
        """
        # assigned keeps track of the bikes assigned so far
        assigned = [False for _ in range(len(bikes))]

        # Initialize result array with -1 to track workers that have not been assigned bikes
        result = [-1 for _ in range(len(workers))]

        # map to hold distances (manhattan distance between worker and bike) as keys with a list of pairs of workers and bikes
        _map = defaultdict(list)
        max_dist = 0

        # For every worker, bike pair, calculate the distance and append it to the list corresponding to that distance
        for worker_id, worker in enumerate(workers):
            for bike_id, bike in enumerate(bikes):
                dist = self.manhattan_dist(worker, bike)
                _map[dist].append((worker_id, bike_id))
                # Keep track of the max distance in the map
                # Use this to iterate over keys in the map
                # This eliminates the need to sort the keys or use a sorted map/Tree Map
                max_dist = max(max_dist, dist)

        # Iterate over all distances from 0 to max_distance
        for i in range(0, max_dist+1):
            # If the key exists in the map and the corresponding list is not empty
            if i in _map and _map[i]:
                # get the list
                li = _map[i]
                # Iterate over every pair in the list and greedily assign bikes to workers that have not been assigned so far
                for worker_id, bike_id in li:
                    if not assigned[bike_id] and result[worker_id] == -1:
                        result[worker_id] = bike_id
                        assigned[bike_id] = True
        return result

    # Function to compute manhattan distance between two points
    def manhattan_dist(self, p1, p2):
        return abs(p1[0]-p2[0]) + abs(p1[1]-p2[1])
