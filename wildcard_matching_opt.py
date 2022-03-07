#time-min(len(s),len(p))-big proof, space-1
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        sp=0
        pp=0
        sstar=-1
        pstar=-1
        
        while sp<len(s):
            print(sp,pp)
            if pp<len(p) and( s[sp]==p[pp] or p[pp]=='?'):
                sp+=1
                pp+=1
            elif pp<len(p) and p[pp]=='*':
                sstar=sp
                pstar=pp
                pp+=1
            else:
                if pstar==-1:
                    return False
                else:
                    sp=sstar+1
                    pp=pstar+1
                    sstar+=1
        while pp<len(p):
            if p[pp]!='*':
                return False
            pp+=1
        return True
                
                    
                
            