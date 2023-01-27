// Approach 1: Treemap / ordered_map

// Time Complexity : O(mn log mn) log mn to insert to the map
// Space Complexity : O(m*n)

class Solution {
public:
    vector<int> assignBikes(vector<vector<int>>& workers, vector<vector<int>>& bikes) {
        if(workers.size() == 0 || bikes.size() == 0)
            return vector<int>(); 
        
        map <int, vector<vector<int>> > distanceMap;
        vector<int> result(workers.size(), -1);
        vector<int> bikeStatus(bikes.size(), -1);
        
        for(int w = 0; w<workers.size(); w++) {
            for (int b = 0; b<bikes.size(); b++) {
                int dist = abs(bikes[b][0]-workers[w][0]) + abs(bikes[b][1]-workers[w][1]);
                if(distanceMap.find(dist) == distanceMap.end())
                    distanceMap[dist] = vector<vector<int>> ();
                distanceMap[dist].push_back({w,b});
            }
        }
        
        for(auto dist:distanceMap) {
            for(vector<int> pairs:dist.second) {
                if(result[pairs[0]] == -1 && bikeStatus[pairs[1]] == -1) {
                    result[pairs[0]] = pairs[1];
                    bikeStatus[pairs[1]] = 0;
                }
            }
        }
        
        return result;
    }
};

// Approach 2: Mantian min and max manhattan distance and iterate between them

// Time Complexity : O(m*n)
// Space Complexity : O(m*n)

class Solution {
public:
    vector<int> assignBikes(vector<vector<int>>& workers, vector<vector<int>>& bikes) {
        if(workers.size() == 0 || bikes.size() == 0)
            return vector<int>(); 
        
        unordered_map <int, vector<vector<int>> > distanceMap;
        vector<int> result(workers.size(), -1);
        vector<int> bikeStatus(bikes.size(), -1);
        int minDist = INT_MAX;
        int maxDist = INT_MIN;
        
        for(int w = 0; w<workers.size(); w++) {
            for (int b = 0; b<bikes.size(); b++) {
                int dist = abs(bikes[b][0]-workers[w][0]) + abs(bikes[b][1]-workers[w][1]);
                if(distanceMap.find(dist) == distanceMap.end())
                    distanceMap[dist] = vector<vector<int>> ();
                distanceMap[dist].push_back({w,b});
                minDist = min(minDist, dist);
                maxDist = max(maxDist, dist);
            }
        }
        int noWorkers = workers.size();
        for(int i = minDist; i<= maxDist; i++) {
            if(distanceMap.find(i) != distanceMap.end()) {
                for(vector<int> index: distanceMap[i])
                    if(result[index[0]] == -1 && bikeStatus[index[1]] == -1) {
                        result[index[0]] = index[1];
                        bikeStatus[index[1]] = 1;
                        noWorkers--;
                        if(noWorkers==0)
                            return result;
                    }
            }
        }
        
        return result;
    }
};