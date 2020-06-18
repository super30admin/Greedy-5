"""
// Time Complexity : O(M + N)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
Given Below
"""


class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        if p == '*':
            return True
        dp = [[False for _ in range(len(s)+1)] for _ in range(len(p)+1)]
        dp[0][0] = True
        
        for p_idx in range(1,len(dp)):
            if p[p_idx-1] == '*':
                #handle the case for *
                
                s_idx = 1
                
                #idea is we need to find the first s_idx for which string matches the pattern
                while not dp[p_idx-1][s_idx-1] and s_idx < len(s) + 1:
                    s_idx+=1
                
                # if string matches the pattern, then string also matches the (pattern)*
                dp[p_idx][s_idx-1] = dp[p_idx-1][s_idx-1]
                
                #once we found the match till now, rest can be assigned to True
                while s_idx < len(s) + 1:
                    dp[p_idx][s_idx] = True
                    s_idx+=1
                
            elif p[p_idx-1] == '?':
                #if the pattern char is ?, then we can just pull down the matched value of string and pattern from upper diagonal
                for s_idx in range(1,len(s)+1):
                    dp[p_idx][s_idx] = dp[p_idx-1][s_idx-1]
                
            else:
                #handle the case for normal alphabets
                # in this case, we need to check the last matched value and check if current char matches the one in pattern
                for s_idx in range(1,len(s)+1):
                    dp[p_idx][s_idx] = dp[p_idx-1][s_idx-1] and p[p_idx-1] == s[s_idx-1]
                    
        return dp[len(p)][len(s)]
        
        """
        Neat - two pointer with backtracking solution
        Idea is to iterate through the s and p such that we use two auxilliary pointer s_star and p_star that denotes the marking of last occuring star position, which essentially helps us in backtracking to that position + 1, if we don't find success with zero case for * and by doing backtracking, we would have ensured the we match multiple characters with * along the way
        
        Finally if the p pointer is still in play, and if  last char is not *, then wereturn false else we return True
        """
        s_idx,p_idx,s_star,p_star = 0,0,-1,-1
        while s_idx < len(s):
            if p_idx < len(p) and p[p_idx] in [s[s_idx],'?']:
                s_idx+=1
                p_idx+=1
            elif p_idx < len(p) and p[p_idx] == '*':
                s_star = s_idx
                p_star = p_idx
                p_idx+=1
            elif p_star == -1:
                #check if there is no star before,then return false
                return False
            else:
                s_idx = s_star + 1
                p_idx = p_star + 1
                s_star = s_idx
        
#         while p_idx < len(p):
#             if p[p_idx] !='*':
#                 return False
#             p_idx+=1
        
#         return True
    
        #more cleaner end - all remaining characters must be *
        return all(x == '*' for x in p[p_idx:])