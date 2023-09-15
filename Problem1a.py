class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        '''
        Time complexity -->O(m * n), m - length of string s and n - length of pattern p
        Space complexity -->O(m * n) due to the DP array that has the same dimensions as the input strings.
        '''
        # Check for special cases where s equals p or p is "*"
        if s == p or p == "*":
            return True

        m, n = len(s), len(p)

        # Initialize a 2D DP array with all False values
        dp = [[False] * (n + 1) for _ in range(m + 1)]

        # Base case: empty pattern matches empty string
        dp[0][0] = True

        # Initialize the first row based on the pattern
        for j in range(1, n + 1):
            if p[j - 1] == '*':
                dp[0][j] = dp[0][j - 1]

        # DP computation
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                if p[j - 1] == s[i - 1] or p[j - 1] == '?':
                    dp[i][j] = dp[i - 1][j - 1]
                elif p[j - 1] == '*':
                    dp[i][j] = dp[i - 1][j] or dp[i][j - 1]

        return dp[m][n]
