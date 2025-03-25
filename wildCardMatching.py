class Solution:
    # TC : O(m*n)
    # SC : O(m*n)
    def isMatch(self, s: str, p: str) -> bool:
        if s == p:
            return True
        if s is None or p is None:
            return False
        m,n = len(p),len(s)
        dp = [[False] * (n+1) for _ in range(m+1)]
        dp[0][0] = True
        for i in range(1,m+1):
            for j in range(n+1):
                if p[i-1] != "*":
                    if j> 0 and (p[i-1] == s[j-1] or p[i-1] == "?"):
                        dp[i][j] = dp[i-1][j-1]
                else:
                    # have * 
                    # zero case : above row
                    # one case : prev col same row if j > 0
                    dp[i][j] = dp[i-1][j]
                    if j > 0:
                        # taking 1 case
                        dp[i][j] = dp[i][j] or dp[i][j-1]
        return dp[m][n]


        
        