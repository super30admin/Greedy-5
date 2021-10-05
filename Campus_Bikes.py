#Time:O(nlogn)
#Space:O(n)
from collections import defaultdict,OrderedDict
class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        
        dict_dist = defaultdict(list)
        ans_workers = [-1]*len(workers)
        ans_bikes = [-1]*len(bikes)
        for index_worker, (worker_column,worker_row) in enumerate(workers):
            for index_bike, (bike_column,bike_row) in enumerate(bikes):
                dict_dist[abs(worker_column-bike_column)+abs(worker_row-bike_row)].append((index_worker,index_bike))
        # print(dict_dist)
        od = collections.OrderedDict(sorted(dict_dist.items()))
        # print(od)
        for key,values in od.items():
            for value in values:
                # print(value)
                if ans_workers[value[0]]==-1 and ans_bikes[value[1]]==-1 :
                    # print("setting",value)
                    ans_workers[value[0]]=value[1]
                    ans_bikes[value[1]]=value[0]
        return ans_workers