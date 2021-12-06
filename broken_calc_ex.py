#exhaustive
#0(2^n),O(n)
# // Time Complexity :O(2^n)
# // Space Complexity :O(n)
# // Did this code successfully run on Leetcode :yes
# // Any problem you faced while coding this :no
class Solution:
    def brokenCalc(self, startValue: int, target: int) -> int:
        q=[]
        q.append(startValue)
        count=0
        while q:
            size=len(q)
            for i in range(size):
                curr=q.pop(0)
                print(curr)
                if curr-1==target or curr*2==target:
                    return count+1
                if curr<target:
                    q.append(curr*2)
                q.append(curr-1)
            count+=1
        
                    
        