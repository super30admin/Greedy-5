#Time Complexity: O(mn)
#Space Complexity: O(mn)

class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        map = [None for _ in range(2000)]
        m = len(workers)
        n = len(bikes)
        for i in range(m):
            for j in range(n):
                dist = self.calculateDistance(workers[i],bikes[j])
                if map[dist] == None:
                    map[dist] = []
                map[dist].append([i,j])
      
        assignedWorkers = [False]*m
        assignedBikes = [False]*n
        result = [0]*m
      
        for i in range(2000):
            if map[i] == None:
                continue
            for j in range(len(map[i])):
                if assignedWorkers[map[i][j][0]] == False and assignedBikes[map[i][j][1]] == False:
                    #print(map[i][j][0],map[i][j][1])
                    result[map[i][j][0]] = map[i][j][1]
                    assignedWorkers[map[i][j][0]]=True
                    assignedBikes[map[i][j][1]]=True
        return result
    def calculateDistance(self,worker,bike):
       
        dist = abs(worker[0]-bike[0]) + abs(worker[1]-bike[1])
        return dist