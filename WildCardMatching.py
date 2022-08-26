# Time Complexity : O(mn);
# Space Complexity : O(n);
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
#
#
class Solution(object):
    def isMatch(self, s, p):
        m = len(p)
        n = len(s)
        ptemp = -1
        stemp = -1
        pp = 0
        sp = 0
        while sp < n:
            if pp < m and (s[sp] == p[pp] or p[pp] == '?'):
                sp += 1
                pp += 1
            elif pp < m and p[pp] == '*':
                ptemp = pp
                stemp = sp
                pp += 1
            elif ptemp == -1:
                return False
            else:
                sp = stemp + 1
                pp = ptemp + 1
                stemp = sp
        while pp < m:
            if p[pp] != '*':
                return False
            pp += 1
        return True

# TC: O(m*n); SC: O(n)
# class Solution(object):
#     def isMatch(self, s, p):
#         m = len(p)
#         n = len(s)
#         dp = [False]*(n+1)
#         dp[0] = True
#         temp1 = False
#         for i in range(1, m+1):
#             for j in range(n+1):
#                 if p[i-1] != '*':
#                     temp = dp[j]
#                     if j == 0 or (p[i-1] != s[j-1] and p[i-1] != '?'):
#                         dp[j] = False
#                     elif p[i-1] == s[j-1] or p[i-1] == '?':
#                         dp[j] = temp1
#                     temp1 = temp
#                 elif p[i-1] == '*' and j > 0:
#                     dp[j] = dp[j] or dp[j-1]
#         return dp[-1]

# TC: O(m*n); SC: O(m*n)
# class Solution:
#     def isMatch(self, s: str, p: str) -> bool:
#         m = len(p)
#         n = len(s)
#         dp = []
#         for _ in range(m+1):
#             dp.append([False]*(n+1))
#         dp[0][0] = True
#         for i in range(1, m+1):
#             for j in range(n+1):
#                 if p[i-1] != '*':
#                     if j != 0 and (p[i-1] == s[j-1] or p[i-1] == '?'):
#                         dp[i][j] = dp[i-1][j-1]
#                 elif p[i-1] == '*':
#                     if j == 0:
#                         dp[i][j] = dp[i-1][j]
#                     else:
#                         dp[i][j] = dp[i-1][j] or dp[i][j-1]
#         return dp[-1][-1]
