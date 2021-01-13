#Time Complexity : O(workers*bikes) 
#Space Complexity : O(2000) 

class Solution(object):
    def assignBikes(self, workers, bikes):
        """
        :type workers: List[List[int]]
        :type bikes: List[List[int]]
        :rtype: List[int]
        """
        arr = [None] * 2000
        for i in range(len(workers)):
            for j in range(len(bikes)):
                distance = self.cal(workers[i], bikes[j])
                if arr[distance] is None:
                    arr[distance] = []
                arr[distance].append([i,j]) 
            
        result = [-1] * len(workers)
        bikesAssigned = [0] * len(bikes)
        count = 0
        
        for i in range(2000):
            if arr[i] is not None:
                for ele in arr[i]:
                    if not bikesAssigned[ele[1]] and result[ele[0]] == -1:
                        result[ele[0]] = ele[1]
                        bikesAssigned[ele[1]] = 1
                        count +=1
                if count == len(workers):
                    return result
                    
                        
        return result
                    
            
                
    def cal(self, workers, bikes):
        return abs(workers[0] - bikes[0]) + abs(workers[1] - bikes[1])
        
                
        