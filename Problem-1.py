class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        # if not s or not p:
        #     return False
        
        m,n=len(p),len(s)
        
        dp=[[False for i in range(n+1)] for j in range(m+1)]
        dp[0][0]=True
        for i in range(1,m+1):
            if p[i-1]=='*':
                dp[i][0]=dp[i-1][0]
                
        j=1
        for i in range(1,m+1):
            j=1
            while j<=n:
                if p[i-1]=='*':
                    dp[i][j]=dp[i-1][j] or dp[i-1][j-1]
                    if dp[i][j]==True:
                        while j<=n:
                            dp[i][j]=True
                            j+=1
                    
                elif p[i-1]==s[j-1] or p[i-1]=='?':
                    dp[i][j]=dp[i-1][j-1]
                
                elif p[i-1]!=s[j-1]:
                    dp[i][j]=False
                j+=1
        return dp[m][n]
        
        