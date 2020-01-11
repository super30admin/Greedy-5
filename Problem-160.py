'''
Leetcode- 44. Wildcard Matching 
time complexity - O(N)
space complexity - O(1)

Approach - two pointers approach 
          1) when two chars are equal we move sp and pp pointer 
          2) when there is "*", we consider or we don't consider it
             a) when we won't consider "*" we keep track of str_index and s_str_index so that we can trace back while considering
                at this time we increment pp pointer
             b) when we consider "*" we update str_index, s_str_index accordingly
             
          3) In the end if ran out of S and when we have p then we take care of it 
'''
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        SP=0
        PP=0
        
        str_idx=-1
        s_str_idx=-1
        
        while SP<len(s):
            if PP<len(p) and  (s[SP]==p[PP] or p[PP]=="?"):
                SP+=1
                PP+=1
            elif PP<len(p) and p[PP]=="*":
                str_idx=PP
                s_str_idx=SP
                PP+=1
            elif str_idx==-1:
                return False
            else:
                PP=str_idx+1
                SP=s_str_idx+1
                s_str_idx=SP
        
        for i in range(PP,len(p)):
            if p[i]!="*":
                return False
        return True