#All TC passed on leetcode

class Solution:
    def isMatch(self, s: str, p: str) -> bool:

        #Using KMP approach.
        #time complexity - O(s*p) - lengths of both strings, but averagely it is O(slogp)
        #space complexity - O(1) - can be optimised to 1D array
        pl = len(p)
        sl = len(s)

        if(p==s or p=="*"):
            return True
        
        sp=0
        pp=0
        sStar=-1
        pStar=-1

        while sp<sl:
            if pp<pl and (s[sp]==p[pp] or p[pp]=="?"):
                sp+=1
                pp+=1

            elif pp<pl and p[pp]=="*":
                pStar = pp
                sStar = sp
                pp+=1

            elif pStar==-1:
                return False

            else:
                sp = sStar+1
                sStar = sp
                pp = pStar+1
        
        while pp<pl:
            if p[pp]=="*":
                pp+=1
            else:
                return False
        
        return True




#-------------------------------------------------------------OR-------------------------------------------------------


        #Using DP approach.
        #time complexity - O(s*p) - lengths of both strings
        #space complexity - O(s*p) - can be optimised to 1D array
        pl = len(p)
        sl = len(s)

        if(p==s or p=="*"):
            return True

        dp = [[False]*(sl+1) for i in range(pl+1)]

        dp[0][0] = True

        #0th column
        for i in range(1, pl+1):
            if p[i-1]=="*":
                dp[i][0] = dp[i-1][0]
        
        for i in range(1, pl+1):
            for j in range(1, sl+1):
                if p[i-1]!="*":
                    if s[j-1]==p[i-1] or p[i-1]=="?":
                        dp[i][j] = dp[i-1][j-1]
                else:
                    dp[i][j] = (dp[i-1][j] or dp[i][j-1])

        return dp[pl][sl]