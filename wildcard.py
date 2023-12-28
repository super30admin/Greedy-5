# Time: O(slogp)
# Space: O(1)
# Did it run on Leetcode: yes

class Solution(object):
    def isMatch(self, s, p):
        """
        :type s: str
        :type p: str
        :rtype: bool
        """
        # O(mn), O(mn) dp matrix
        # if(s==p or p=='*'):
        #     return True
        # sl=len(s)
        # pl=len(p)
        # dp=[[False for _ in range(pl+1)] for _ in range(sl+1)]
        # dp[0][0]=True
        # for j in range(1,pl+1):
        #     if(p[j-1]=='*'):
        #         dp[0][j]=dp[0][j-1]
        # for i in range(1,sl+1):
        #     for j in range(1,pl+1):
        #         if(p[j-1]=='*'):
        #             dp[i][j]=dp[i][j-1] or dp[i-1][j]
        #         else:
        #             if(p[j-1] == s[i-1] or p[j-1]=='?'):
        #                 dp[i][j]=dp[i-1][j-1]
        # return dp[sl][pl]

        # O(mn), O(n) 1d array
        # if(s==p or p=='*'):
        #     return True
        # sl=len(s)
        # pl=len(p)
        # dp=[False for _ in range(pl+1)]
        # dp[0]=True
        # for j in range(1,pl+1):
        #     if(p[j-1]=='*'):
        #         dp[j]=dp[j-1]
        # for i in range(1,sl+1):
        #     diagup=False
        #     if(i==1):
        #         diagup=True
        #         dp[0]=False
        #     for j in range(1,pl+1):
        #         temp=dp[j]
        #         if(p[j-1]=='*'):
        #             dp[j]=dp[j-1] or dp[j]
        #         else:
        #             if(p[j-1] == s[i-1] or p[j-1]=='?'):
        #                 dp[j]=diagup
        #             else:
        #                 dp[j]=False
        #         diagup=temp
        # return dp[pl]

        # optimized two pinters:
        if(s==p or p=='*'):
            return True
        sl=len(s)
        pl=len(p)
        sp=0
        pp=0
        sstar=-1
        pstar=-1
        while(sp<sl):
            if(pp<pl and (s[sp]==p[pp] or p[pp]=='?')):
                sp+=1
                pp+=1
            elif(pp<pl and p[pp]=='*'):
                pstar=pp
                sstar=sp
                pp+=1
            elif(pstar==-1):
                return False
            else:
                sp=sstar+1
                pp=pstar+1
                sstar=sp
        while(pp<pl):
            if(p[pp]=='*'):
                pp+=1
            else:
                return False
        return True