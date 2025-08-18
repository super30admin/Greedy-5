# Time Complexity: O( m*n log (m*n))
# Space Complexity: O(m*n)
class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        pq = []

        for i in range(len(workers)):
            for j in range(len(bikes)):
                dist = self.calculateDistance(workers[i], bikes[j])
                heapq.heappush(pq, (dist, i, j))  # (distance, worker, bike)

        result = [-1] * len(workers)
        workersAssigned = [False] * len(workers)
        bikesAssigned = [False] * len(bikes)
        count = 0

        while pq:
            dist, worker, bike = heapq.heappop(pq)

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