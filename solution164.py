#Time Complexity:O(mn)
#Space Complexity:O(n)
#Calculate the distance between each worker and bike and store the worker and bike number in list Array that holds all possible distances
#between any given worker and bike.bikesAssigned array is used to update the status of assignment of a bike. Parse through each distance 
#stating from zero through 2000. Consider each pair with that distance and check if assignment is possible. Assign the bike to the worker
#by updating the result as well as the bikesAssigned array until every worker is assigned a bike.
class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        if not workers or not bikes:
            return []
        listArray=[[] for i in range(2000)]
        for i in range(len(workers)):
            for j in range(len(bikes)):
                dist=self.calculateDist(workers[i],bikes[j])
                listArray[dist].append([i,j])
        result=[-1]*len(workers)
        bikesAssigned=[False]*len(bikes)
        count=0
        for d in range(2000):
            assign=listArray[d]
            if assign:
                for wbpair in assign:
                    worker=wbpair[0]
                    bike=wbpair[1]
                    if result[worker]==-1 and not bikesAssigned[bike]:
                        result[worker]=bike
                        bikesAssigned[bike]=True
                        count+=1
                    if count==len(workers):
                        return result
        return result
    
    def calculateDist(self,workers:List[int],bikes:List[int])->int:
        return abs(workers[0]-bikes[0])+abs(workers[1]-bikes[1])