#Time Complexity :o(n*m)
#Space Complexity :o(max(n,m))
#Did this code successfully run on Leetcode :yes
#Any problem you faced while coding this :no

class Solution(object):
    def assignBikes(self, workers, bikes):
        """
        :type workers: List[List[int]]
        :type bikes: List[List[int]]
        :rtype: List[int]
        """
        
        distArray=[None]*2000
        
        #maping based on distance of worker to bike
        for worker in range(len(workers)):
            for bike in range(len(bikes)):
                dist=abs(workers[worker][0]-bikes[bike][0])+abs(workers[worker][1]-bikes[bike][1])
                if(not distArray[dist]):
                    distArray[dist]=[]
                distArray[dist].append((worker,bike))
        
        
        bikesAssigned=[False]*len(bikes)
        workerAssigned=[-1]*len(workers)
        
        cnt=0
        #iterate on dist array to assign the bike to worker if not assigned
        for i in range(len(distArray)):
            if(cnt>len(workers)):
                break
            if(distArray[i]):
                arr=distArray[i]
                for i in arr:
                    worker=i[0]
                    bike=i[1]
                    if(workerAssigned[worker]==-1 and not bikesAssigned[bike]):
                        workerAssigned[worker]=bike
                        bikesAssigned[bike]=True
                        cnt+=1
        return workerAssigned
                        
                
        