/* 
    Time Complexity                              :  dynamicProgramming - O(m*n)
                                                    twoPointers - O(max(m,n))
    Space Complexity                             :  O(1)
    Did this code successfully run on Leetcode   :  Yes
    Any problem you faced while coding this      :  No
*/

#include <bits/stdc++.h> 
using namespace std;  

class Solution {
public:
    bool isMatch(string s, string p) {
        // return dynamicProgramming(s, p);
        return twoPointers(s,p);
    }
    
    
    bool dynamicProgramming(string s, string p) {
        int n = s.size();
        int m = p.size();
        vector<vector<bool>> dp(m+1,vector<bool>(n+1,false));
        dp[0][0] = true;
        
        // pattern
        for(int i=1;i<=m;i++) {
            //string
            for(int j=0;j<=n;j++) {
                if(j == 0) {
                    dp[i][j] = p[i-1] == '*' ? dp[i-1][j] : false;
                } else {
                    if(s[j-1] == p[i-1] or p[i-1] == '?') {
                        dp[i][j] = dp[i-1][j-1];
                    } else if(p[i-1] == '*') {
                        dp[i][j] = (dp[i-1][j] or dp[i][j-1]);
                    } else if(s[j-1] != p[i-1]) {
                        dp[i][j] = false;
                    }  
                }
                
            }
        }
        
        return dp[m][n];
    }
    
    
    bool twoPointers(string s, string p) {
        int ps=-1, ss=0, pi=0, si=0, m=s.size(), n=p.size();
        
        while(si < m) {
            if(pi < n and s[si] == p[pi] or p[pi] == '?') {
                si++;
                pi++;
            } else if(pi < n and p[pi] == '*') {
                ps = pi;
                ss = si;
                pi++;
            } else if(ps == -1) {
                return false;
            } else {
                pi = ps;
                ss = ss + 1;
                si = ss;
            }
        }
        
        while(pi < n) {
            if(p[pi] != '*') return false;
            pi++;
        }
        
        return true;
    }
    
};