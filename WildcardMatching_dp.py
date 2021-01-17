"""
TC O(MN) where M is len of s and N is len of P
SC O(MN) for dp table 

in dp table you add extra row and column for "-". then for each column check any case . for "*" if a column becomes True make rest True.

dp table -*a  match -adc
"""
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        if s==p or p=="*":
            return True
        sl=len(s)
        pl=len(p)
        dp=[[False for _ in range(sl+1)] for i in range(pl+1)] #extra column and row for "-" 
        dp[0][0]=True
        for i in range(1,len(dp)):
            #Case 1 - "*"
            if p[i-1]=='*':#i-1 because we have extra row and col
                dp[i][0]=dp[i-1][0]
                j=1
                while j<len(dp[0]):
                    dp[i][j]=dp[i-1][j] or dp[i-1][j-1]
                    if dp[i][j]:#once its true rest of the col are True
                        while j<len(dp[0]):
                            dp[i][j]=True
                            j+=1
                    j+=1
             #Case 2 if its "?"    
            elif p[i-1]=='?':
                for j in range(1,len(dp[0])):
                    dp[i][j]=dp[i-1][j-1] #Diagonal above
            else:
                 for j in range(1,len(dp[0])):
                        dp[i][j]= (p[i-1]==s[j-1]) and dp[i-1][j-1] #diagonal
        return dp[-1][-1]