# class Solution:
#     def isMatch(self, s: str, p: str) -> bool:
#         m,n= len(s), len(p)
#         dp = [[False] *(n+1) for _ in range(m+1)]
#         dp[0][0] = True
#         for j in range(1,n+1):
#             charp = p[j-1]
#             if charp == "*":
#                 dp[0][j] = dp[0][j-1]
        
#         for i in range(1,m+1):
#             for j in range(1,n+1):
#                 charp = p[j-1]
#                 if charp == "*":
#                     dp[i][j] = dp[i-1][j] or dp[i][j-1]
#                 elif charp == s[i-1] or charp == "?":
#                     dp[i][j] = dp[i-1][j-1]
#                 else:
#                     dp[i][j] = False
#         return dp[m][n]

# class Solution:
#     def isMatch(self, s: str, p: str) -> bool:
#         m,n= len(s), len(p)
#         dp = [False] *(n+1)
#         dp[0] = True
#         for j in range(1,n+1):
#             charp = p[j-1]
#             if charp == "*":
#                 dp[j] = dp[j-1]
        
#         for i in range(1,m+1):
#             diagup = dp[0]
#             dp[0] = False
#             for j in range(1,n+1):
#                 temp = dp[j]
#                 charp = p[j-1]
#                 if charp == "*":
#                     dp[j] = dp[j] or dp[j-1]
#                 elif charp == s[i-1] or charp == "?":
#                     dp[j] = diagup
#                 else:
#                     dp[j] = False
#                 diagup = temp
#         return dp[n]

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        sp, pp = 0,0
        sstar,pstar = -1,-1
        while sp<len(s):
            if pp < len(p) and (s[sp] == p[pp] or p[pp] == '?'):
                sp+=1
                pp+=1
            elif pp < len(p) and p[pp] == '*':
                sstar = sp
                pstar = pp
                pp+=1
            elif pstar!=-1: # if we have * somewhere then move the sstarand sp also pp to pstar
                sstar +=1
                sp = sstar
                pp = pstar +1
            else:
                return False
        while pp<len(p):
            if p[pp]!= '*':
                return False
            pp+=1
        return True



        
        