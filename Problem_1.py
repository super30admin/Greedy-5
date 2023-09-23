# Time Complexity: O(|s| * |p|)
# Space Complexity: O(|s| * |p|)
# Did this code successfully run on Leetcode: Yes
# Any problem you faced while coding this: No

class Solution(object):
    def isMatch(self, s, p):
        """
        :type s: str
        :type p: str
        :rtype: bool
        """
        m = len(s)
        n = len(p)
        dp = [([False] * (n + 1)) for _ in range(m + 1)]
        dp[0][0] = True

        def isMatch(i, j):
            return (((i >= 0) and (p[j] == '?')) or (s[i] == p[j]))
        for j, c in enumerate(p):
            if c == '*':
                dp[0][j + 1] = dp[0][j]
        for i in range(m):
            for j in range(n):
                if p[j] == '*':
                    matchEmpty = dp[i + 1][j]
                    matchSome = dp[i][j + 1]
                    dp[i + 1][j + 1] = matchEmpty or matchSome
                elif isMatch(i, j):
                    dp[i + 1][j + 1] = dp[i][j]
        return dp[m][n]