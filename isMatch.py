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
                
#Two pointer Solution:
#TC:O(n+m)
#SC:O(1)
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        if s==p or p=="*":
            return True
        sLen=len(s)
        pLen=len(p)
        sp=0
        pp=0
        sStar=-1
        pStar=-1
       
        while sp<sLen:
            if pp<pLen and (s[sp]==p[pp]  or p[pp]=="?"):
                sp+=1
                pp+=1
            elif pp<pLen and p[pp]=="*":
                sStar=sp
                pStar=pp
                pp+=1
            elif pStar==-1:
                return False
            else:
                pp = pStar + 1
                sp = sStar + 1
                sStar = sp
        while pp<pLen:
            if p[pp]!="*":
                return False
            pp+=1
        return True
                

    


