// Approach Using an ordered map
// Time Complexity - O(m*n*log(m*n))
// Space Complexity - O(m*n)
// It gives a TLE on Leetcode!
// Problems Faced - Gives a TLE on Leetcode!
class Solution {
public:
    vector<int> assignBikes(vector<vector<int>>& workers, vector<vector<int>>& bikes) {
        map<int, vector<vector<int>>> m;
        for(int i = 0; i < bikes.size(); i++){
            for(int j = 0; j < workers.size(); j++){
                int dist = abs(workers[j][0] - bikes[i][0]) + abs(workers[j][1] - bikes[i][1]);
                m[dist].push_back({j, i});
            }
        }

        vector<int> bikeA(bikes.size());
        vector<int> workerA(workers.size());
        vector<int> assigned(workers.size());
        int count = 0;
        
        for(auto itr : m){
            vector<vector<int>> curr = itr.second;
            for(int i = 0; i < curr.size(); i++){
                if(bikeA[curr[i][1]] == 0 && workerA[curr[i][0]] == 0){
                    assigned[curr[i][0]] = curr[i][1];
                    bikeA[curr[i][1]] = 1;
                    workerA[curr[i][0]] = 1;
                    count++;
                }
                if(count == workers.size())
                    return assigned;
            }
        }
        
        return assigned;
    }
};

// Approach - Bucket Sort
// Time Complexity - O(m*n)
// Space Complexity - O(m*n)
// It runs on Leetcode!
// Problems Faced - No!
class Solution {
public:
    vector<int> assignBikes(vector<vector<int>>& workers, vector<vector<int>>& bikes) {
        unordered_map<int, vector<vector<int>>> m;
        int mini = INT_MAX;
        int maxi = 0;
        for(int i = 0; i < bikes.size(); i++){
            for(int j = 0; j < workers.size(); j++){
                int dist = abs(workers[j][0] - bikes[i][0]) + abs(workers[j][1] - bikes[i][1]);
                m[dist].push_back({j, i});
                mini = min(mini, dist);
                maxi = max(maxi, dist);
            }
        }

        vector<int> bikeA(bikes.size());
        vector<int> workerA(workers.size());
        vector<int> assigned(workers.size());
        int count = 0;
        
        for(int d = mini; d <= maxi; d++){
            vector<vector<int>> curr = m[d];
            if(curr.size() == 0)
                continue;
            for(int i = 0; i < curr.size(); i++){
                if(bikeA[curr[i][1]] == 0 && workerA[curr[i][0]] == 0){
                    assigned[curr[i][0]] = curr[i][1];
                    bikeA[curr[i][1]] = 1;
                    workerA[curr[i][0]] = 1;
                    count++;
                }
                if(count == workers.size())
                    return assigned;
            }
        }
        
        return assigned;
    }
};