'''
TC: O(s+p)
SC: O(1)
'''
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        plen = len(p)
        slen = len(s)
        sp, pp, ss, ps = 0, 0, -1, -1
        
        while sp < slen:
            if pp < plen and (s[sp] == p[pp] or p[pp] == "?"):
                sp += 1
                pp += 1
            elif pp < plen and p[pp] == "*":
                ss = sp
                ps = pp
                pp += 1
            elif ps == -1:
                return False
            else:
                ss += 1
                sp = ss
                pp = ps + 1
        
        while pp < plen and p[pp] == "*":
            pp += 1
        
        return pp == plen