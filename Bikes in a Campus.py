""""// Time Complexity : O(mn)
// Space Complexity :O(mn)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :
"""
class Solution:
    def assignBikes(self, workers, bikes):
        map = {}
        m = len(workers)
        n = len(bikes)
        mini = float('inf')
        maxi = float('-inf')
        for i in range(m):
            for j in range(n):
                dist = self.calculateDistance(workers[i],bikes[j])
                mini=min(mini, dist)
                maxi=max(maxi, dist)
                if dist not in map:
                    map[dist] = []
                map[dist].append([i,j])

        assignedWorkers = [False]*m
        assignedBikes = [False]*n
        result = [0]*m
        count=0

        for i in range(mini, maxi+1, 1):
            if not map[i]:
                continue
            for k in map[i]:
                if assignedWorkers[k[0]]==False and assignedBikes[k[1]]==False:
                    assignedWorkers[k[0]] = True
                    assignedBikes[k[1]] = True
                    result[k[0]]=k[1]
                    count+=1
                    if count==len(workers):
                        return result
        return result

    def calculateDistance(self,worker,bike):

        dist = abs(worker[0]-bike[0]) + abs(worker[1]-bike[1])
        return dist

Obj=Solution()
print(Obj.assignBikes([[0,0], [1,1], [2,0]], [[1,0], [2,2], [2,1]]))