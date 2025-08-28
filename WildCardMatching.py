class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        m = len(s)
        n = len(p)

        dp = [[False for i in range(n + 1)] for j in range(m + 1)]

        for i in range(m + 1):
            for j in range(n + 1):
                if i == 0 and j == 0:
                    dp[i][j] = True
                elif i == 0:
                    if p[j - 1] == "*":
                        dp[i][j] = dp[i][j - 1]
                elif j == 0:
                    dp[i][j] = False
                elif s[i - 1] == p[j - 1] or p[j - 1] == "?":
                    dp[i][j] = dp[i - 1][j - 1]
                elif p[j - 1] == "*":
                    case1 = dp[i][j - 1]
                    case2 = dp[i - 1][j]
                    dp[i][j] = case1 or case2
                else:
                    dp[i][j] = False
        return dp[m][n]