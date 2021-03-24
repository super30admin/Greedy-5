"""
Time Complexity : O(mn)
Space Complexity : O(1)
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No

Explaination
METHOD 1: use DP  space/ time : o(mn)
in the dimentions keep source and destination
1. if there is * :
    (upper element, diagoanl upper element)
2. if chars do not match then False:
3. if match then take from diagonal up
4. if ? take from diagonal up

METHOD 2: two pointers
use 4 pointers in total if we found star we point that
location with the different variable
if we fail to match the sequence then we 
start again from the same star

"""
 

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        if s == p or p == "*": return True
        sl = len(s) # cols
        pl = len(p) # rows
        sp,pp = 0,0
        sStar, pStar = -1,-1
        
        while sp < sl:
            #case 1: both chars are equal
            if pp<pl and (s[sp] == p[pp] or p[pp]=="?"):
                sp+=1
                pp+=1
            
            #case 2: when p[pp] == *
            elif pp< pl and p[pp] == "*":
                pStar = pp
                sStar = sp
                pp += 1
            
            elif pStar == -1:
                return False
            
            else:
                sStar += 1
                sp = sStar
                pp = pStar + 1
        while pp < pl:
            if p[pp] != "*":
                return False
            pp+=1
        return True
        
        
        
class Solution1:
    def isMatch(self, s: str, p: str) -> bool:
        if s == p or p == "*": return True
        sl = len(s) # cols
        pl = len(p) # rows
        dp = [[False]*(sl+1) for _ in range(pl+1)]
        dp[0][0] = True
            
        for i in range(1, pl+1):
            if p[i-1]=="*":
                dp[i][0]=dp[i-1][0]
                j = 1
                while j<=sl:
                    dp[i][j] = dp[i-1][j] or dp[i-1][j-1]
                    if dp[i][j]:
                        while j<=sl:
                            dp[i][j] = True
                            j+=1
                    j+=1
                
            elif p[i-1] == "?":
                for j in range(1, sl+1):
                    dp[i][j] = dp[i-1][j-1]
            else:
                for j in range(1, sl+1):
                    if s[j-1] == p[i-1]:
                        dp[i][j] = dp[i-1][j-1]
        return dp[pl][sl]
                    
            