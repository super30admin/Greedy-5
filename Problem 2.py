# Time: O(m*n*log(m*n))
# Space: O(m*n)
class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        ord_dic = {}
        mn = float('inf')
        mx = float('-inf') 
        vals = set()
        for i in range(len(workers)):
            for j in range(len(bikes)):
                dis = abs(workers[i][0]-bikes[j][0])+abs(workers[i][1]-bikes[j][1])
                mn = min(mn, dis)
                mx = max(mx, dis)
                #print(dis)
                vals.add(dis)
                if dis not in ord_dic:
                    ord_dic[dis] = [[i,j]]
                else:
                    ord_dic[dis].append([i,j])
                #break
        vals = list(vals)
        vals.sort()
        taken_bikes = [False]*len(bikes)
        taken_workers = [False]*len(workers)
        ans = [-1]*len(workers)
        for i in vals:
            for pair in ord_dic[i]:
                if taken_workers[pair[0]] == False and taken_bikes[pair[1]] == False:
                    ans[pair[0]] = pair[1]
                    taken_workers[pair[0]] = True
                    taken_bikes[pair[1]] = True
        return ans
        
