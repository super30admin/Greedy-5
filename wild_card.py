
# // Time Complexity :O(min(s,p))-refer pdf
# // Space Complexity :O(1) 
# // Did this code successfully run on Leetcode :yes
# // Any problem you faced while coding this :no

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        plen=len(p)
        slen=len(s)
        pstar=-1
        sstar=-1
        pb=0
        sb=0
        while sb<slen:
            print("x")
            if pb<plen and (s[sb]==p[pb] or p[pb]=='?'):
                sb+=1
                pb+=1
            elif pb<plen and p[pb]=='*':
                pstar=pb
                sstar=sb
                pb+=1
            elif pstar==-1:
                return False
            else:
                pb=pstar+1
                sb=sstar+1
                sstar=sb
                
        while pb<plen:
            if p[pb]!='*':
                return False
            pb+=1
        return True
        