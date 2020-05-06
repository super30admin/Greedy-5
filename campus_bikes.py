# Time complexity --> o(mn)  m--> len(workers) n--> len(bikes)
# space complexity --> o(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None


// Your code here along with comments explaining your approach:
In this problem we have to calculate the bike nearest to a worker and assign it to him.
we do this by calcuating each bike distance to each worker using manhattan distance and using those distances as indices to store the worker and bike indices in the list.we then traverse thorough the created list and assign nearest bike to the worker.

class Solution(object):
    def dist(self,x,y):
        return abs(x[0]-y[0])+abs(x[1]-y[1])
    def assignBikes(self, workers, bikes):
        """
        :type workers: List[List[int]]
        :type bikes: List[List[int]]
        :rtype: List[int]
        """
        #workers max length can be 1000 and bikes max can be 1000.max distance it can go is till 2000.
        out=[-1 for i in range(2005)]
        #creating a list of lists where each list index represents the distance between two points and the points are stored in their respective indices.
        for i in range(len(workers)):
            for j in range(len(bikes)):
                distance=self.dist(workers[i],bikes[j])
                if out[distance]==-1:
                    out[distance]=[[i,j]]
                else:
                    out[distance].append([i,j])
         #Assign two lists to track the bike assignment to each worker
        workerlist=[False for i in range(len(workers))]
        bikeslist=[False for i in range(len(bikes))]
        # print(out)
        result=[-1 for i in range(len(workers))]
        for i in range(len(out)):
            val=out[i]
            if val!=-1:
                for j in val:
                    w=j[0]
                    b=j[1]
                    #If the worker and bike both are not assigned then only we assign a worker the nearest bike else we continue
                    if workerlist[w]==False and bikeslist[b]==False:
                        bikeslist[b]=True
                        workerlist[w]=True
                        result[w]=b
                    # print(workerlist,bikeslist,j,result)
        return result