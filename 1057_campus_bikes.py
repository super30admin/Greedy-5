from typing import List
from collections import defaultdict


class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        """
            https://leetcode.com/problems/campus-bikes/
            Time Complexity - O(mn + logk)
            m - number of workers
            n - number of bikes
            k - number of distances in the dictionary
            Space Complexity - O(mn)
        """
        # edge case
        if not workers or not bikes:
            return []

        dic = defaultdict(list)
        for w_idx, worker in enumerate(workers):
            for b_idx, bike in enumerate(bikes):
                dist = self._calculate_dist(worker, bike)
                dic[dist].append((w_idx, b_idx))

        result = [0] * len(workers)
        workers_assigned = [False] * len(workers)
        bikes_assigned = [False] * len(bikes)
        for dist in sorted(dic.keys()):
            for worker, bike in dic[dist]:
                if not workers_assigned[worker] and not bikes_assigned[bike]:
                    workers_assigned[worker] = True
                    bikes_assigned[bike] = True
                    result[worker] = bike
        return result

    def _calculate_dist(self, worker, bike):
        return abs(worker[0] - bike[0]) + abs(worker[1] - bike[1])


if __name__ == '__main__':
    print(Solution().assignBikes([[0, 0], [1, 1], [2, 0]], [[1, 0], [2, 2], [2, 1]]))
