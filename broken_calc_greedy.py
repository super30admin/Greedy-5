# // Time Complexity :O(logn)
# // Space Complexity :O(1)
# // Did this code successfully run on Leetcode :yes
# // Any problem you faced while coding this :no

#greedy, give one path of bottom up order
#o(logn)
class Solution:
    def brokenCalc(self, startValue: int, target: int) -> int:
        count=0
        if startValue==target:
            return count
        while target>startValue:
            print(target)
            if target%2==0:
                target/=2
            else:
                target+=1
            count+=1
        return int(count+startValue-target)
                
        