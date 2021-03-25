"""
Time Complexit:  case O(mn) for the 2 pointer approach
Space Complexity : O(mn) for DP approach and O(1) for the 2 pointer approach
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No
Approach would be the 2 pointer aproach. Here, we assign 2 extra pointers as p* and s*. We start iterating over the string and
the pattern. Now we have 4 cases. If char at s and p match, so we just increase the s and p pointer. In case, its a *. we just take the
0 case as of now, put p* at p and increment p, considering we did not expand *. Another case would be if p is still -1, we just
return False. At the end, if p is also not -1 and there is no match, that means we could have taken 1 case for *. So we go back,
increase s* by 1, put s there, put p to p*, and start our traversal from there. In the end when we reached end of s but we still have
values left in p, so if all are *, we could return True,but if any character is still left in p, there wont be a match and we can return
False.
"""

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        if s == p or p == "*":
            return True # Ex: 2
        sp,pp = 0,  0
        sStar, pStar = -1, -1
        
        while sp < len(s):
            #case 1 
            if pp < len(p) and (s[sp] == p[pp] or p[pp] == "?"): # since increase pp+ = 1 check pp < pl 
                sp += 1
                pp += 1
            elif pp < len(p) and p[pp] == "*": 
                 # Zero case
                sStar = sp
                pStar = pp
                pp += 1
            elif pStar == -1:
                return False
            else:  # One case
                sStar += 1
                sp = sStar # we skipped the char sStar or we cleverly matched that char to our *
                pp = pStar + 1
                
        while pp < len(p):
            if p[pp] != '*':
                return False
            pp += 1
        return True
