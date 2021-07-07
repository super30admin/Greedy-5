//Time Complexity-O(m*n)-->'m' is number of workers and 'n' is number of bikes
//Space Complexity-O(m+n)--->vectors used to indicate whether particular worker or bike is assigned
//Did the code execute on Leetcode? Yes

class Solution {
public:
    vector<int> assignBikes(vector<vector<int>>& workers, vector<vector<int>>& bikes) {
        if(workers.size()==0 || bikes.size()==0)
        {
            return {};
        }
        vector<int>res(workers.size(),-1);
        vector<bool>temp(workers.size(),false);
        vector<bool>temp1(bikes.size(),false);
        vector<vector<vector<int>>>m(2000);
        for(int i=0;i<workers.size();i++)
        {
            for(int j=0;j<bikes.size();j++)
            {
                int dist=abs(bikes[j][0]-workers[i][0])+abs(bikes[j][1]-workers[i][1]);
                m[dist].push_back({i,j});
            }
        }
        for(int i=0;i<m.size();i++)
        {
            vector<vector<int>>a=m[i];
            if(a.size()==0)
            {
                continue;
            }
            for(int j=0;j<a.size();j++)
            {
                vector<int>b=a[j];
                if(temp[b[0]]==false && temp1[b[1]]==false)
                {
                    res[b[0]]=b[1];
                    temp[b[0]]=true;
                    temp1[b[1]]=true;
                }
            }
        }
        return res;
    }
};