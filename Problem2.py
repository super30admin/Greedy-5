# Problem 2: Bikes in a Campus
class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        Map = {}
        m = len(workers)
        n = len(bikes)
        assingedWorkers = [False for _ in range(m)]
        assingedBikes = [False for _ in range(n)]
        Min = float("inf")
        Max = float("-inf") 
        count = 0
        result = [-1 for _ in range(m)]

        def getdistance(worker,bike):
            return abs(worker[0] - bike[0]) + abs(worker[1] - bike[1])

        for i in range(m):
            for j in range(n):
                dist = getdistance(workers[i],bikes[j])
                if dist not in Map:
                    Map[dist] = []
                    Min = min(Min, dist)
                    Max = max(Max, dist)
                Map[dist].append((i,j))

        for i in range(Min,Max+1):
            if i not in Map: continue
            li = Map[i]
            for wb in li:
                worker = wb[0]
                bike = wb[1]
                if not assingedWorkers[worker] and not assingedBikes[bike]:
                    result[worker] = bike
                    assingedWorkers[worker] = True
                    assingedBikes[bike] = True
                    count += 1
                    if count == m: 
                        return result

        return result

        
        