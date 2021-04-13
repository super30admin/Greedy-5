# Time Complexity : O(MN)
# Space Complexity : O(1)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No


# Your code here along with comments explaining your approach

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        #can do two pointer solution instead of DP
        if s == p or p == '*':
            return True

        sp = 0
        pp = 0
        sStar = -1
        pStar = -1
        
        while sp < len(s):
            if pp < len(p) and (s[sp] == p[pp] or p[pp] == '?'):
                sp += 1
                pp += 1
            elif pp < len(p) and p[pp] == '*':
                sStar = sp
                pStar = pp
                pp += 1
            elif pStar == -1:
                return False
            else:
                sStar += 1
                sp = sStar
                pp = pStar + 1
        while pp < len(p):
            if p[pp] != '*':
                return False
            pp += 1
            
        return True