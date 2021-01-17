"""
TC = O(MN) , M #len of S and N #len of P
SC= O(1)

Two pointers and sstar and Pstar for handling "*" case and reset between zero case and one case
"""
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        if s == p or p == "*":
            return True
        sl = len(s)
        pl = len(p)
        sstar, pstar = -1, -1
        sp, pp = 0, 0
        while sp < sl:
            #Case 1 -if both ptr at s and p are same or if its "?"
            if pp < pl and (s[sp] == p[pp] or p[pp] == '?'):
                sp += 1
                pp += 1 #increasing pp so check boundary case
            elif pp < pl and p[pp] == '*':
            #case 2 - if * we take zero case
                sstar = sp
                pstar = pp
                pp += 1
            elif pstar == -1: # Case 3- didn't come up with * zero case
                return False
            else:
            #Case 4 -   one case 
                sstar += 1
                sp = sstar
                pp = pstar+1
        while pp < pl:
            if p[pp] != '*':
                return False
            pp += 1
        return True