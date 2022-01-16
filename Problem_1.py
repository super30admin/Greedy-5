class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        if s == p or p == '*':
            return True
        sl, pl = len(s), len(p)
        dp =  [[False] * (sl +1) for i in range(pl + 1)]
        dp[0][0] = True
        for i in range(1, len(dp)):
            for j in range(len(dp[0])):
                # *
                if p[i-1] == '*':
                    # zero
                    dp[i][j] = dp[i-1][j]
                    
                    # one
                    if j > 0:
                        dp[i][j] = dp[i][j] or dp[i][j-1]
                else:
                    if j > 0 and ( p[i-1] == s[j-1] or p[i-1] == '?'):
                        dp[i][j] = dp[i-1][j-1]
        return dp[pl][sl]

# Time Complexity: O(sl * pl)
# Space Complexity: O(sl * pl)

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        if s == p or p == '*':
            return True
        sl, pl = len(s), len(p)
        sp, pp = 0, 0
        sStar, pStar = -1, -1
        while sp < sl:
            if pp < pl and (s[sp] == p[pp] or p[pp] == '?'):
                pp += 1
                sp += 1
            elif pp < pl and p[pp] == '*':
                # Zero case
                sStar, pStar = sp, pp
                pp += 1
            elif pStar == -1:
                return False
            else:
                sStar += 1
                sp = sStar # Skips char
                pp = pStar + 1
                
        if pp < pl:
            while pp < pl:
                if p[pp] != '*':
                    return False
                pp += 1   

        return True

# Time Complexity: O( sl * log(pl) )
# Space Complexity: O(1)
                    