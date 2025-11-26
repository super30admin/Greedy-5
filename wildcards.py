# Time Complexity : O(M*N) where M is the length of the input string and N is the length of the pattern
# Space Complexity : O(M*N) for the DP table
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Your code here along with comments explaining your approach:
# I am using a dynamic programming approach to solve the wildcard matching problem.
# I create a DP table where dp[i][j] represents whether the first i characters of
# the string match the first j characters of the pattern. I initialize the table and
# fill it based on the characters in the string and pattern, considering the special        
# characters '?' and '*'. Finally, I return the value in the bottom-right corner of the table
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        m = len(s)
        n = len(p)
        dp = [False] * (n+1)
        dp[0] = True
        for j in range(1,n+1):
            pchar = p[j-1]
            if pchar == "*":
                dp[j] = dp[j-1]
        for i in range(1,m+1):
            dia = dp[0]
            dp[0] = False
            for j in range(1,n+1):
                temp = dp[j]
                pchar = p[j-1]
                if pchar == "*":
                    dp[j] = dp[j-1] or dp[j]
                else:
                    if pchar == s[i-1] or pchar == "?":
                        dp[j] = dia
                    else:
                        dp[j] = False
                dia = temp
        return dp[n]
        