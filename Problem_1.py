"""
Time Complexity : O(mn) for DP approach where m and n are length of string and pattern respectively but it would be 
worst case O(mn) for the 2 pointer approach
Space Complexity : O(mn) for DP approach and O(1) for the 2 pointer approach
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No

Your code here along with comments explaining your approach:
I have implemented 2 solutions here, one with DP(commented) and the other being 2 pointer approach. For implementing DP, we make 
a 2D matrix of length of string and patter (+1) and start iterating over string and pattern. Majorly, we have 3 cases here. In case
the char in string and pattern match, we have to check if the string previous to both of them matches or not. For that, we can
check our grid for diagonal left. Another case could be that pattern has a '?'. As it can match to any character, the whole column
is filled with value in grid for diagonal left up as the string and pattern need to match before that '?'. Another case is '*'. Here,
we have a 0 case and a 1 case. ) case is directly above and 1 case is diagonal left up. But in case we find one to be True, the whole
column has to be made True. This is because, a * can be matched to any number of same and/or different characters. 
Another approach would be the 2 pointer aproach. Here, we assign 2 extra pointers as p* and s*. We start iterating over the string and
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
            return True
        sl = len(s)
        pl = len(p)
        sp, pp = 0, 0
        sstar, pstar = -1, -1
        while sp < sl:
            if pp < pl and (s[sp] == p[pp] or p[pp] == '?'):
                sp += 1
                pp += 1
            elif pp < pl and p[pp] == '*':
                sstar = sp
                pstar = pp
                pp += 1
            elif pstar == -1:
                return False
            else:
                sstar += 1
                sp = sstar
                pp = pstar+1
        while pp < pl:
            if p[pp] != '*':
                return False
            pp += 1
        return True


# class Solution:
#     def isMatch(self, s: str, p: str) -> bool:
#         if s==p or p=="*":
#             return True
#         sl=len(s)
#         pl=len(p)
#         grid=[[False for i in range(sl+1)] for j in range(pl+1)]
#         grid[0][0]=True
#         for i in range(1,pl+1):
#             if p[i-1]=='*':
#                 grid[i][0]=grid[i-1][0]
#                 j=1
#                 while j<sl+1:
#                     grid[i][j]=grid[i-1][j] or grid[i-1][j-1]
#                     if grid[i][j]:
#                         while j<sl+1:
#                             grid[i][j]=True
#                             j+=1
#                     j+=1
#             elif p[i-1]=='?':
#                 for j in range(1,sl+1):
#                     grid[i][j]=grid[i-1][j-1]
#             else:
#                 for j in range(1,sl+1):
#                     grid[i][j]= p[i-1]==s[j-1] and grid[i-1][j-1]
#         return grid[-1][-1]
