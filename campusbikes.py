#Time - O(M*N + S*T), M - len(workers), N - len(bikes), S - len(Hashmap), T - len(longest list)
#space - O(S*T)
#Not available on leetcode

class Solution:
    def assignBikes(self,workers, bikes):
        if workers == None or len(workers) == 0 or bikes == None or len(bikes) == 0: # edge cases
            return []
        
        hashmap = {}

        work = len(workers)
        bike = len(bikes)

        result = [-1 for i in range(work)]
        
        for i in range(len(workers)):
            for j in range(len(bikes)):
                #finding the distance between each worker and all bikes
                dist = abs((workers[i][0]-bikes[j][0]) + (workers[i][1] - bikes[j][1]))
                # creating a hash map of distance and corresponding worker and bike
                if dist not in hashmap.keys():
                    hashmap[dist] = [(i,j)]
                else:
                    val = hashmap.get(dist)
                    val.append((i,j))
        visworkers = [False for i in range(work)]
        visbikes = [False for i in range(bike)]

        for key, value in hashmap.items():
            if value == None:
                    continue
            for wb in range(len(value)):
                worker = value[wb][0]
                biker = value[wb][1]
            
                # assigning biker to a worker
                if visworkers[worker] == False and visbikes[biker] == False:
                    visworkers[worker] =True
                    visbikes[biker] =True
                    result[worker] = biker
                    # print(result)
        return result



sol = Solution()
w = [[0,0],[1,1],[2,0]]
b = [[1,0],[2,2],[2,1]]
print(sol.assignBikes(w,b))