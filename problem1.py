# TC:O(m*n)
# SC:O(m*n)

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        if s is p or p == "*":
            return True

        m = len(p)
        n = len(s)

        dp = [[False] * (n + 1) for i in range(m + 1)]

        dp[0][0] = True

        for i in range(1, m + 1):
            for j in range(0, n + 1):
                if p[i - 1] != "*":
                    if j > 0 and (p[i - 1] == s[j - 1] or p[i - 1] == "?"):
                        dp[i][j] = dp[i - 1][j - 1]

                else:
                    if j > 0:
                        dp[i][j] = dp[i - 1][j] or dp[i][j - 1]
                    else:
                        dp[i][j] = dp[i - 1][j]

        return dp[-1][-1]




# TC:O(min(m + n))
# SC:O(1)
# Approach 2:

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        if s is p or p == "*":
            return True

        m = len(p)
        n = len(s)

        sp = 0
        pp = 0

        sstar = -1
        pstar = -1

        while sp < n:
            if pp < m and (s[sp] == p[pp] or p[pp] == "?"):
                sp += 1
                pp += 1

            elif pp < m and p[pp] == "*":
                sstar = sp
                pstar = pp
                pp += 1

            elif pstar == -1:
                return False

            else:
                pp = pstar + 1
                sp = sstar + 1
                sstar = sp

        while pp < m:
            if p[pp] != "*":
                return False
            pp += 1

        return True





