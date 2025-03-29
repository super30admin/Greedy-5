#time: O(n * m log (n * m))
#space: O(n * m)
from collections import defaultdict
class Solution(object):
    def assignBikes(self, workers, bikes):
        """
        :type workers: List[List[int]]
        :type bikes: List[List[int]]
        :rtype: List[int]
        """

        def distance(worker,bike):
            return abs(worker[0]-bike[0]) + abs(worker[1]-bike[1])
        dis_map=defaultdict(list)
        for i in range(len(workers)):
            for j in range(len(bikes)):
                dis= distance(workers[i],bikes[j])
                dis_map[dis].append([i,j])

        w=[False]*len(workers)
        b=[False]*len(bikes)
        result=[-1]*len(workers)
        for key in sorted(dis_map.keys()):
            for wbp in dis_map[key]:
                wk=wbp[0]
                bk=wbp[1]
                if w[wk] == False and b[bk]== False:
                    w[wk]=True
                    b[bk]= True
                    result[wk]=bk
        return result
