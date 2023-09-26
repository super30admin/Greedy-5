class Solution(object):
    def assignBikes(self, workers, bikes):
        """
        :type workers: List[List[int]]
        :type bikes: List[List[int]]
        :rtype: List[int]
        """
        ans = [-1]*len(workers)
        distances = collections.defaultdict(list)
        set_bikes = set()
        
        for i in range(len(workers)):
            for j in range(len(bikes)):
                distances[abs(workers[i][0]-bikes[j][0])+abs(workers[i][1]-bikes[j][1])].append([i,j])
        
        for k in sorted(distances.keys()):
            for i in range(len(distances[k])):
                if ans[distances[k][i][0]] == -1 and distances[k][i][1] not in set_bikes:
                    ans[distances[k][i][0]] = distances[k][i][1]
                    set_bikes.add(distances[k][i][1])
                
        
        return ans