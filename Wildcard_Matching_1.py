# Time complexity : O(m*n)
# Space complexity : O(m*n)
# Leetcode : Solved and submitted

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        # check for base cases
        if s == p or p == '*':
            return True
        
        # find the lengths of the strings
        sl = len(s); pl = len(p)
        
        # make a dp array of size m+1*n+1
        dp = [[False for _ in range(sl+1)] for _ in range(pl+1)]
        
        # mark the first index in matrix as True
        dp[0][0] = True
        
        # fill the first column
        for i in range(1,pl+1):
            if p[i-1] == '*':
                dp[i][0] = dp[i-1][0]
        
        # start traversing the matrix
        for i in range(1,pl+1):
            for j in range(1,sl+1):
                # if we do not encounter a star
                if p[i-1] != '*':
                    
                    # if the strings match or the pattern string is ?
                    if p[i-1] == s[j-1] or p[i-1] == '?':
                        # fetch the value from diagonal
                        dp[i][j] = dp[i-1][j-1]
                
                else:
                    # else do OR operation for 0 and 1 cases
                    dp[i][j] = dp[i-1][j] or dp[i][j-1]
        
        # return the last element as the result
        return dp[pl][sl]
