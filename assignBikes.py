"""
TC: O(w*b)
SP: O(w*b)
First calculate manhattan distance for all worker/bike combination and store it in hmap, maintain two variables to keep track of min/max distances
Iterate through the min/max range and start assiging bikes to workers by keeping track of the assigned unassigned bikes and unassigned workers.
"""
class Solution:
    def assignBikes(
        self, workers: List[List[int]], bikes: List[List[int]]
    ) -> List[int]:
        hmap = defaultdict(list)
        mini = float("inf")
        maxi = float("-inf")
        lw = len(workers)
        for i, w in enumerate(workers):
            for j, b in enumerate(bikes):
                dist = abs(w[0] - b[0]) + abs(w[1] - b[1])
                hmap[dist].append((i, j))
                mini = min(dist, mini)
                maxi = max(dist, maxi)

        result = [-1] * lw
        bike = [False] * len(bikes)
        assigned = 0
        for d in range(mini, maxi + 1):
            for w, b in hmap[d]:
                if result[w] == -1 and bike[b] == False:
                    result[w] = b
                    bike[b] = True
                    assigned += 1
                    if assigned == lw:
                        break
        return result
