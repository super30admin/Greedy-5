# time complexity: O(workers * bikes)
# space complexity: O( workers * bikes)
class Solution:
    def assignBikes(self, workers, bikes):
        # list containing list of tupples of distances of each work with each bike
        worker_distances = []

        # this loop is for adding the tuples of bikes and distances for each worker in list of list
        for w in range(len(workers)):
            worker_distances.append([])
            for b in range(len(bikes)):
                dist = abs(workers[w][0] - bikes[b][0]) + abs(workers[w][1] - bikes[b][1])
                worker_distances[-1].append((dist, w, b))
            worker_distances[-1].sort(reverse=True)

        ans = [None for d in range(len(workers))]
        used_bikes = set()
        # created a queue which contains 1 entry for each worker with distances in ascending order
        queue = [worker_distances[i].pop() for i in range(len(workers))]
        heapq.heapify(queue)

        # while each worker is not given bike
        while len(used_bikes) < len(workers):
            d, worker, bike = heapq.heappop(queue)
            # if bike is not assigned then assign it to the worker
            if bike not in used_bikes:
                ans[worker] = bike
                used_bikes.add(bike)
            # else find the next entry for the current worker and enter it in the queue
            else:
                heapq.heappush(queue, worker_distances[worker].pop())

        return ans





