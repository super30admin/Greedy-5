//Time:O(m*n) where m and n are the length of workers and bikes vector
//Space:O(m+n) 
class Solution {
public:
    int getDistance(vector<int> workerPos,vector<int> bikePos){
        return abs(workerPos[0] - bikePos[0]) + abs(workerPos[1] - bikePos[1]);
    }
    vector<int> assignBikes(vector<vector<int>>& workers, vector<vector<int>>& bikes) {
        vector<vector<pair<int,int>>> treeMap(2000);
        vector<bool> assigned(workers.size(),false);
        vector<bool> occupied(bikes.size(),false);
        vector<int> result(workers.size());
        for(int i = 0; i < workers.size();i++){
            for(int j = 0; j < bikes.size(); j++){
                int dist = getDistance(workers[i],bikes[j]);
                treeMap[dist].push_back({i,j});
                
            }
        }
        int count = 0;
        for(int i = 0; i < treeMap.size(); i++){
            if(treeMap[i].size() == 0) continue;
            for(auto val : treeMap[i]){
                if(!assigned[val.first] and !occupied[val.second]){
                    result[val.first] = val.second;
                    assigned[val.first] =true;
                    occupied[val.second] = true;
                    count++;
                    if(count == workers.size()) break;
                }
            }
            if(count == workers.size()) break;
        }
        return result;
    }
};