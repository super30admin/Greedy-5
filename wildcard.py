class Solution:
    #TC-O(SlogP)
    # using two pointers, if chars match increase both by one, if star then dont consider (zero case), if chars are not same , and no star found before, return false, if star s found before, move to one char ahead of star , thus skipping one char
    def isMatch(self, s: str, p: str) -> bool:
        if s==p or p == '*':
            return True
        sl=len(s)
        pl=len(p)
        sp=0
        pp=0
        sstar=-1
        pstar=-1
        while(sp<sl):
            # both match  or char is ?]
            
            if pp<pl and (s[sp]==p[pp] or p[pp]=='?'):
                sp+=1
                pp+=1
            # zero case
            elif pp<pl and p[pp]=='*':
                pstar=pp
                sstar=sp
                pp+=1
            elif pstar==-1:
                return False
            else:
                pp=pstar+1
                sp=sstar+1
                sstar=sp
        while(pp<pl):
            if p[pp]!='*':
                return False
            pp+=1
        return True


        