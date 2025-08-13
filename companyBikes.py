"""
Compute the Manhattan distance for every (worker, bike) pair
Process pairs in increasing distance; on ties, pick smaller worker index, then smaller bike index (problem’s rule)
Assign a bike to a worker the first time we see a valid pair where both are still unassigned
"""
"""
Time Complexity: O(W·B + D + total_pairs_in_bucket_sorts) ≈ O(W·B) in practice (D ≤ 2000)
Space Complexity: O(W·B + W + B)
"""
class companyBikes:
    def assignBikes(self, workers: list[list[int]], bikes: list[list[int]]) -> list[int]:

        MAXD = 2000
        buckets = [[] for _ in range(MAXD + 1)]

        for wi, (wx, wy) in enumerate(workers):
            for bi, (bx, by) in enumerate(bikes):
                d = abs(wx - bx) + abs(wy - by)
                buckets[d].append((wi, bi))

        n, m = len(workers), len(bikes)
        ans = [-1] * n
        bike_used = [False] * m
        assigned = 0

        for d in range(MAXD + 1):
            if assigned == n:
                break
            if not buckets[d]:
                continue

            buckets[d].sort(key=lambda x: (x[0], x[1]))
            for wi, bi in buckets[d]:
                if ans[wi] == -1 and not bike_used[bi]:
                    ans[wi] = bi
                    bike_used[bi] = True
                    assigned += 1
                    if assigned == n:
                        break
        return ans

if __name__ == "__main__":
    workers = [[0,0],[2,1]]
    bikes   = [[1,2],[3,3]]

    print(companyBikes().assignBikes(workers, bikes))

    workers = [[0,0],[1,1],[2,0]]
    bikes   = [[1,0],[2,2],[2,1]]

    print(companyBikes().assignBikes(workers, bikes))
