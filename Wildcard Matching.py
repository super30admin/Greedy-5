class Solution:
    #Solution 1
    def isMatch(self, s: str, p: str) -> bool:
        #Approach: Greedy
        #Time Complexity: O(m log n)
        #Space Complexity: O(1)
        #where, m and n are the lengths of strings s and p, respectively
        
        sPointer = pPointer = 0
        sStar = pStar = -1
        
        while sPointer < len(s):
            if pPointer < len(p) and (p[pPointer] == s[sPointer] or p[pPointer] == '?'):
                sPointer += 1
                pPointer += 1
                
            elif pPointer < len(p) and p[pPointer] == '*':
                # zero case
                sStar, pStar = sPointer, pPointer
                pPointer += 1
                
            elif pStar == -1:
                return False
            
            else:   #char mismatch but a '*' has happened in the pattern before
                # one case
                sStar += 1   # matching '*' in p with one 'more' char is s
                sPointer, pPointer = sStar, pStar + 1
                
        while pPointer < len(p):
            if p[pPointer] != '*':
                return False
            pPointer += 1
        
        return True
    
    #Solution 2
    """
    def isMatch(self, s: str, p: str) -> bool:
        #Approach: Dynamic Programming
        #Time Complexity: O(m * n)
        #Space Complexity: O(m * n)
        #where, m and n are the lengths of strings s and p, respectively
        
        dp = [[False for j in range(len(s) + 1)] for i in range(len(p) + 1)]
        dp[0][0] = True
        
        for i in range(1, len(dp)):
            for j in range(len(dp[0])):
                if j == 0:
                    if p[i - 1] == '*':
                        dp[i][j] = dp[i - 1][j]
                else:
                    if p[i - 1] == '*':
                        dp[i][j] = dp[i - 1][j] or dp[i][j - 1]
                    elif p[i - 1] == s[j - 1] or p[i - 1] == '?':
                        dp[i][j] = dp[i - 1][j - 1]
                        
        return dp[-1][-1]
    """