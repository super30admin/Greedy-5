// Time Complexity : O(m*n) where m and n are lengths of strings s and p respectively
// Space Complexity : O(m*n) where m and n are lengths of strings s and p respectively
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
// DP Solution

class wildcardMatching_DP {
    public boolean isMatch(String s, String p) {
        if (s.equals(p) || p.equals("*")) return true;
        if (s.isEmpty() || p.isEmpty()) return false;
        int sLen = s.length(), pLen = p.length();
        boolean[][] dp = new boolean[pLen+1][sLen+1];
        dp[0][0] = true;
        for (int i = 1; i <= pLen; i++) {
            if (p.charAt(i-1) == '*') {
                dp[i][0] = dp[i-1][0];    
            }
        }
        for (int i = 1; i <= pLen; i++) {
            for (int j = 1; j <= sLen; j++) {
                char patternChar = p.charAt(i-1);
                char stringChar = s.charAt(j-1);
                if (stringChar == patternChar || patternChar == '?') {
                    dp[i][j] = dp[i-1][j-1];
                }
                if (patternChar == '*') {
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];    
                }
            }
        }
        return dp[pLen][sLen];
    }
}