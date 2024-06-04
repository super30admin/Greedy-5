class Solution {
    public boolean isMatch(String s, String p) {
        // Tc: O(m*n) Sc: O(m*n)
        if (s.equals(p) || p.equals("*"))
            return true;
        int sl = s.length();
        int pl = p.length();
        boolean[][] dp = new boolean[pl + 1][sl + 1];
        dp[0][0] = true;

        for (int i = 1; i <= pl; i++) {
            for (int j = 0; j <= sl; j++) {
                if (p.charAt(i - 1) == '*') {
                    if (j == 0) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                    }
                } else {
                    if (j > 0 && (p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(i - 1) == '?')) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[pl][sl];
    }
}