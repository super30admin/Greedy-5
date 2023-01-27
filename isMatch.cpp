// Approach 1: DP

// Time Complexity : O(m*n)
// Space Complexity : O(m*n)

/*
if the pattern in * -> get the result from top or left 
(that is. 0 or 1 case) 

else: 

if the chars match or the pattern in '?' : get the diagonal cause the letters cancel out 
*/

class Solution {
public:
    bool isMatch(string s, string p) {
        if(s == p)
            return true;

        int m = p.length();
        int n = s.length();

        vector<vector<bool>> dp (m+1, vector<bool>(n+1, false));
        dp[0][0] = true;

        for(int i = 1; i<m+1; i++) {
            for(int j = 0; j<n+1; j++) {
                if(p[i-1] == '*') {
                    if(j>0)
                        dp[i][j] = dp[i-1][j] || dp[i][j-1];
                    else
                        dp[i][j] = dp[i-1][j];
                }
                else {
                    if(j > 0 && (p[i-1] == '?' || p[i-1] == s[j-1]))
                            dp[i][j] = dp[i-1][j-1];
                }
            }
        }
        return dp[m][n];
    }
};