class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        ns = len(s)
        np = len(p)

        dp = [[False] * (ns+1) for _ in range(np+1) ]
        dp[0][0] = True
        for i in range(1,np+1):
            for j in range(ns+1):
                pchar = p[i-1]
                if j == 0:
                    if pchar == '*':
                    # 0 and 1 case
                        dp[i][j] = dp[i-1][j]
                    else:
                        dp[i][j] = False
                else:
                    if pchar == '*':
                    # 0 and 1 case
                        dp[i][j] = dp[i-1][j] or dp[i][j-1]
                    else:
                        if pchar == s[j-1] or pchar == '?':
                            dp[i][j] = dp[i-1][j-1]
                        else: 
                            dp[i][j] = False
        return dp[np][ns]

