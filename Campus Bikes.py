class Solution:
    #Solution 1
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        #Approach: Greedy // Arranging by distance
        #Time Complexity: O(wb)
        #Space Complexity: O(wb)
        #where, w and b are the number of workers and bikes, respectively
        
        pairs = [None for i in range(2000)]     #all possible distances
        for w in range(len(workers)):
            for b in range(len(bikes)):
                worker = workers[w]
                bike = bikes[b]
                dist = abs(worker[0] - bike[0]) + abs(worker[1] - bike[1])
                if not pairs[dist]:
                    pairs[dist] = []
                pairs[dist].append((w, b))
        
        visitedWorkers = set()
        visitedBikes = set()
        result = [None for _ in workers]
        
        for distPairs in pairs:
            if not distPairs:
                continue
            for pair in distPairs:
                if pair[0] not in visitedWorkers and pair[1] not in visitedBikes:
                    result[pair[0]] = pair[1]
                    visitedWorkers.add(pair[0])
                    visitedBikes.add(pair[1])
                    
                    if len(visitedWorkers) == len(workers):
                        return result
    
    #Solution 2
    """
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        #Approach: Greedy // Sorting by distance
        #Time Complexity: O(wb log wb)
        #Space Complexity: O(wb)
        #where, w and b are the number of workers and bikes, respectively
        
        pairs = []
        for w in range(len(workers)):
            for b in range(len(bikes)):
                worker = workers[w]
                bike = bikes[b]
                dist = abs(worker[0] - bike[0]) + abs(worker[1] - bike[1])
                pairs.append((dist, w, b))
                
        pairs.sort()
        
        visitedWorkers = set()
        visitedBikes = set()
        result = [None for _ in workers]
        
        for pair in pairs:
            if pair[1] not in visitedWorkers and pair[2] not in visitedBikes:
                result[pair[1]] = pair[2]
                visitedWorkers.add(pair[1])
                visitedBikes.add(pair[2])
                
                if len(visitedWorkers) == len(workers):
                    return result
    """
    
    #Solution 3
    """
    def assignBikes(self, workers: List[List[int]], bikes: List[List[int]]) -> List[int]:
        #Approach: Brute Force
        #Time Complexity: O(w^2 * b)
        #Space Complexity: O(w + b)
        #where, w and b are the number of workers and bikes, respectively
        
        visitedWorkers = set()
        visitedBikes = set()
        result = [None for _ in workers]
        
        while len(visitedWorkers) != len(workers):
            minDist = inf
            for w in range(len(workers)):
                if w in visitedWorkers:
                    continue
                for b in range(len(bikes)):
                    if b in visitedBikes:
                        continue
                    worker = workers[w]
                    bike = bikes[b]
                    dist = abs(worker[0] - bike[0]) + abs(worker[1] - bike[1])
                    if dist < minDist:
                        minDist = dist
                        bestPair = (w, b)
            
            result[bestPair[0]] = bestPair[1]
            visitedWorkers.add(bestPair[0])
            visitedBikes.add(bestPair[1])
            
        return result
    """