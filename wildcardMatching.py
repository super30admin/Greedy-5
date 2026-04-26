class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        pStar = sStar = -1
        sl = len(s)
        pl = len(p)

        sp = pp = 0

        while sp < sl:
            if pp<pl and (p[pp]=='?' or p[pp]==s[sp]):
                sp+=1
                pp+=1
            
            elif pp<pl and p[pp]=='*':
                pStar = pp
                sStar = sp
                pp+=1
            
            elif pStar==-1:
                return False
            
            else: # pStar!=-1
                sStar+=1
                sp = sStar
                pp = pStar+1
        
        while pp<pl:
            if p[pp]!='*':
                return False
            pp+=1
        
        return True
