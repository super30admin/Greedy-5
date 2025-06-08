// Problem: Campus Bikes
// Time complexity: O(n * m * log(n * m)), where n is the number of workers and m is the number of bikes.
// Space complexity: O(n * m), as we are storing the distances in a map.
// Did this code run successfully on LeetCode: Yes

// Approach:
// We will calculate the distance between each worker and each bike.
// We will store these distances in a map where the key is the distance and the value is a vector of pairs (worker index, bike index).
// We will then iterate through the map in order of distance.
// For each distance, we will assign the bike to the worker if both are not already assigned.
// If a worker is already assigned to a bike or a bike is already occupied, we will skip that pair.
// Finally, we will return the result vector containing the bike assigned to each worker.

#include <vector>
#include <map>
#include <cmath>
using namespace std;
class Solution {
public:
    vector<int> assignBikes(vector<vector<int>>& workers, vector<vector<int>>& bikes) {
        int n = workers.size(), m = bikes.size();
        vector<int> result(n, -1);
        vector<bool> assigned(n, false);
        vector<bool> occupied(m, false);

        map<int, vector<pair<int, int>>> map;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++) {
                int d = dist(workers[i], bikes[j]);
                map[d].push_back({i, j});
            }
        }
        for(auto& [dist, pairs] : map) {
            for(auto& [w, b] : pairs) {
                if(!assigned[w] && !occupied[b]) {
                    assigned[w] = true;
                    occupied[b] = true;
                    result[w] = b;
                }
            }
        }
        return result;
    }
private: 
    int dist(vector<int>& workers, vector<int>& bikes) {
        return abs(workers[1] - bikes[1]) + abs(workers[0] - bikes[0]);
    }
};