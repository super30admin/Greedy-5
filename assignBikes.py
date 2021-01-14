"""
Time complexity O(WB) No of worker * No of Bike
Space complexity O(WB)

Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]

Output: [0,2,1]

"""
from collections import defaultdict
class Solution():
    def assignBikes(self, workers, bikes):
        """
        :type workers: List[List[int]]
        :type bikes: List[List[int]]
        :rtype: List[int]
        """
        # Bikes assigned 
        assigned = [False for _ in range(len(bikes))]

        # result to keep track workers that have not been assigned bikes
        res = [-1 for _ in range(len(workers))]

        # distance is the key and value is the list [w,b]
        map_ = defaultdict(list)
        maxdist = 0

        # for each w-b calculate distance
        for wid, worker in enumerate(workers):
            for bid, bike in enumerate(bikes):
                dist = self.manhattandist(worker, bike)
                map_[dist].append((wid, bid))
                # maxdist to keep track of distance to eliminate the treemap /sorted/ ordered keys
                maxdist = max(maxdist, dist)

        for i in range(0, maxdist+1):
            # If the key in map and the value list is not empty
            if i in map_ and map_[i]:
                
                li = map_[i]
                for wid, bid in li:
                    if not assigned[bid] and res[wid] == -1:
                        res[wid] = bid
                        assigned[bid] = True
        return res

    # Function to compute manhattan distance between two points
    def manhattandist(self, p1, p2):
        return abs(p1[0]-p2[0]) + abs(p1[1]-p2[1])

s=Solution()
res=s.assignBikes([[0,0],[1,1],[2,0]],[[1,0],[2,2],[2,1]])
print(res)