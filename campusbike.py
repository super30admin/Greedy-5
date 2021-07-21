# Sc := O(n)
#Time :- O(N*n)
class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:

        if workers is None or len(workers) == 0 or bikes is None or len(bikes) == 0: return List[int]

        distancemap = collections.defaultdict(list)

        # distancing map from each bike to worker
        for bi in range(len(bikes)):
            for work in range(len(workers)):
                dist = self.calculatemanhatandist(workers[work], bikes[bi])

                distancemap[dist].append([work, bi])

        heap = []
        for element in distancemap.keys():
            heapq.heappush(heap, element)

        worky = [None] * len(workers)
        biky = [True] * len(bikes)

        # get the mindistance by heapsort
        while heap.__len__() > 0:
            mindistance = heapq.heappop(heap)
            keymap = distancemap[mindistance]

            for worker in keymap:

                if worky[worker[0]] == None and biky[worker[1]]:
                    worky[worker[0]] = worker[1]
                    biky[worker[1]] = False

        print(worky)

        return worky

    def calculatemanhatandist(self, work, bike):
        return abs(work[0] - bike[0]) + abs(work[1] - bike[1])

