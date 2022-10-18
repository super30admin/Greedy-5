"""
Problem1: Wildcard Matching (https://leetcode.com/problems/wildcard-matching/)
"""

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        """
        TC: O(M*N) M = len(p) N = len(S)
        SC: O(M*N)
        """
        sl = len(s)
        pl = len(p)
        dp = [[False for _ in range(pl + 1)]for i in range(sl + 1)]
        
        dp[0][0] = True
        
        for j in range(1, pl+1):
            if p[j - 1] == "*":
                dp[0][j] = dp[0][j-1]
        for i in range(1, sl+1):
            for j in range(1, pl+1):
                
                if p[j-1] != "*":
                    
                    if p[j - 1] == s[i - 1] or p[j - 1] == "?":
                       
                        dp[i][j] = dp[i - 1][j - 1]
                else:
                    dp[i][j] = dp[i - 1][j] or dp[i][j - 1]
        return dp[sl][pl]


# Approach - 2

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        """
        TC: O(mlogn)
        SC: O(1)
        """
        sl = len(s)
        pl = len(p)
        
        sp, pp = 0, 0
        _s, _p = -1, -1
        
        while sp < sl:
            #first condition if the char matches
            if (pp < pl) and (s[sp] == p[pp] or  p[pp] == "?"):
                sp += 1
                pp += 1
                
            # Second condition when the char is *
            elif (pp < pl and p[pp] == "*"):
                _s = sp
                _p = pp
                pp += 1
            #third condition when there is no * in p
            elif _p == -1:
                return False
            
            #Forth condition when * is there and need to match char sequence
            else:
                sp = _s + 1
                _s = sp
                pp = _p + 1
                
        while pp< pl:
            if p[pp] != "*" : return False
            pp += 1
             
            
        return True
            
            
            
            
        