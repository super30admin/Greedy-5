'''

TC: O(w*b)
SC: O(w*b)

'''
class Solution:
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        
        wlen = len(workers)
        blen = len(bikes)
        if not wlen or not blen:
            return []
        
        hmap = dict()
        maxd = float("-inf")
        mind = float("inf")
        
        for w in range(wlen):
            for b in range(blen):
                dist = abs(bikes[b][0] - workers[w][0]) + abs(bikes[b][1] - workers[w][1])
                hmap[dist] = hmap.get(dist, [])
                hmap[dist].append([w, b])
                maxd = max(maxd, dist)
                mind = min(mind, dist)
        
        assgn_w = [False for w in workers]
        assgn_b = [False for b in bikes]
        res = [0 for w in workers]
        count = 0
        
        for dist in range(mind, maxd + 1):
            if dist not in hmap:
                continue
            for val in hmap[dist]:
                w, b = val
                if not assgn_w[w] and not assgn_b[b]:
                    assgn_w[w] = True
                    assgn_b[b] = True
                    res[w] = b
                    count += 1
                    
                    if count == wlen:
                        return res
        
        return res
                
                