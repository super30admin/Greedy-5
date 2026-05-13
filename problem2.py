# problem 2 

## Problem2: Bikes in a Campus (https://leetcode.com/problems/campus-bikes/)

class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        worker_bike_list = []
        pq = []
        for worker_idx,worker_loc in enumerate(workers):
            current_pair = []
            for bike_idx,bike_loc in enumerate(bikes):
                distance = self.find_distance(worker_loc,bike_loc)
                current_pair.append((distance,worker_idx,bike_idx))
            current_pair.sort(reverse=True)
            heapq.heappush(pq,current_pair.pop())
            worker_bike_list.append(current_pair)
        bike_status = [False] * len(bikes)
        worker_status = [-1] * len(workers)
        while pq:
            distance,worker,bike = heapq.heappop(pq)
            if not bike_status[bike]:
                bike_status[bike]=True 
                worker_status[worker] = bike 
            else:
                next_close_bike = worker_bike_list[worker].pop()
                heapq.heappush(pq,next_close_bike)
        return worker_status
    

    def find_distance(self,worker_arr,bike_arr):
        return abs(worker_arr[0]-bike_arr[0]) + abs(worker_arr[1]-bike_arr[1])