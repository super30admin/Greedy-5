#time-mn,space-m*n(hmap)
class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        hmap={}
        mini=inf
        maxi=-inf
        for i in range(len(workers)):
            for j in range(len(bikes)):
                m_d=abs(workers[i][0]-bikes[j][0])+abs(workers[i][1]-bikes[j][1])
                if m_d not in hmap.keys():
                    hmap[m_d]=[]
                hmap[m_d].append([i,j])
                mini=min(mini,m_d)
                maxi=max(maxi,m_d)
        
      
        visited_w=[False for i in range(len(workers))]
        visited_b=[False for i in range(len(bikes))]
        res=[0 for i in range(len(workers))]
        for i in range(mini,maxi+1):
            
            if i in hmap.keys():
                for j in hmap[i]:
             
                    if visited_w[j[0]]==False and visited_b[j[1]]==False:
                       
                        visited_w[j[0]]=True
                        visited_b[j[1]]=True
                        res[j[0]]=j[1]
        return res
                        
        
                    
        
                
        