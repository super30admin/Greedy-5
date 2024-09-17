# // Time Complexity :O(m*n),worst case total number of keys 
# // Space Complexity :O(n) 
# // Did this code successfully run on Leetcode :yes
# // Any problem you faced while coding this :no



import math
def calc(workers,bikes):
    hmap={}
    minn=math.inf
    maxx=-math.inf
    for i in range(len(workers)):
        for j in range(len(bikes)):
            dist=manhattan_dist(workers[i][0],workers[i][1],bikes[j][0],bikes[j][1])
            minn=min(minn,dist)
            maxx=max(maxx,dist)
            if dist not in hmap.keys():
                hmap[dist]=[]
            hmap[dist].append([i,j])

    print(hmap)
    print(minn,maxx)
    assigned=[]
    alloted=[]
    res=[0 for i in range(len(workers))]
    for dist in range(minn,maxx+1):
        if dist not in hmap.keys():
            continue
        li=hmap[dist]
        for wb in li:
            print(wb)
            currw=wb[0]
            currb=wb[1]
            if currw not in assigned and currb not in alloted:
                assigned.append(currw)
                alloted.append(currb)
                res[currw]=currb
            print(res)

    return res


def manhattan_dist(x1,y1,x2,y2):
    return abs((x2-x1)+(y2-y1))

workers=[[0,0],[1,1],[2,0]]
bikes=[[1,0],[2,2],[2,1]]
print(calc(workers,bikes))