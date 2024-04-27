'''
Time Complexity - O(mn), average case = O(mlogn)
Space Complexity - O(mn) for dp and O(1) for 2 pointer

Works on Leetcode
'''
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        #Using DP
        #create a DP
        # m = len(p)
        # n = len(s)
        # #We need to consider a null string shown by "-" on paper
        # dp = [[False for j in range(n+1)] for i in range(m+1)]
        # dp[0][0] = True
        # #we initially calculate for null string in sample and complete pattern
        # for i in range(1,m+1):
        #     if p[i-1] == "*":
        #         dp[i][0] = dp[i-1][0]
        
        
        # for i in range(1, m+1):
        #     for j in range(1, n+1):
        #         #when current character in the pattern is a "*" we have two possibilities we need to take an or
        #         if p[i-1] == "*":
        #             dp[i][j] = dp[i-1][j] or dp[i][j-1]
        #         #when both character in pattern as well as source string match, we consider the previous case (diagonally left up)
        #         elif p[i-1] == s[j-1] or p[i-1] == "?":
        #             dp[i][j] = dp[i-1][j-1]
        #         else:
        #             dp[i][j] = False
        # return dp[m][n]

        #Using two pointers
        sl = len(s)
        pl = len(p)
        sp, pp = 0,0 
        pStar, sStar = -1,-1
        while sp < sl:
            if pp < pl and (s[sp] == p[pp] or p[pp] == "?"):
                sp+=1 
                pp+=1
            elif pp<pl and p[pp] == "*":
                pStar = pp
                sStar = sp
                pp+=1
            elif pStar == -1:
                return False
            else:
                sStar = sStar + 1
                sp = sStar
                pp = pStar + 1
        while pp < pl:
            if p[pp] != "*":
                return False
            pp+=1
        return True


        