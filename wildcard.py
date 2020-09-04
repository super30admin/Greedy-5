# Using dynamic programming
# Time complexity - O(nm)
# Space complexity - O(nm)
# Did this solution run on leetcode? - yes
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        sLen = len(s)   # length of string s
        pLen = len(p)   # length of string p
        
        # create a dp array
        dp = [[False for _ in range(sLen+1)] for _ in range(pLen+1)]
        dp[0][0] = True     # case where empty string matches with the empty pattern.
        
        for i in range(1, pLen+1):
            if p[i-1] == "*":
                for j in range(sLen+1):
                    if j==0:
                        dp[i][j] = dp[i-1][j]   # mark as True if True for 0 case
                    else:
                        dp[i][j] = dp[i-1][j] or dp[i-1][j-1] or dp[i][j-1]  # 1case/0case/if it is True till the previous character in the string.
            else:
                for j in range(1, sLen+1):
                    if p[i-1]=="?" or p[i-1]==s[j-1]:
                        dp[i][j] = dp[i-1][j-1]
        
        return dp[-1][-1]


# By keeping track of a single row.
# Time complexity - O(nm)    where n is the length of string s, m is the length of string p.
# Space complexity - O(2n)   where n is the length of string s.
# Did this solution run on leetcode? - yes
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        sLen = len(s)   # length of string s
        pLen = len(p)   # length of string p
        
        # create a dp array
        prev = [False for _ in range(sLen+1)] 
        prev[0] = True     # case where empty string matches with the empty pattern.
        
        for i in range(1, pLen+1):
            curr = [False for _ in range(sLen+1)]
            if p[i-1] == "*":
                for j in range(sLen+1):
                    if j==0:
                        curr[j] = prev[j]   # mark as True if True for 0 case
                    else:
                        curr[j] = prev[j] or prev[j-1] or curr[j-1]  # 1case/0case/if it is True till the previous character in the string.
            else:
                for j in range(1, sLen+1):
                    if p[i-1]=="?" or p[i-1]==s[j-1]:
                        curr[j] = prev[j-1]
            
            # assign previous to current
            prev = curr
        
        return prev[-1]


# Time complexity - O(min(S, P)) # avg case - O(SlogP)
# Space complexity - O(1)
# Did this solution run on leetcode? - yes
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        sptr, pptr = 0, 0  # keep two pointers at the starting position of both s and p.
        pwild = -1
        swild = -1
        
        while sptr < len(s):
            # check if the character match or the wild card character is "?".
            if pptr < len(p) and (p[pptr]=="?" or p[pptr]==s[sptr]):
                sptr += 1
                pptr += 1
            elif pptr < len(p) and p[pptr]=="*":  # check if the wild card character is an "*"
                pwild = pptr
                swild = sptr
                pptr += 1                         # considering only the zero case
            else:                            
                # if there was no wild card character "*", return False
                if pwild == -1: return False 
                # case 1 considering the character is matched with the "*"
                pptr = pwild + 1
                sptr = swild + 1      # begin from the position of the string matched with the wild card character "*".
                swild += 1            # increment the position of the wild card ptr.
        

        # check if the remaining characters are "*"
        for j in range(pptr, len(p)):
            if p[j]!="*":
                return False
        return sptr == len(s) 

        