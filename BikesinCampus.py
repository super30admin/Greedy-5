# TC: O(m * n log n)
# SC: O(m * n)


class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        distance = []
        for i, w in enumerate(workers):
            distance.append([])
            for j, b in enumerate(bikes):
                distance[-1].append([abs(w[0]-b[0]) + abs(w[1]-b[1]), i, j])
            heapq.heapify(distance[-1])
        ans = [-1] * len(workers)
        used_bikes = set()
        heap = [heapq.heappop(d) for d in distance]
        heapq.heapify(heap)
        while len(used_bikes) < len(workers):
            _, w, b = heapq.heappop(heap)
            if b in used_bikes:
                heapq.heappush(heap, heapq.heappop(distance[w]))
            else:
                used_bikes.add(b)
                ans[w] = b
        return ans 