# DP Solution

# Time: O(n*m)
# Space: O(n*m)
class Solution(object):
    def isMatch(self, s, p):
        """
        :type s: str
        :type p: str
        :rtype: bool
        """
        dp = [[False for i in range(len(s)+1)] for j in range(len(p)+1)]
        dp[0][0] = True
        for i in range(1, len(p)+1):
            if p[i-1] == '*':
                #print(i, p[i-1], dp[i-1][0])
                dp[i][0] = dp[i-1][0]
        #print(dp)
        for i in range(1, len(p)+1):
            for j in range(1, len(s)+1):
                if p[i-1] == '*':
                    not_taken = dp[i-1][j]
                    taken = dp[i][j-1]
                    dp[i][j] = not_taken or taken
                else:
                    if p[i-1] == s[j-1] or p[i-1] == '?':
                        dp[i][j] = dp[i-1][j-1]
        #print(dp)
        return dp[len(p)][len(s)]
                        
            
# Greedy
# Time: O(len(s)+len(p))
# Space: O(1)
class Solution(object):
    def isMatch(self, s, p):
        """
        :type s: str
        :type p: str
        :rtype: bool
        """
        pp = 0
        sp = 0
        p_st = -1
        s_st = -1
        while sp < len(s):
            if pp < len(p) and (s[sp] == p[pp] or p[pp] == '?'):
                sp += 1
                pp += 1
                
            elif pp < len(p) and p[pp] == '*':
                p_st = pp
                s_st = sp
                pp += 1
            elif p_st == -1:
                return False
            else:
                # Taking the '*' untill there is a match
                sp = s_st + 1
                pp = p_st + 1
                s_st += 1
        while pp < len(p):
            if p[pp] != '*':
                return False
            pp += 1
        return True
            
        
