# TC: O(N x M) where N is the length of the input string and M is the length of the pattern string. 
# SC: O(N x M) as we create a matrix of size N x M.

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        if  s is p or p == '*': 
            return True        
        
        dp = [[False]*(len(s) + 1) for j in range(len(p) + 1)]
        
        dp[0][0] = True
        
        
        for i in range(1,len(dp)) : 
            for j in range(len(dp[0])):
                if p[i - 1] == '*': 
                    if j == 0: 
                        dp[i][j] = dp[i - 1][j]
                    else: 
                        dp[i][j] = dp[i - 1][j] or dp[i][j - 1]
                
                elif (j > 0 and p[i - 1] == s[j - 1]) or p[i - 1] == '?': 
                    if j > 0: 
                        dp[i][j] = dp[i - 1][j - 1]
        
        return dp[-1][-1]
                        
