//Time - O(mn)
//Space - O(mn)
class Solution {
 public:
    vector<int> assignBikes(vector<vector<int>> workers, vector<vector<int>> bikes){
        
        unordered_map<int,vector<vector<int>>> mp;
        int w = workers.size(), b = bikes.size();
        int minn = INT_MAX, maxx = INT_MIN;
        for(int i = 0;i<w;i++){
            for(int j = 0;j<b;j++){
                int dist = findDistance(workers[i],bikes[j]);
                minn = min(minn,dist);
                maxx = max(maxx,dist);
                mp[dist].push_back({i,j});
            }
        }

        vector<int> result (w,-1);
        vector<bool> bikeAssigned (b,false);
        
        for(int i = minn;i<=maxx;i++){
            if(mp.find(i)!=mp.end()){
                for(int j = 0;j<mp[i].size();j++){
                    vector<int> ele = mp[i][j];
                    if(result[ele[0]]==-1 && bikeAssigned[ele[1]]==false){
                        result[ele[0]] = ele[1];
                        bikeAssigned[ele[1]] = true;
                    }
                }
            }
        }
        
        return result;
        
        
    }
    
    int findDistance(vector<int> worker, vector<int> bike){
        return abs(worker[0]-bike[0])+abs(worker[1]-bike[1]);
    }
    
};