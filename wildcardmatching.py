class Solution:
    	def isMatch(self, s: str, p: str) -> bool:
            m = len(p)
            n = len(s)
            dp = [[True for i in range(m+1)] for j in range(n+1)]
            i = 0
            for j in range(m+1):
                if j == 0:
                    dp[i][j] = True
                else:
                    if p[j-1] == '*':
                        dp[i][j] = dp[i][j-1]
                    else:
                        dp[i][j] = FalsefF

            for j in range(1, n+1):
                dp[j][0] = False

            for i in range(1, n+1):
                for j in range(1, m+1):
                    if s[i-1] == p[j-1] or p[j-1] == '?':
                        dp[i][j] = dp[i-1][j-1]  

                    elif p[j-1] == '*':
                        if dp[i-1][j] == True or dp[i][j-1] == True:
                            dp[i][j] = True     
                        # Else
                        else:
                            dp[i][j] = False    
                    else:
                        dp[i][j] = False        
            return dp[n][m]