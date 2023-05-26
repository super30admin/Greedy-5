class Solution:
    # Time O(n*m + k) n is len of workers list and m is len of bikes list and k is the iteration for assigning bikes to workers
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        hmap = collections.defaultdict(list)
        minn = float('inf')
        maxx = 0
        for i in range(len(workers)):
            for j in range(len(bikes)):
                dist = abs(workers[i][0] - bikes[j][0]) + abs(workers[i][1] - bikes[j][1])
                minn = min(minn, dist)
                maxx = max(maxx, dist)
                hmap[dist].append([i,j])
        # print(hmap)
        w_avail = [False for i in range(len(workers))]
        b_avail = [False for i in range(len(bikes))]
        res = [0 for i in range(len(workers))]
        count = 0
        for dis in range(minn, maxx+1):
            for pair in hmap[dis]:
                if not w_avail[pair[0]] and not b_avail[pair[1]]:
                    res[pair[0]] = pair[1]
                    w_avail[pair[0]] = True
                    b_avail[pair[1]] = True
                    count += 1
                if count >= len(workers):
                    return res
        
        return res

