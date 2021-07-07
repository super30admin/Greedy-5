'''
Leetcode - 1057. Campus Bikes
time complexity - O(M*N)*logN  -------(logN for hash_map)
space complexity - O(M*N)

Approach - 1) First we create the distance for all workers and bikes
           2) And then for each distance we store (worker, bike) pair in hash_map -- [it's a list]
           3) At each distance we check worker and bikes and then assign each worker a bike.
'''
class solution:
    def bikes(self,workers,bikes):
        hash_map={}
        
        for i in range(len(workers)):
            for j in range(len(bikes)):
                dist=abs(workers[i][0]-bikes[j][0])+abs(workers[i][1]-bikes[j][1])
                
                if dist in hash_map:
                    hash_map[dist].append((i,j))
                    
                else:
                    hash_map[dist]=[(i,j)]
        res=[0 for _ in range(len(workers))]
        check_worker=[False for _ in range(len(workers))]
        check_bike=[False for _ in range(len(workers))]
        
        
        for key in sorted(hash_map.keys()):
            for temp in hash_map[key]:
                worker=temp[0]
                bike=temp[1]
                if check_worker[worker]==False and check_bike[bike]==False:
                    check_worker[worker]=True
                    check_bike[bike]=True
                    res[worker]=bike
        return res
    
# output
m = solution()
workers = [[0,0],[1,1],[2,0]]
bikes = [[1,0],[2,2],[2,1]]
print(m.bikes(workers,bikes))
