# TC: O(m*n) where m is len of workers and n is len of bikes
# SC: O(m*n) where m is len of workers and n is len of bikes

class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        if workers is None or len(workers) == 0:
            return []

        hashmap = {}
        result = [None] * len(workers)

        boolBike = [False] * len(bikes)
        boolWorker = [False] * len(workers)

        _min = sys.maxsize
        _max = 0

        for i in range(0, len(bikes)):
            for j in range(0, len(workers)):

                dist = self.calcDist(bikes[i], workers[j])
                _min = min(_min, dist)
                _max = max(_max, dist)
                if dist not in hashmap:
                    hashmap[dist] = []
                hashmap[dist].append([i, j])

        for i in range(_min, _max + 1):
            if i in hashmap:
                arr = hashmap[i]

                for elem in arr:
                    if boolBike[elem[0]] == False and boolWorker[elem[1]] == False:
                        result[elem[1]] = elem[0]
                        boolBike[elem[0]] = True
                        boolWorker[elem[1]] = True

        return result

    def calcDist(self, bike, worker):
        return abs(bike[0] - worker[0]) + abs(bike[1] - worker[1])







