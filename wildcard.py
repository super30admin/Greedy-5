import copy
class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        if p=="":
            if s=="":
                return True
            else:
                return False
        if s=="":
            if p=="" or p=="*"*len(p):
                return True
            else:
                return False
        
        
            

        
        i=0
        l=[]
        prev=[]
        while(i<len(p)):
            #print(p[i])
            if i==0:
                if p[0]=="*":
                    while(i<len(p) and p[i]=="*"):
                        i+=1
                    if i>=len(p):
                        return True
                    if p[i]=="?":
                        for j in range(len(s)):
                            l.append(j)
                    else:
                        for j in range(len(s)):
                            if p[i]==s[j]:
                                l.append(j)
                elif p[0]=="?" or p[0]==s[0]:
                    l.append(0)
            else:
                if p[i]=="*":
                    while(i<len(p) and p[i]=="*"):
                        i+=1
                    if i>=len(p):
                        return True
                    if p[i]=="?":
                        for j in range(prev[0]+1,len(s)):
                            l.append(j)
                    else:
                        for j in range(len(s)):
                            if p[i]==s[j] and j>prev[0]:
                                l.append(j)
                elif p[i]=="?":
                    for j in range(len(prev)):
                        if (prev[j]+1)<len(s):
                            l.append(prev[j]+1)
                else:
                    for j in range(len(prev)):
                        if prev[j]+1<len(s) and s[prev[j]+1]==p[i]:
                            l.append(prev[j]+1)
            #print(prev)
            #print(l)
            if l==[]:
                return False
           
            prev=copy.copy(l)
            l=[]
            i+=1
       
        if len(s)-1 in prev:
            return True
        else:
            return False


    
         
        