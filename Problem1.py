"""
Time Complexity: O(mn + (max - min) * K)
Space Complexity: O(mn)
"""
class Solution:
    def assignBikes(self, workers, bikes):

        dist_map = defaultdict(list)
        min_d, max_d = float('inf'), float('-inf')

        for i in range(len(workers)):
            for j in range(len(bikes)):
                dist = self.calculateDistance(workers[i], bikes[j])
                dist_map[dist].append((i, j))
                min_d = min(min_d, dist)
                max_d = max(max_d, dist)

        result = [-1] * len(workers)
        workersAssigned = [False] * len(workers)
        bikesAssigned = [False] * len(bikes)
        count = 0

        for d in range(min_d, max_d + 1):
            if d not in dist_map:
                continue
            for worker, bike in dist_map[d]:
                if not workersAssigned[worker] and not bikesAssigned[bike]:
                    result[worker] = bike
                    workersAssigned[worker] = True
                    bikesAssigned[bike] = True
                    count += 1
                    if count == len(workers):
                        return result
        return result

    def calculateDistance(self, worker, bike):
        return abs(worker[0] - bike[0]) + abs(worker[1] - bike[1])
