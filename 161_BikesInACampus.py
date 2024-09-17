'''
Not tested on leetcode.
time - O(M*N + H*L), M - len(workers), N - len(bikes), H - len(Hashmap), L - len(longest list)
space - O(H*L)
'''
class Solution:
    def assignBikes(self,workers, bikes):
        hashmap = {}

        # iterate over workers and bikes and calculate distance.
        # dist being the key of hashmap add workers and bikes to the hashmap as a list values.
        for i in range(len(workers)):
            for j in range(len(bikes)):
                dist = abs(workers[i][0] - bikes[j][0]) + abs(workers[i][1] - bikes[j][1])

                if dist in hashmap:
                    hashmap[dist].append((i,j))
                else:
                    hashmap[dist] = [(i,j)]
        # declaration
        result = [0 for i in range(len(workers))]
        checkWorkers = [False for i in range(len(workers))]
        checkBikes = [False for i in range(len(bikes))]

        # After filling hashmap iterate over it to generate the final result.
        # Create a boolean array workers and bikes and if those already occupied change to false and add to result.
        for key in sorted(hashmap.keys()):
            for itm in hashmap[key]:
                worker = itm[0]
                bike = itm[1]

                if checkWorkers[worker] == False and checkBikes[bike] == False:
                    checkWorkers[worker] = True
                    checkBikes[bike] = True
                    result[worker] = bike

        return result


sol = Solution()
w = [[0,0],[1,1],[2,0]]
b = [[1,0],[2,2],[2,1]]
print(sol.assignBikes(w,b))