# time: O(workers * bikes)
# space:O( workers * bikes)
class Solution:
    def assignBikes(self, workers, bikes):
        
        worker_distances = []

       
        for w in range(len(workers)):
            worker_distances.append([])
            for b in range(len(bikes)):
                dist = abs(workers[w][0] - bikes[b][0]) + abs(workers[w][1] - bikes[b][1])
                worker_distances[-1].append((dist, w, b))
            worker_distances[-1].sort(reverse=True)

        ans = [None for d in range(len(workers))]
        used_bikes = set()
        
        queue = [worker_distances[i].pop() for i in range(len(workers))]
        heapq.heapify(queue)

       
        while len(used_bikes) < len(workers):
            d, worker, bike = heapq.heappop(queue)
            
            if bike not in used_bikes:
                ans[worker] = bike
                used_bikes.add(bike)
          
            else:
                heapq.heappush(queue, worker_distances[worker].pop())

        return ans
