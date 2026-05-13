# problem 1

## Problem1: Wildcard Matching (https://leetcode.com/problems/wildcard-matching/)

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        m = len(s)
        n = len(p)
        dp_arr = [[False]*(n+1) for _ in range(m+1)]
        dp_arr[0][0] = True
        # fill first row
        for i in range(1,n+1):
            prev_char = p[i-1]
            if prev_char == '*':
                dp_arr[0][i] = dp_arr[0][i-1]
        for i in range(1,m+1):
            for j in range(1,n+1):
                prev_char = p[j-1]
                if prev_char == '*':
                    dp_arr[i][j] = dp_arr[i-1][j] or dp_arr[i][j-1]
                else:
                    if prev_char == s[i-1] or prev_char == '?':
                        dp_arr[i][j] = dp_arr[i-1][j-1] 
                    else:
                        dp_arr[i][j] = False   
        return dp_arr[m][n]