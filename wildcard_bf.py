#time-m*n, space-m*n
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        dp=[[False for i in range(len(s)+1)] for i in range(len(p)+1)]
        dp[0][0]=True
       
        
        
        for i in range(1,len(dp)):
            
            for j in range(0,len(dp[0])):
               
                
                if j>0 and (p[i-1]==s[j-1] or p[i-1]=='?'):
                    
                    dp[i][j]=dp[i-1][j-1]
                elif p[i-1]=='*':
                    if j>0:
                        dp[i][j]=dp[i-1][j] or dp[i][j-1]
                    else:
                        dp[i][j]=dp[i-1][j]
                        
        
        return dp[-1][-1]
                