# Time:- O(m*n)
# Space:- O(m*n)
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        if s==p or s=="*":
            return True
        dp=[]
        for i in range(len(p)+1):
            dp.append([])
            for j in range(len(s)+1):
                dp[i].append(False)
        dp[0][0]=True
        for i in range(1,len(dp)):
            if p[i-1]=="*":
                dp[i][0]=dp[i-1][0]
                for j in range(1,len(dp[0])):
                    dp[i][j]=dp[i-1][j] or dp[i][j-1]
            elif p[i-1]=='?':
                for j in range(1,len(dp[0])):
                    dp[i][j]=dp[i-1][j-1]
            else:
                for j in range(1,len(dp[0])):
                    if s[j-1]==p[i-1]:
                        dp[i][j]=dp[i-1][j-1]
        return dp[-1][-1]