'''
Accepted on leetcode(44)
time - O(N), N- len of s
space - O(1)

'''


class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        sLen = len(s)
        pLen = len(p)

        SP = 0
        PP = 0

        star_idx = -1
        s_star_idx = -1

        # iterate until the len of string(s)
        while (SP < sLen):
            # case 1: if PP not out of bounds and p and s chars are equal or p is a ? then increment both pointers.
            if PP < pLen and (p[PP] == s[SP] or p[PP] == '?'):
                SP += 1
                PP += 1
            # case 2: if the char is *, then skip *. And get the index of star for both PP and SP and increment PP to skip *.
            elif PP < pLen and p[PP] == '*':
                star_idx = PP
                s_star_idx = SP
                PP += 1
            # case 3: if no * exists in the pattern(p) then simply return -1 as the s and p dont match.
            elif star_idx == -1:
                return False
            # case 4: if we consider star and there are multiple characters for it. Increment SP but dont increment PP until all characters are satisfied.
            else:
                PP = star_idx + 1
                SP = s_star_idx + 1
                s_star_idx = SP

        # if PP is not exhausted
        for i in range(PP, pLen):
            if p[i] != '*':
                return False

        return True