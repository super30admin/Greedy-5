// Time Complexity : O(mn)
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p) || p.equals("*")) return true;
        int m = p.length();
        int n = s.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for(int i = 1; i < dp.length; i++) {
            for(int j = 0; j < dp[0].length; j++) {
                char c = p.charAt(i - 1);
                if(c != '*') {
                    if(j > 0 && (c == s.charAt(j - 1) || c == '?')) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else {
                    // zero case
                    if(j > 0)
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                    else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }
        return dp[m][n];
    }
}