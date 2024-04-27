'''
Time Complexity - O(mn). We are traversing the bikes for each worker
Space Complexity - O(mn). We are using hashMap for storing pairs for each distance

Works on Leetcode.
'''
class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        hashMap = {}
        m = len(workers)
        n = len(bikes)
        #calculate the distances for each worker and bike and put in hashMap
        #we are doing bucket sorting
        minDist, maxDist = 1e9, -1e9
        for i in range(len(workers)):
            for j in range(len(bikes)):
                dist = abs(bikes[j][1]- workers[i][1]) + abs(bikes[j][0]- workers[i][0])
                if dist not in hashMap:
                    hashMap[dist] = []
                distList = hashMap.get(dist)
                distList.append([i,j])
                minDist = min(minDist, dist)
                maxDist = max(maxDist, dist)
        #Assigning the workers bikes  
        assignedbikes = [False] * n
        assignedworkers = [False] * m
        result = [-1] * m
        count = 0
        for dist in range(minDist, maxDist+1):
            distList = hashMap.get(dist)
            if distList:
                #for every pair for each distance
                for pair in distList:
                    #if worker and bike are not assigned, assign them
                    if not assignedworkers[pair[0]] and not assignedbikes[pair[1]]:
                        assignedworkers[pair[0]] = True
                        assignedbikes[pair[1]] = True
                        result[pair[0]] = pair[1]
                        count+=1
                        #when all workers are assigned bikes, return
                        if count == m:
                            return result
        return result


        