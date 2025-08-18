# Time Complexity: best case - O(min(m,n)), worst case - O(m*n) average case = O(m log n)
# Space Complexity: O(1)
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        sp, pp = 0, 0
        sStar, pStar = -1, -1

        while sp < len(s):
            if pp < len(p) and (s[sp] == p[pp] or p[pp] == '?'):
                sp += 1
                pp += 1
            elif pp < len(p) and p[pp] == '*':
                pStar = pp
                sStar = sp
                pp += 1
            elif pStar != -1:
                sStar += 1
                sp = sStar
                pp = pStar + 1
            else:
                return False

        while pp < len(p):
            if p[pp] != '*':
                return False
            pp += 1

        return True