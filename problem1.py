#Time O(n+m), space O(1)

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        sl=len(s)
        pl=len(p)
        
        si=0
        pi=0
        ps=-1
        ss=-1
        while si<sl:
            #If both are equal incrementing index
            if pi<pl and(s[si]==p[pi] or p[pi]=="?"):
                si+=1
                pi+=1
            #If * considering it as empty and introducing other two pointers
            elif pi<pl and p[pi]=="*":
                ss=si
                ps=pi
                pi+=1
           #if unequal     
            elif ss==-1:
                    return False
            #coming back to last star index and incrementing s pointer to check 
            else:
                si=ss+1
                pi=ps+1
                ss+=1
        #If still p is left traversing it        
        while pi<pl:
            if p[pi]!="*":
                return False
            pi+=1
            
        return True
               
                
            
        
