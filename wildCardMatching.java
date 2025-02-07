// Approach : DP (When you try to be exhaustive and create decision tree. You can see the repeated sub problems)
// Time :  O(m*n)
// Space : O(m*n)
class Solution {
    public boolean isMatch(String s, String p) {
        // Edge cases
        // if (s.equals(p) || p.equals('*'))
        //     return true;
        // if (s.isEmpty() || p.isEmpty())
        //     return false;
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true; // top left corner

        // Go Over the first row
        for (int j = 1; j <= n; j++) {
            char pChar = p.charAt(j - 1);
            if (pChar == '*') {
                // No choose case
                dp[0][j] = dp[0][j - 1]; // one step back
            }
        }

        // Go Over the DP
        for(int i =1;i<=m;i++){
            for (int j=1;j<=n;j++){
                char pChar = p.charAt(j-1);
                if(pChar =='*'){
                    // choose case
                    dp[i][j] = dp[i][j-1] || dp[i-1][j]; // left and straight above
                } else{
                    // incoming charaters match
                    if(pChar == s.charAt(i-1) || pChar == '?'){
                        dp[i][j] = dp[i-1][j-1]; // diagonal up
                    } else {
                        dp[i][j] = false; // characters not matching
                    }
                }
            }
        }
       return dp[m][n];
    }
}