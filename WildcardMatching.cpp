// Approach - DP
// Time Complexity - O(m*n)
// Space Complexity - O(m*n), can be optimised to O(length of 's').
// Problems Faced - No!
// It runs on Leetcode!
class Solution {
public:
    bool isMatch(string s, string p) {
        int n = s.length();
        int m = p.length();
        if(p == "*")
            return true;
        
        vector<vector<bool>> dp(m+1, vector<bool>(n+1));
        
        dp[0][0] = true;
        
        for(int i = 1; i <= m; i++){
            if(p[i-1] == '*')
                dp[i][0] = dp[i-1][0];
        }
        
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(p[i-1] == '*')
                    dp[i][j] = dp[i][j-1] || dp[i-1][j];
                else if(p[i-1] == '?' || p[i-1] == s[j-1])
                    dp[i][j] = dp[i-1][j-1];
            }
        }
        return dp[m][n];
    }
};


// Approach - 2-pointer solution
// Time Complexity - O(sl*log(pl)) - average case scenario.
// Space Complexity - O(1)
// Problems Faced - No!
// It runs on Leetcode!

class Solution {
public:
    bool isMatch(string s, string p) {
        int sL = s.length();
        int pL = p.length();
        int sStar = -1;
        int pStar = -1;
        
        int sP = 0;
        int pP = 0;
        
        while(sP != sL){
            if(s[sP] == p[pP] || p[pP] == '?'){
                sP++;
                pP++;
            }
            else if(p[pP] == '*'){
                pStar = pP;
                sStar = sP;
                pP++;
            }
            else if(pStar == -1)
                return false;
            else{
                pP = pStar + 1;
                sP = sStar + 1;
                sStar = sP;
            }
        }
        
        while(pP != pL){
            if(p[pP] != '*')
                return false;
            pP++;
        }
        
        return true;
    }
};