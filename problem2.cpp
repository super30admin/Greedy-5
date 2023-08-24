#include<vector>
#include<unordered_map>
#include<iostream>
#include<math.h>

using namespace std;

class Solution{
    int manhattan_distance(vector<int> c1,vector<int> c2){
        return abs(c1.at(0) - c2.at(0))+abs(c1.at(1) - c2.at(1));
    }

    public:
    void display(vector<int> res){
        for(int i:res){
            cout<<i<<" ";
        }
        cout<<endl;
    }
    
    void disp_vec(vector<vector<int>>& res){
        for(vector<int> vec:res){
            cout<<"(";
            for(int i:vec){
                cout<<i<<",";
            }
            cout<<")";
        }
        cout<<endl;
    }

    void display(unordered_map<int,vector<vector<int>>>& umap){
        unordered_map<int,vector<vector<int>>> :: iterator itr;
        for(itr = umap.begin();itr!=umap.end();itr++){
            cout<<itr->first<<" ";
            disp_vec(itr->second);
        }
    }

    vector<int> assign_bike(vector<vector<int>> workers,vector<vector<int>> bikes){
        unordered_map<int,vector<vector<int>>> umap{};
        int worker_len = workers.size();
        int bikes_len = bikes.size();
        int min_dist = INT_MAX;
        int max_dist = INT_MIN;
        for(int i{};i<worker_len;++i){
            for(int j{};j<bikes_len;++j){
                int dist = manhattan_distance(workers.at(i),bikes.at(j));
                if(umap.find(dist) == umap.end()){
                    umap[dist] = vector<vector<int>> {};
                }
                umap[dist].push_back(vector<int>{i,j});
                min_dist = min(min_dist,dist);
                max_dist = max(max_dist,dist);
            }
        }
        display(umap);
        vector<bool> bike_flag(bikes_len,false);
        vector<bool> worker_flag(worker_len,false);
        vector<int> res(worker_len,-1);
        for(int i{min_dist};i<=max_dist;++i){
            if(umap.find(i) !=umap.end()){
                for(vector<int> gg:umap[i]){
                    int work_num = gg.at(0);
                    int  bike_num = gg.at(1);
                    if(!worker_flag.at(work_num) && !bike_flag.at(bike_num)){
                        res.at(work_num) = bike_num;
                        bike_flag.at(bike_num) = true;
                        worker_flag.at(work_num) = true;
                    }
                }
            }
        }
        return res;
    }

};

int main(){
    vector<vector<int>> workers{
        {0,0},
        {1,1},
        {2,0}
    };
    vector<vector<int>> bikes{
        {1,0},
        {2,2},
        {2,1}
    };
    Solution sol;
    vector<int> res = sol.assign_bike(workers,bikes);
    sol.display(res);
    return 0;
}