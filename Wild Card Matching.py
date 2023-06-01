class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        # DP approach
        # Time O(m * n)
        # Space O(m * n)
        if s == p or p == '*':
            return True
        sl = len(s)
        pl = len(p)
        dp = [[False for i in range(sl+1)] for j in range(pl+1)]
        # print(dp)
        dp[0][0] = True
        for i in range(1, pl+1):
            for j in range(sl+1):
                if p[i-1] == '*':
                    if j==0:
                        dp[i][j] = dp[i-1][j]
                    else:
                        dp[i][j] = dp[i-1][j] or dp[i][j-1]
                elif j > 0 and ((p[i-1] == s[j-1]) or (p[i-1] == '?')):
                    dp[i][j] = dp[i-1][j-1]
        # print(dp)
        return dp[-1][-1]
