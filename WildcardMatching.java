/*
Time Complexity: O(N*N)
Space Complexity: O(N*N)
*/

class Solution {
    public boolean isMatch(String s, String p) {
        int n = s.length(), m = p.length();
        // dp[i][j] == true represents s[0..i-1] matches pattern[0..j-1]
        boolean[][] dp = new boolean[n + 1][m + 1];
        // initialization
        dp[0][0] = true;
        // s is empty
        for (int j = 1; j <= m; j++) {
            // only use '*' to match empty string -> equal to ignore current '*' 
            if (p.charAt(j - 1) == '*') dp[0][j] = dp[0][j - 1];
        }

        // dp
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // current pattern is *, either ignore "*" (match empty string) -> dp[i][j - 1]
                // or match multiple previous characters(at least 1) in s 
                // -> if use '*' can match s[0..i-2] (dp[i-1][j]), it can also match s[0..i-1] 
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                } else {
                    // current pattern is '?' or letter, can only match 1 character in s
                    dp[i][j] = dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1)
                            || p.charAt(j - 1) == '?');
                }
            }
        }

        return dp[n][m];
    }
}