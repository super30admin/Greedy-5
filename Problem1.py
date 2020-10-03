"""
time - o(m*n) , m - length of string, n - length of pattern
space - o(m*n)
"""

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
       
        dp = [[False for _ in range(len(s)+1)] for _ in range(len(p)+1)]
        
        dp[0][0] = True
        
        for i in range(1,len(dp)):
            for j in range(len(dp[0])):
                
                if j == 0:
                    if p[i - 1] == '*':
                        dp[i][j] = dp[i - 1][j]
                else:
                    if p[i - 1] == "*" and dp[i][j-1] == True:
                        dp[i][j] = True
                        
                    elif s[j - 1] == p[i - 1] or p[i -1] == '?':
                        dp[i][j] = dp[i - 1][j - 1]
                        
                    elif p[i - 1] == "*":
                        dp[i][j] = dp[i - 1][j - 1] or dp[i - 1][j]
                        
        return dp[-1][-1]
                        
                        
                    
        
                         
                