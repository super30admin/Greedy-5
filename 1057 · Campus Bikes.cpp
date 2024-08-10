
class Solution {
public:
    /**
     * @param workers: workers' location
     * @param bikes: bikes' location
     * @return: assign bikes
     */

    vector<int> assignBikes(vector<vector<int>> &w, vector<vector<int>> &b) {
        // write your code here
        vector<vector<int>> wb[2005]; //bucket size - highest possible distance

        for(int i=0; i<w.size(); i++){
            for(int j=0; j<b.size(); j++){
                int d = abs(w[i][0]-b[j][0]) + abs(w[i][1]-b[j][1]);
                wb[d].push_back({i, j});
            }
        }
        vector<int> ret(w.size(), -1);
        vector<int> taken(b.size(), -1);

        //we now do a bucket sort
        for(int i=0;i<2005;i++){
            for(auto &v: wb[i]){
                int idx = v[0], bike = v[1];
                //keep track of which worker has been assigned and which bike has been used
                if(ret[idx] == -1 && taken[bike] == -1)  ret[idx] = bike, taken[bike] = 1;
            }    
        }

        return ret;

    }
};