class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        if s == p or p == "*":
            return True
        m = len(p)
        n = len(s)

        dp = [[False] * (n + 1) for i in range(m + 1)]

        dp[0][0] = True

        for i in range(1, len(dp)):
            for j in range(len(dp[0])):
                char = p[i - 1]

                if char != "*":
                    if j > 0 and (char == "?" or char == s[j - 1]):
                        dp[i][j] = dp[i - 1][j - 1]
                else:
                    if j > 0:
                        dp[i][j] = dp[i - 1][j] or dp[i][j - 1]
                    else:
                        dp[i][j] = dp[i - 1][j]

        return dp[-1][-1]

# Dynamic Programming
# Time Complexity:O(m * n)
# Space Complexity: O(m *n)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
