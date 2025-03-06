# Problem 1: Wildcard Matching
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        sp = 0
        pp = 0
        sStar = -1
        pStar = -1

        while(sp < len(s)):
            if pp < len(p) and (s[sp] == p[pp] or p[pp] == "?"):
                sp += 1
                pp += 1
            elif pp < len(p) and p[pp] == "*":
                pStar = pp
                sStar = sp
                pp += 1
            else:
                if pStar == -1:
                    return False
                pp = pStar + 1
                sStar += 1
                sp = sStar

        if pp <= len(p):
            for k in range(pp,len(p)):
                if p[k] != "*":
                    return False

        return True

        