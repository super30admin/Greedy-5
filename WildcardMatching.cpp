// Time Complexity - O(m*n)
// Space Complexity - O(m*n)
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