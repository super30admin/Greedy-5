'''
1957 Campus Bikes
https://leetcode.com/problems/campus-bikes/description/

On a campus represented on the X-Y plane, there are n workers and m bikes, with n <= m.

You are given an array workers of length n where workers[i] = [xi, yi] is the position of the ith worker. You are also given an array bikes of length m where bikes[j] = [xj, yj] is the position of the jth bike. All the given positions are unique.

Assign a bike to each worker. Among the available bikes and workers, we choose the (workeri, bikej) pair with the shortest Manhattan distance between each other and assign the bike to that worker.

If there are multiple (workeri, bikej) pairs with the same shortest Manhattan distance, we choose the pair with the smallest worker index. If there are multiple ways to do that, we choose the pair with the smallest bike index. Repeat this process until there are no available workers.

Return an array answer of length n, where answer[i] is the index (0-indexed) of the bike that the ith worker is assigned to.

The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) =
|p1.x - p2.x| + |p1.y - p2.y|.

Example 1:
Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
Output: [1,0]
Explanation: Worker 1 grabs Bike 0 as they are closest (without ties), and Worker 0 is assigned Bike 1. So the output is [1, 0].

Example 2:
Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
Output: [0,2,1]
Explanation: Worker 0 grabs Bike 0 at first. Worker 1 and Worker 2 share the same distance to Bike 2, thus Worker 1 is assigned to Bike 2, and Worker 2 will take Bike 1. So the output is [0,2,1].

Constraints:
n == workers.length
m == bikes.length
1 <= n <= m <= 1000
workers[i].length == bikes[j].length == 2
0 <= xi, yi < 1000
0 <= xj, yj < 1000
All worker and bike locations are unique

Solution:
1. Hashing using Tree Map or Priority Queue
For every worker-bike pair, calculate the Manhattan distance and push it into a Tree Map or a Priority Queue. We implement our solution using Tree Map.
Thus, the Tree Map takes <distance, list> as key, value pairs where the list is a list of sublists containing [worker id i, bike id j]. The outer for loop computing the Manhattan distance runs over the worker ids (smaller to larger). The inner loop runs over the bike ids (smaller to larger). Thus, in TreeMap<distance, list>, the list is automatically arranged by sorted (increasing) order of worked ids. If the workers ids are identical, the the sorting is over bike ids.
Eg. TreeMap - {1: [0,0], [1,0], [1,2], [2,0], [2,2]}

For each distance (key) in the Tree Map, we get a list of worker=bike pairs.
Assign bikes to workers based given that the worker has not been assigned already and the bike is not in use. This ensures each bike and worker is used only once. Once we have allocated all the workers, we exit.

The main problem with this approach is that the insertion of a key/retrieval of key from a TreeMap takes O(log K) time, where K = total # of keys in the TreeMap. For M bikes and N workers, we have MN worker-bike pairs which could potentially lead to a max of MN distances if the distances are unique. Thus, K <= MN. Worst case K = MN. This, results in O(NM log NM) time complexity.

https://youtu.be/32uI-men3Bc?t=457
Time: O(NM log NM), Space: O(NM)

2. Hashing using regular Hash Map
We could avoid the log(NM) time complexity in Tree Map by using a Hash Map.
While computing the Manhattan distances over NM pairs, we store the min and max distances in min_dist and max_dist variables.

Then while assigning workers to bikes, we read the the keys in the Hash Map from min_dist to max_dist and fetch the corresponding worker-bike pairs.
We keep a count of #no. of assignments. If the count == no. of workers, we exit.

https://youtu.be/32uI-men3Bc?t=2590
Time: O(NM), Space: O(NM)
'''

from collections import defaultdict  # Regular dictionary
from sortedcontainers import SortedDict # TreeMap
# Note: TreeMap is a data structure that stores key-value pairs of a dictionary
# in a sorted order based on the keys. If there are K keys, then
# inserting a new key: O(log K) time
# deleting an existing key: O(log K) time
# getting a value of an existing key: O(log K) time
from typing import List

def manhattan(p, q):
        return abs(p[0] - q[0]) + abs(p[1] - q[1])


def assignBikes_TreeMap(workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
    ''' Time: O(NM log NM), Space: O(NM) (max no. of distances between M bikes and N workers is NM since pair can potentially have a unique distance) '''
    M = len(bikes)
    N = len(workers)
    map = SortedDict() # sorts the keys (in ascending) automatically

    # define array which tells which worker got which bike?
    # index = worker id, value = bike id
    workers_assigned = [-1]*N

    # define array which tells if bike id has been allocated to worker
    # or not
    bikes_assigned = [False]*M # bikes assigned or not

    count = 0 # no. of workers paired with bikes
    for i in range(N): # O(N)
        for j in range(M): # O(M)
            dist = manhattan(workers[i], bikes[j])
            if dist not in map: # O(log K), K = #keys
                map[dist] = []
            map[dist].append([i,j]) # O(log K)

    for dist in map: # each key takes O(log K). K keys takes O(K log K)
        wb_pairs = map[dist]
        for wb in wb_pairs:
            worker = wb[0]
            bike = wb[1]
            if workers_assigned[worker] == -1:
                if bikes_assigned[bike] == False:
                    workers_assigned[worker] = bike
                    bikes_assigned[bike] = True
                    count += 1
                    if count == N: return workers_assigned

    return workers_assigned


def assignBikes_HashMap(workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
    ''' Time: O(NM), Space: O(NM) (max no. of distances between M bikes and N workers is NM since pair can potentially have a unique distance) '''
    M = len(bikes)
    N = len(workers)
    map = defaultdict(list) # sorts the keys (in ascending) automatically

    # define array which tells which worker got which bike?
    # index = worker id, value = bike id
    workers_assigned = [-1]*N

    # define array which tells if bike id has been allocated to worker
    # or not
    bikes_assigned = [False]*M # bikes assigned or not

    count = 0 # no. of workers paired with bikes
    min_dist, max_dist = float('inf'), float('-inf')
    for i in range(N):
        for j in range(M):
            dist = manhattan(workers[i], bikes[j])
            map[dist].append([i,j]) #  O(1)
            min_dist = min(min_dist, dist)
            max_dist = max(max_dist, dist)

    for dist in range(min_dist, max_dist+1):
        if dist not in map:
            continue
        wb_pairs = map[dist]
        for wb in wb_pairs:
            worker = wb[0]
            bike = wb[1]
            if workers_assigned[worker] == -1:
                if bikes_assigned[bike] == False:
                    workers_assigned[worker] = bike
                    bikes_assigned[bike] = True
                    count += 1
                    if count == N: return workers_assigned

    return workers_assigned

def run_assignBikes():
    tests = [([[0,0],[2,1]], [[1,2],[3,3]], [1,0]),
             ([[0,0],[1,1],[2,0]], [[1,0],[2,2],[2,1]], [0,2,1]),
    ]
    for test in tests:
        workers, bikes, ans = test[0], test[1], test[2]
        print(f"\nworkers: {workers}")
        print(f"bikes: {bikes}")
        for method in ['TreeMap', 'HashMap']:
            if method == 'TreeMap':
                result = assignBikes_TreeMap(workers, bikes)
            elif method == 'HashMap':
                result = assignBikes_HashMap(workers, bikes)
            print(f"Method {method}: result = {result}")
            success = (ans == result)
            print(f"Pass: {success}")
            if not success:
                print(f"Failed")
                return

run_assignBikes()
