class Solution(object):
    def isMatch(self, s, p):
        m, n = len(s), len(p)
        
        # dp[i][j] = whether s[:i] matches p[:j]
        dp = [[False] * (n + 1) for _ in range(m + 1)]
        
        # Empty string matches empty pattern
        dp[0][0] = True

        # Fill first row for patterns like '*', '**', etc.
        for j in range(1, n + 1):
            if p[j - 1] == '*':
                dp[0][j] = dp[0][j - 1]
        
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                if p[j - 1] == s[i - 1] or p[j - 1] == '?':
                    dp[i][j] = dp[i - 1][j - 1]
                elif p[j - 1] == '*':
                    # '*' can match 0 (dp[i][j-1]) or 1+ characters (dp[i-1][j])
                    dp[i][j] = dp[i][j-1] or dp[i - 1][j]
        
        return dp[m][n]
    # TC : O(m*n)
    # SC : O(m*n)