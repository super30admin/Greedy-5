#time O(N)
#space O(N)
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        # if len(p)==0 and len(s)==0:
        #     return True
        # if len(p)==0 or len(s)==0:
        #     return False
        # dp=[["F" for i in range(len(s))] for i in range(len(p))]
        # # print(dp)
        # if p[0]!=s[0] and p[0].isalpha():
        #     return False
        # for i in range(len(s)):
        #     if p[0]=="?":
        #         dp[0][0]="T"
        #         continue
        #     if p[0]=="*":
        #         dp[0][i]="T"
        #     if p[0]==s[0]:
        #         dp[0][0]="T"
        # # print(dp)
        # for i in range(len(p)):
        #     if s[0]==p[i] or p[i]=="*" or p[i]=="?":
        #         dp[i][0]="T"
        #     else:
        #         dp[i][0]="F"
        # for i in range(1,len(p)):
        #     for j in range(1,len(s)):
        #         if p[i]=="*":
        #             dp[i][j]="T"
        #         elif p[i]=="?":
        #             dp[i][j]=dp[i-1][j-1]
        #         elif p[i]==s[j]:
        #             dp[i][j]=dp[i-1][j-1]
        #         else:
        #             dp[i][j]="F"
        # print(dp)
        # if dp[len(p)-1][len(s)-1] =="F":
        #     return False
        # # return True
        # return dp[len(p)-1][len(s)-1]         
            def ismatch(a,b):
                if a=="?":
                    return True
                if a==b:
                    return True
                return False
            dp =[[False for _ in range(len(s)+1)] for _ in range(len(p)+1)]
            dp[0][0] = True
            for i in range(0,len(p)):
                if p[i]=="*":
                    dp[i+1][0] = True
                else:
                    break
            for i,cp in enumerate(p,1):
                for j,cs in enumerate(s,1):
                    if cp=="*":
                        dp[i][j] = dp[i][j-1]|dp[i-1][j]
                    else:
                        dp[i][j] = dp[i-1][j-1] and ismatch(cp,cs)
            # print(dp)
            return dp[-1][-1]               