class Solution:
    def minDominoRotations(self, tops: List[int], bottoms: List[int]) -> int:
        """check using first ele of tops and if that is not present in all dominoes at either tops or bottoms, 
        then check using first ele of bottoms
        Time Complexity-O(n)
        Space complexity-O(1)"""
        result=self.check(tops, bottoms, tops[0])
        if result==-1:
            return self.check(tops, bottoms, bottoms[0])
        else:
            return result        
        
    def check(self, tops, bottoms, ele):
        arot=0
        brot=0
        for i in range(len(tops)):
            if tops[i]!=ele and bottoms[i]!=ele:
                return -1
            if tops[i]!=ele:
                arot+=1
            if bottoms[i]!=ele:
                brot+=1
        return min(arot, brot)
        
                
        """Greedy Approach using hashmap and double pass algo
        Time complexity-O(2*n)
        Space complexity-O(n)"""
        # hashmap=dict()
        # maxele=-1
        # for i in range(len(tops)):
        #     if tops[i] not in hashmap:
        #         hashmap[tops[i]]=1
        #     else:
        #         hashmap[tops[i]]+=1
        #     if hashmap[tops[i]]>=len(tops):
        #         maxele=tops[i]
        #     if bottoms[i] not in hashmap:
        #         hashmap[bottoms[i]]=1
        #     else:
        #         hashmap[bottoms[i]]+=1
        #     if hashmap[bottoms[i]]>len(bottoms):
        #         maxele=bottoms[i]
        # if maxele==-1:
        #     return maxele
        # arot=0
        # brot=0
        # for i in range(len(tops)):
        #     if tops[i]!=maxele and bottoms[i]!=maxele:
        #         return -1
        #     if tops[i]!=maxele:
        #         arot+=1
        #     if bottoms[i]!=maxele:
        #         brot+=1
        # return min(arot, brot)
            
        