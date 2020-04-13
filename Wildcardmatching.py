# T:O(m+n) where m is len of s and n is len of p
#Space: O(1)
# Works on leet code

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        
        if s == p or p== '*': # checking if the pattern string has only star or both string are same
            return True
        if len(s) == 0 or len(p) == 0: # if either of string are empty return False
            return False

      # 2 pointer apporach  
        SP = 0 
        PP = 0
        SStar =-1
        PStar = -1
        
        while(SP<len(s)):
            if(PP < len(p) and (s[SP] == p[PP] or p[PP] == '?')): # if there is a character match then we move pointers forward
                SP+=1
                PP+=1
            elif PP < len(p) and p[PP] == '*': # consiedering that the * takes only empty character
                SStar = SP
                PStar = PP
                PP+=1
            elif PStar == -1: # if there is no star in the pattern straing and the string is smaller than source string
                return False
            
            else: # considering the case where * takes any number of characters
                SP= SStar + 1
                PP = PStar +1
                SStar = SP
                
        while(PP < len(p)):
            if p[PP] != '*':
                return False
            PP+=1
        
        return True