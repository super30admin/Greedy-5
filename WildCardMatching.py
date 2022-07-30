#Time complexity: O(len(s))
#Space complexity: O(1)
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        if s==p or p=="*":
            return True
        sp,pp=0,0
        s_str,p_str=-1,-1
        while sp<len(s):
            if pp<len(p) and (s[sp]==p[pp] or p[pp]=="?"):
                sp+=1
                pp+=1
            elif pp<len(p) and p[pp]=='*':
                p_str=pp
                s_str=sp
                pp+=1
            elif p_str==-1:
                return False
            else:
                pp=p_str+1
                sp=s_str+1
                s_str=sp
        #print(sp,pp)
        for i in range(pp,len(p)):
            if p[i]!='*':
                return False
        return True
        
        
        