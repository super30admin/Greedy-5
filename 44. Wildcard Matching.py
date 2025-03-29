class Solution:
    # def isMatch(self, s: str, p: str) -> bool:
    #     n= len(s)+1
    #     m= len(p)+1

    #     dp=[[False for _ in range(n)] for _ in range(m)]

    #     # print(dp)
    #     dp[0][0]= True
    #     for i in range(1,m):
    #         if p[i-1]== '*':
    #             dp[i][0]= dp[i-1][0]
    #     # print('dp is',dp)
    #     for i in range(1,m):
    #         for j in range(1,n):
    #             print('i ',i,'j',j)
    #             if p[i-1] == s[j-1] or p[i-1]=='?':
    #                 dp[i][j]= dp[i-1][j-1]
    #             elif p[i-1] == '*':
    #                 dp[i][j]= dp[i-1][j] or dp[i][j-1]

    #     return dp[m-1][n-1]
    #time:O(n)
    #space:O(1)
    def isMatch(self, s: str, p: str) -> bool:
        sl=len(s)
        pl=len(p)
        sp,pp,sstar,pstar=0,0,-1,-1

        while sp <sl:
            print(sp,pp,sstar,pstar)
            if (pp <pl) and (s[sp] == p[pp] or p[pp]=='?'):
                sp+=1
                pp+=1
            elif (pp <pl) and p[pp]=='*':
                sstar, pstar = sp,pp
                pp+=1
            elif pstar == -1:
                return False
            else:
                sp= sstar+1
                pp= pstar+1
                sstar=sp
        while pp<pl:
            if p[pp] !='*':
                return False
            pp+=1
        return True
