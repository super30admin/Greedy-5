//Time Complexity-O(m*n*log(x))--->here 'm'  is number of workers,'n' is number of bikes and 'x' is total number of unique 
//                                 distances stored in hashmap(number of keys or map.size())
//Space Complexity-O(x*k)+O(m)+O(n)--> Auxiliary space for storing if the workers and bikes are assigned(O(m)+O(n))
//                                     Addition to this we also used ordered map which is of size x*k.
//                                     Here 'x' is map size and 'k' is average number of pairs per key in the map.
//Did the code execute on Leetcode? Yes but this is not getting accepted in c++ and it's passing 27/28 cases.   
//                                  It's giving TLE for last test case. This is because for large input and various
//                                  distances the time complexity of adding an element in ordered map is log(n) for each element.

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
        map<int,vector<vector<int>>>m;
        for(int i=0;i<workers.size();i++)
        {
            for(int j=0;j<bikes.size();j++)
            {
                int dist=abs(bikes[j][0]-workers[i][0])+abs(bikes[j][1]-workers[i][1]);
                if(m.find(dist)==m.end())
                {
                    m[dist]={};
                }
                m[dist].push_back({i,j});
            }
        }
        for(auto i=m.begin();i!=m.end();i++)
        {
            cout<<i->first;
            cout<<"\n";
            vector<vector<int>>a=i->second;
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