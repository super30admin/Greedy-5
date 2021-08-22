from collections import OrderedDict
class Solution:
    def Sol(self, workers, bikes):
        """using list to store the elements with distance from 0 to 2000
        Time complexity-O(m*n)
        Space complexity-O(m*n)"""
        li=[[] for _ in range(2000)]
        for i in range(len(workers)):
            for j in range(len(bikes)):
                dis=self.calcDistance(workers[i], bikes[j])
                li[dis].append([i,j])
        Assigned=[0 for _ in range(len(workers))]
        occupied=[False for i in range(len(bikes))]
        workernotAvl=[False for i in range(len(workers))]
        for i in range(len(li)):
            if li[i]!=[]:
                for j in li[i]:
                    if not occupied[j[1]] and not workernotAvl[j[0]]:
                        Assigned[j[0]]=j[1]
                        occupied[j[1]]=True
                        workernotAvl[j[0]]=True
        return Assigned   
        """using hashmap to store the elements with same distance, but to get it in sorted order again store in list"""
        # li=[]
        # hashmap=OrderedDict()
        # for i in range(len(workers)):
        #     for j in range(len(bikes)):
        #         dis=self.calcDistance(workers[i], bikes[j])
        #         if dis not in hashmap:
        #             hashmap[dis]=[]
        #         hashmap[dis].append([i,j])
        # for i in sorted(hashmap):
        #     li.append((i, hashmap[i]))
        # print(li)
        # Assigned=[0 for _ in range(len(workers))]
        # occupied=[False for i in range(len(bikes))]
        # workernotAvl=[False for i in range(len(workers))]
        # for i in range(len(li)):
        #     for j in li[i][1]:
        #         if not occupied[j[1]] and not workernotAvl[j[0]]:
        #             Assigned[j[0]]=j[1]
        #             occupied[j[1]]=True
        #             workernotAvl[j[0]]=True
        # return Assigned

    def calcDistance(self, worker, bike):
        dis=abs(worker[0]-bike[0])+abs(worker[1]-bike[1])
        return dis
s=Solution()
print(s.Sol([[0,0],[1,1],[2,0]], [[1,0],[2,2],[2,1]]))
