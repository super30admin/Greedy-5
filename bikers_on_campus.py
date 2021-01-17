## TIme - O(nmlog(mn)) - for dict
## TIme - O(nm) - for dict
## space - O(mn) -
class Solution:
    from collections import defaultdict
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        def dist(worker,bike):
            return (abs(worker[0]-bike[0]))+(abs(worker[1]-bike[1]))
        # dic=defaultdict(list)
        arr=[[] for i in range(2000)]
        for i in range(len(workers)):
            for j in range(len(bikes)):
                d=dist(workers[i],bikes[j])
                # dic[d].append([i,j])
                arr[d].append([i,j])
        result=[-1]*len(workers)
        b_assgined=[False]*len(bikes)
        # print()
        # for i in sorted(dic.keys()):
        for i in range(len(arr)):
            # for val in dic[i]:
            for val in arr[i]:
                if result[val[0]]==-1 and b_assgined[val[1]]==False:
                    result[val[0]]=val[1]
                    b_assgined[val[1]]=True
        return result