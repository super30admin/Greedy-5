# TC: Best case: O(S.N) and avergae case: O(S log P) where S is the length of S and P is the length of the pattern.  
# SC: O(1) as we do not use any extra space.

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        if s is p or p == '*': 
            return True
        
        sl = len(s)
        pl = len(p)
        sp, pp = 0, 0
        temp_s, temp_p = -1, -1
        
        while sp < sl: 
            if pp < pl and (s[sp] == p[pp] or p[pp] == '?'): 
                sp += 1
                pp += 1
            elif pp < pl and p[pp] == '*': 
#                 introduce pointers to store the current position in order to return back if we have to consider the 1 case
                temp_s = sp
                temp_p = pp
                pp += 1
            elif temp_p == -1: 
                return False
            else: 
                pp = temp_p + 1
                sp = temp_s + 1
                temp_s = sp
        
        return all(x == '*' for x in p[pp:])
