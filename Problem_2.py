# Time Complexity: O(m * n)
# Space Complexity: O(m * n)
# Did this code successfully run on Leetcode: Yes
# Any problem you faced while coding this: No

class Solution(object):
    def assignBikes(self, workers, bikes):
        ans = ([-1] * len(workers))
        usedBikes = ([False] * len(bikes))
        buckets = [[] for _ in range(2001)]

        def dist(p1, p2):
            return ((abs(p1[0] - p2[0])) + (abs(p1[1] - p2[1])))
        for i, worker in enumerate(workers):
            for j, bike in enumerate(bikes):
                buckets[dist(worker, bike)].append((i, j))
        for k in range(2001):
            for i, j in buckets[k]:
                if (ans[i] == -1) and (not usedBikes[j]):
                    ans[i] = j
                    usedBikes[j] = True
        return ans