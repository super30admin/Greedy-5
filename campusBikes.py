#Time Complexity : O(w*b) where w is the number of workers and b is the number of bikes
#Space Complexity : O(2000) 
#Did this code successfully run on Leetcode : Yes

class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        distance = [None for _ in range(2000)]
        for i, w in enumerate(workers):
            for j, b in enumerate(bikes):
                d = abs(w[0]-b[0]) + abs(w[1]-b[1])
                if distance[d] == None:
                    distance[d] = []
                distance[d].append([i, j])


        result = [-1] * len(workers)
        bike_taken = set()
        asssigned = 0
        for i in range(2000):
            currDistance = distance[i]
            if currDistance:
                for pair in currDistance:
                    w = pair[0]
                    b = pair[1]
                    if result[w] == -1 and b not in bike_taken:
                        result[w] = b
                        bike_taken.add(b)
                        asssigned += 1
                    if asssigned == len(workers):
                        break

        return result
