class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        if s == p or p == "*":
            return True

        sl = len(s)
        pl = len(p)
        pp = 0
        sp = 0
        p_star = -1
        s_star = -1

        while sp < sl:
            if pp < pl and (s[sp] == p[pp] or p[pp] == "?"):
                sp += 1
                pp += 1
            elif pp < pl and p[pp] == "*":
                p_star = pp
                s_star = sp
                pp += 1
            elif p_star == -1:
                return False
            else:
                sp = s_star + 1
                s_star = sp
                pp = p_star + 1

        while pp < pl:
            if p[pp] != "*":
                return False
            pp += 1

        return True
# Two Pointers
# Time Complexity: Worst case O(m * n). Avg min(len(s), len(p))
# Space Complexity: O(1)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No


