# Time: O(mn)
# Space: O(mn)
# Did it run on Leetcode: yes

def solution(self,bikes,workers):
    if(len(workers)==0 or len(bikes)==0):
        return []
    m=len(workers)
    n=len(bikes)
    hmap={}
    minval=float('inf')
    maxval=float('-inf')
    for i in range(m):
        w=workers[i]
        for j in range(n):
            bike=bikes[j]
            dist=calculatedist(w,b)
            minval=min(minval,dist)
            maxval=max(maxval,dist)
            if(dist not in hmap):
                hmap[dist]=[]
            hmap[dist].append([i,j])
    assigned=[False]*m
    occupied=[False]*n
    res=[0]*m
    count=0
    for dist in range(minval,maxval+1):
        li=hmap[dist]
        if(li!=None):
            for wb in li:
                w=wb[0]
                b=wb[1]
                if(occupied[w]==False and occupied[b]==False):
                occupied[w] = True
                occupied[b]=True
                    res[w]=b
                    count+=1
                if(count==m):
                    return res
    return res