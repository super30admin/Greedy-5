# TC: Best-> min(len(s), len(p));   Avg->O(mlog(n))
# SC: O(1)
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        if s == p or p == '*':
            return True
        
        sp = pp = 0
        s_str = p_str = -1
        
        while sp < len(s):
            if pp < len(p) and (s[sp]==p[pp] or p[pp]=='?'):
                sp += 1
                pp += 1
            elif pp < len(p) and p[pp] == '*':
                p_str = pp
                s_str = sp
                pp += 1
            elif p_str == -1:
                return False
            else:
                pp = p_str + 1
                sp = s_str + 1
                s_str = sp
        for i in range(pp, len(p)):
            if p[i] != '*':
                return False
        return True


# Approach: DP
# TC: O(mn)
# SC: O(mn)
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        # base
        if s == p or p == "*":
            return True
        
        m, n = len(p), len(s)
        
        dp = [[False for _ in range(n+1)] for _ in range(m+1)]
        
        dp[0][0] = True
        
        for i in range(1,m+1):
            for j in range(n+1):
                if p[i-1] == '*':
                    if j == 0:
                        dp[i][j] = dp[i-1][j]
                    else:
                        dp[i][j] = dp[i-1][j] or dp[i][j-1]
                elif j > 0 and (p[i-1] == s[j-1] or p[i-1] == '?'):
                    dp[i][j] = dp[i-1][j-1]
        return dp[m][n]
