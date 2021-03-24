class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        #Approach: Dynamic Programming
        #Time Complexity: O(m * n)
        #Space Complexity: O(m * n)
        #where, m and n are the lengths of strings s and p, respectively
        
        dp = [[False for j in range(len(s) + 1)] for i in range(len(p) + 1)]
        dp[0][0] = True
        
        for i in range(1, len(dp)):
            for j in range(len(dp[0])):
                if j == 0:
                    if p[i - 1] == '*':
                        dp[i][j] = dp[i - 1][j]
                else:
                    if p[i - 1] == '*':
                        dp[i][j] = dp[i - 1][j] or dp[i][j - 1]
                    elif p[i - 1] == s[j - 1] or p[i - 1] == '?':
                        dp[i][j] = dp[i - 1][j - 1]
                        
        return dp[-1][-1]