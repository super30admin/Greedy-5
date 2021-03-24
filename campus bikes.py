"""
Time Complexity : O()
Space Complexity : O()
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No

Explaination

1. for each worker find distance to eacj bike
2. create adjecency list of distance and worker and bike
i.e distance == 1 : (0,2) - > worker 0 is 1 unit away from bike 2
3. create available array and result arrays
4. iterate over ,ap and fill res array if not already filled 

"""

class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        wl = len(workers)
        bl = len(bikes)
        array = [None]*2000
        order = []
        res = []
        
        def calculate(wrkr, bk):
            return abs(wrkr[0] - bk[0]) + abs(wrkr[1] - bk[1])
            
        for i in range(wl):
            for j in range(bl):
                dist = calculate(workers[i], bikes[j])
                if array[dist] == None:
                    array[dist] = []
                array[dist].append((i,j)) 

        availableBikes = [False]*bl
        res = [-1]*wl
        
        for i in range(len(array)):
            if array[i] != None:
                ls = array[i]
                # j[0] is worker
                # j[1] is bike
                # check if both are avalable

                for j in ls:
                    if res[j[0]] == -1 and availableBikes[j[1]] ==False:
                        res[j[0]] = j[1]
                        availableBikes[j[1]] =True
        return res
    
    
    
    
# With HashMap    
class Solution1:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        wl = len(workers)
        bl = len(bikes)
        hm = {}
        order = []
        res = []
        
        def calculate(wrkr, bk):
            return abs(wrkr[0] - bk[0]) + abs(wrkr[1] - bk[1])
            
        for i in range(wl):
            for j in range(bl):
                dist = calculate(workers[i], bikes[j])
                if dist not in hm:
                    hm[dist] = []
                hm[dist].append((i,j))
        order = sorted(hm)
        availableBikes = [False]*bl
        res = [-1]*wl
        
        for i in order:
            ls = hm[i]
            # j[0] is worker
            # j[1] is bike
            # check if both are avalable

            for j in ls:
                if res[j[0]] == -1 and availableBikes[j[1]] ==False:
                    res[j[0]] = j[1]
                    availableBikes[j[1]] =True
        return res
        