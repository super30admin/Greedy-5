#Time Complexity : O(n*m)
#Space Complexity : O(1) 
#Did this code successfully run on Leetcode : Yes

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        if s == p or p == "*":
            return True

        pLen, sLen = len(p), len(s)

        P, S, pStar, sStar = 0, 0, -1, -1
        while S < sLen:
            if P < pLen and (p[P] == s[S] or p[P] == "?"):
                S += 1
                P += 1
            elif P < pLen and p[P] == "*":
                pStar = P
                sStar = S
                P += 1
            elif pStar == -1:
                return False
            else:
                sStar += 1
                S = sStar
                P = pStar + 1

        while P < pLen:
            if p[P] != "*":
                return False
            P += 1

        return True



#         dp = [[False for _ in range(sLen+1)] for _ in range(pLen+1)]
#         dp[0][0] = True

#         for i in range(1, pLen+1):
#             #case 1: char is *
#             if p[i-1] == "*":
#                 dp[i][0] = dp[i-1][0]
#                 j = 1
#                 while j < sLen+1:
#                     dp[i][j] = dp[i-1][j] or dp[i-1][j-1]
#                     if dp[i][j]:
#                         while j < sLen+1:
#                             dp[i][j] = True
#                             j += 1
#                     j += 1
#             #case 2: char is ?
#             elif p[i-1] == "?":
#                 for j in range(1, sLen+1):
#                     dp[i][j] = dp[i-1][j-1]
#             #case 3: char is alphabet
#             else:
#                 for j in range(1, sLen+1):
#                     dp[i][j] = p[i-1] == s[i-1] and dp[i-1][j-1]

#         return dp[-1][-1]
