#TC:O(m*n)
#SC:O(m*n)
#DP Solution

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        if not s and not p:
            return True
        # elif not s :
        #     return False
            
        if s==p or p=="*":
            return True
        dp=[[False]*(len(s)+1) for _ in range(len(p)+1)]
        # print(dp)
        dp[0][0]=True
        
        for i in range(1,len(dp)):
            if p[i-1]=="*":
                dp[i][0]=dp[i-1][0]
                j=1
                while j <len(dp[0]):
                    dp[i][j]=dp[i-1][j-1] or dp[i-1][j]
                    if dp[i][j]==True:
                        while j <len(dp[0]):
                            dp[i][j]=True
                            j+=1
                    j+=1
            elif p[i-1]=="?":
                for j in range(1,len(dp[0])): 
                    dp[i][j]=dp[i-1][j-1]
            else:
                for j in range(1,len(dp[0])):
                     if p[i-1]==s[j-1] and dp[i-1][j-1]:
                        dp[i][j]=True
        return dp[len(p)][len(s)]
                
