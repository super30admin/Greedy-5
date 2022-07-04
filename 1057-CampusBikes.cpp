/* 
    Time Complexity                              :  usingMap - O(m*n log m*n)
                                                    usingNodeStruct - O(m*n log m*n) where m is the size of the 
                                                    workers array and n is the size of the bieks array
    Space Complexity                             :  O(1)
    Did this code successfully run on Leetcode   :  Yes
    Any problem you faced while coding this      :  No
*/

#include <bits/stdc++.h> 
using namespace std;  


class Solution {
private:
    int bsz, wsz;
public:
    struct node {
        int distance, worker, bike;
    };
    
    vector<int> assignBikes(vector<vector<int>>& workers, vector<vector<int>>& bikes) {
        bsz=bikes.size();
        wsz=workers.size();
        // return usingNodeStruct(workers,bikes);
        return usingMap(workers,bikes);
    }
    
    // time limit exceeded
     vector<int> usingMap(vector<vector<int>>& workers, vector<vector<int>>& bikes) {
        map<int,vector<vector<int>>> mp;
        
        // ~= O(m*n log m*n) where m is the size of the workers array and n is the size of the bikes array
        for(int i=0;i<wsz;i++) {
            auto worker = workers[i];
            for(int j=0;j<bsz;j++) {
                auto bike = bikes[j];
                int distance = abs(bike[0] - worker[0]) + abs(bike[1] - worker[1]);       
                if(mp.find(distance) == mp.end()) {
                    mp[distance] = vector<vector<int>>();
                }
                mp[distance].push_back(vector<int>{i,j});
            }
        }
        
        vector<int> ans(wsz,-1);
        vector<bool> allocatedBikes(bsz,false);
        int count = 0;
        // O(m*n)
        for(auto m : mp) {
            for(auto workerBike : m.second) {
                int worker = workerBike[0];
                int bike = workerBike[1];
                if(ans[worker] == -1 and allocatedBikes[bike] == false) {
                    ans[worker] = bike;
                    allocatedBikes[bike] = true;
                    count++;
                    if(count == wsz) return ans;
                }   
            }
        }
         return ans;
     }
    
    vector<int> usingNodeStruct(vector<vector<int>>& workers, vector<vector<int>>& bikes) {
        vector<node> nodes;
        // O(m*n) where m is the size of the workers array and n is the size of the bikes array
        for(int i=0;i<wsz;i++) {
            auto worker = workers[i];
            for(int j=0;j<bsz;j++) {
                auto bike = bikes[j];
                int distance = abs(bike[0] - worker[0]) + abs(bike[1] - worker[1]); 
                nodes.push_back({distance, i, j});
            }
        }
        auto cmp = [](node& a, node& b) { 
            if(a.distance != b.distance) return a.distance < b.distance;
            else if (a.worker != b.worker) return a.worker < b.worker;
            else return a.bike < b.bike;
        };
        // O(m*n log m*n) - total number of nodes created above = m*n;
        sort(begin(nodes),end(nodes),cmp);
        vector<int> result(wsz,-1);
        // unordered_set<int> aBikes;
        vector<bool> aBikes(bsz,false);
        
        // O(m*n) = size of the nodes array
        for(auto node : nodes) {
            // if(result[node.worker] == -1 and aBikes.find(node.bike) == aBikes.end()) {
            if(result[node.worker] == -1 and aBikes[node.bike] == false) {
                result[node.worker] = node.bike;
                // aBikes.insert(node.bike);
                aBikes[node.bike] = true;
            }
        }
        return result;
    }
    
};