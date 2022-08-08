# Time Complexity : O((w * b) * log(w * b)) where w is the workers and b is bikes
# Space Complexity : O(w * b)
# Did this code successfully run on Leetcode : Yes
import heapq

class Solution(object):
    def assignBikes(self, workers, bikes):
        def manhattan(p1, p2):
            return abs(p1[0] - p2[0]) + abs(p1[1] - p2[1])

        distances = [[] for _ in xrange(len(workers))]
        for i in range(len(workers)):
            for j in range(len(bikes)):
                distances[i].append((manhattan(workers[i], bikes[j]), i, j))
            distances[i].sort(reverse = True)

        result = [None] * len(workers)
        lookup = set()
        min_heap = []
        for i in range(len(workers)):
            heapq.heappush(min_heap, distances[i].pop())
        while len(lookup) < len(workers):
            _, worker, bike = heapq.heappop(min_heap)
            if bike not in lookup:
                result[worker] = bike
                lookup.add(bike)
            else:
                heapq.heappush(min_heap, distances[worker].pop())
        return result
