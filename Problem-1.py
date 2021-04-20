#Time Complexity :o(n) 
#Space Complexity :o(1)
#Did this code successfully run on Leetcode :yes
#Any problem you faced while coding this :no

class Solution(object):
    def isMatch(self, s, p):
        """
        :type s: str
        :type p: str
        :rtype: bool
        """
        if(s==p or p=='*'):
            return True
        pstart=-1
        sstart=-1
        pp=0
        sp=0
        
        while sp<len(s):
            #case1
            if( pp<len(p) and (s[sp]==p[pp] or p[pp]=='?')):
                sp+=1
                pp+=1
                print(pp)
            #case2
            elif( pp<len(p) and( p[pp]=='*')):
                pstart=pp
                sstart=sp
                pp+=1
            #case3
            elif(pstart==-1):
                return False
            #case4
            else:
                sstart+=1
                sp=sstart
                pp=pstart+1 
        
        while pp<len(p):
            if(p[pp]!='*'):
                return False
            pp+=1
        return True
        
        
            