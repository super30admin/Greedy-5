// Time Complexity : 0(nxm)
// Space Complexity : O(nxm)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


class Solution {
    public boolean isMatch(String s, String p) {
        int[][] dp = new int[ s.length() ][ p.length() ];

        for(int[] arr : dp) Arrays.fill( arr, -1 ); // -1 means not visited

        return helper(s, p, s.length() - 1, p.length() - 1, dp);
    }

    private boolean helper(String s, String p, int i, int j, int[][] dp) {
        if(i < 0 && j < 0) return true; // both s and p are empty
        if(i >= 0 && j < 0) return false; // s is left but p is empty
        if(i < 0 && j >= 0) {
            for(int k = 0; k <= j; k++) {
                if(p.charAt(k) != '*') return false;
            }
            return true;
        }

        if(dp[i][j] != -1) return dp[i][j] == 1;

        boolean result = false;
        if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') result = helper(s, p, i-1, j-1, dp); // both characters match
        else
        if(p.charAt(j) == '*') result = helper(s, p, i, j-1, dp) || helper(s, p, i-1, j, dp);

        dp[i][j] = result ? 1 : 0;
        return result; 
    }
}