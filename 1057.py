class Solution(object):
    def assignBikes(self, workers, bikes):
        """
        :type workers: List[List[int]]
        :type bikes: List[List[int]]
        :rtype: List[int]
        """
        if len(workers) == 0 or len(bikes) == 0:
            return []
        n = len(workers)
        m = len(bikes)
        tree_map = {}
        result = [-1]* n
        assigned_bikes = [False]*m
        assigned_workers = [False]*n
        maxdist = -1
        mindist = float('inf')
        def findManhattanDistance(worker, bike):
            return abs(worker[0] - bike[0]) + abs(worker[1] - bike[1])
        for i in range(n):
            for j in range(m):
                distance = findManhattanDistance(workers[i], bikes[j])
                mindist = min(mindist,distance)
                maxdist = max(maxdist, distance)
                if distance not in tree_map:
                     tree_map[distance] = []
                tree_map[distance].append((i,j))
        assigned_count = 0
        for k in range(mindist,maxdist+1):
            for i, j in tree_map.get(k, []):
                if not assigned_workers[i] and  not assigned_bikes[j]:
                    result[i] = j
                    assigned_workers[i] = True
                    assigned_bikes[j] = True
                    assigned_count += 1
                    if assigned_count == n:
                        return result
        return result

                
# TC : O(mn)
# SC : O(mn)