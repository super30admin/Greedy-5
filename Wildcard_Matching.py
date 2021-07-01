class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        
        
        
        sp , pp = 0,0
        sstar , pstar = -1, -1 
        
        m , n = len(s), len(p)
        
        while sp < m:
            if pp < n and  (s[sp] == p[pp] or p[pp] == '?'): 
                sp +=1
                pp +=1
            elif pp < n and p[pp] == '*':
                pstar = pp
                sstar = sp
                pp+=1
            
            elif pstar == -1:
                return False
            else:
                sstar +=1
                sp = sstar
                pp = pstar
        
        while pp < n:
            if p[pp] != '*':
                return False
            pp+=1
        
        return True
                
            
