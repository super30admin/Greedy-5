class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        ## T.C = O(m.n + max - min)
        ## S.C = O(m.n)

        from collections import defaultdict

        hm = defaultdict(list)
        w = len(workers)
        b = len(bikes)
        bike_assignment = [False]*b
        res = [-1]*w
        mx = float('-inf')
        mn = float('inf')

        for i in range(w):
            w_x, w_y = workers[i]
            for j in range(b):
                b_x, b_y = bikes[j]
                dist = abs(w_x - b_x) + abs(w_y - b_y)
                hm[dist].append([i, j])
                mn = min(mn, dist)
                mx = max(mx, dist)
                
        cnt = 0
        for x in range(int(mn), int(mx + 1)):
            if not hm[x]:
                continue
            for i, j in hm[x]:
                if res[i] == -1 and bike_assignment[j] == False:
                    res[i] = j
                    bike_assignment[j] = True
                    cnt += 1
                
                    if cnt == w:
                        break
        
        return res
