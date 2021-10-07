class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        if workers is None or len(workers)==0:
            return 0
        distMap={}
        for i in range(len(workers)):
            for j in range(len(bikes)):
                dist=self.manhattendist(workers[i],bikes[j])
                if dist not in distMap:
                    distMap[dist]=[]
                distMap[dist].append((i,j))
        workers=[-1]*len(workers)
        bikes=[False]*len(bikes)
        count=len(workers)
        
        for key in (sorted(distMap.keys())):
            li = distMap[key]
            for val in li:
                worker=val[0]
                bike=val[1]
                if workers[worker]==-1:
                    if bikes[bike]==False:
                        workers[worker]=bike
                        bikes[bike]=True
                        count-=1
                        if count==0:
                            return workers
             
    def manhattendist(self,workers,bikes):
        return abs(workers[0]-bikes[0])+abs(workers[1]-bikes[1])
        