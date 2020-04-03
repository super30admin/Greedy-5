class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        """
            https://leetcode.com/problems/wildcard-matching/
            Time Complexity - O(mn)
            Space Complexity - O(mn)
            'm' and 'n' is the length of input strings.
        """
        if s is None or p is None:
            return False
        s_len = len(s)
        p_len = len(p)
        dp = [[False for _ in range(p_len + 1)] for _ in range(s_len + 1)]

        # empty string matched with empty string
        dp[0][0] = True
        # char with empty string
        for r in range(1, s_len + 1):
            dp[r][0] = False
        # empty string with pattern string
        for c in range(1, p_len + 1):
            if p[c - 1] == '*':
                dp[0][c] = dp[0][c - 1]

        for r in range(1, s_len + 1):
            for c in range(1, p_len + 1):
                # if cur_char in both string match or if cur_pattern char is '?'
                # extend the string
                if (s[r - 1] == p[c - 1] or p[c - 1] == '?') and dp[r - 1][c - 1]:
                    dp[r][c] = True
                # if cur_pattern char is *
                # extend from prev string (taking * into account) or
                # ignore '*'
                elif p[c - 1] == '*' and (dp[r - 1][c] or dp[r][c - 1]):
                    dp[r][c] = True
        return dp[s_len][p_len]


if __name__ == '__main__':
    print(Solution().isMatch("aa", "a"))
    print(Solution().isMatch("adceb", "*a*b"))
    print(Solution().isMatch("aa", "*"))
