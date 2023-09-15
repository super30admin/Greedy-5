class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        '''
        Time complexity: O(n * m * (n + m)), n --> number of workers, m --> number of bikes.
        Space complexity: O(n * m), for hashmap to store distances between workers and bikes, which can have a maximum of n * m unique distances.
        '''
        n = len(workers)
        m = len(bikes)

        # Create a hashmap to store distances between workers and bikes
        distance_map = collections.defaultdict(list)

        # Populate the hashmap with distances and their corresponding indices
        for i in range(n):
            for j in range(m):
                dist = abs(workers[i][0] - bikes[j][0]) + \
                    abs(workers[i][1] - bikes[j][1])
                distance_map[dist].append((i, j))

        # Create lists to keep track of assigned workers and bikes
        worker_assigned = [False] * n
        bike_assigned = [False] * m

        # Initialize the result list with -1
        result = [-1] * n

        # Iterate through the distances in ascending order
        for dist in sorted(distance_map.keys()):
            pairs = distance_map[dist]
            for worker, bike in pairs:
                # Check if the worker and bike are available
                if not worker_assigned[worker] and not bike_assigned[bike]:
                    result[worker] = bike  # Assign the worker to the bike
                    worker_assigned[worker] = True
                    bike_assigned[bike] = True

        return result
