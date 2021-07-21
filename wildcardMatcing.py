#Sc = o(m+n)
# tine = O(mn)
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        if p is None or s is None: return False
        row = len(p) + 1
        column = len(s) + 1
        dp = [[False for _ in range(column)] for k in range(row)]

        # making _ to True
        dp[0][0] = True

        for r in range(1, len(dp)):
            for c in range(len(dp[0])):
                if p[r - 1] == '*':
                    # Zero Case
                    if c == 0:
                        dp[r][c] = dp[r - 1][c]
                    else:
                        dp[r][c] = dp[r - 1][c] or dp[r][c - 1]
                elif c > 0 and p[r - 1] == s[c - 1] or p[r - 1] == '?':
                    if c > 0:
                        dp[r][c] = dp[r - 1][c - 1]

        return dp[row - 1][column - 1]


