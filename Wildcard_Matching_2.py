# Time complexity : O(m*log(n))
# Space complexity : O(1)
# Leetcode : Solved and submitted

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        # check for base cases
        if p == s or p == '*':
            return True
        
        # find the lengths and set default value for pointers
        sl = len(s); pl = len(p)
        sp = 0; pp = 0; pstar = -1; sstar = -1
        
        # traverse until we reach the end of source strgin
        while sp < sl:
            # if the characters match or p has '?' and we are within boundary for p
            if pp < pl and (p[pp] == s[sp] or p[pp] == '?'):
                
                # increment the pointers
                sp += 1
                pp += 1
            
            # if we encounter a star in p
            elif pp < pl and p[pp] == '*':
                # mark the pointers in strings and move the pointer pp by 1
                sstar = sp
                pstar = pp
                pp += 1
            
            # if until now we have not encountered a star, then return False
            elif pstar == -1:
                return False
            else:
                # else if we have string left and it has been unmatchde
                # shift the pointers as previously we have assumed the star to take 0 chars
                sp = sstar + 1
                sstar = sp
                pp = pstar + 1
        
        # at the end, we can also have some chars, left, if any char other than '*' is there, we need to return False
        while pp < pl:
            if p[pp] != '*':
                return False
            pp += 1
            
        return True
