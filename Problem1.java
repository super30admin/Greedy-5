/*
Wildcard Matching
approach: same as regex matching, use dp
time: O(mxn)
space: O(mxn)
 */
public class Problem1 {
    public boolean isMatch(String s, String p) {
        if (s.equals(p) || p.equals("*")) return true;
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;

        for (int i=1;i<=n;i++) {
            if (p.charAt(i-1)=='*') dp[i][0] = dp[i-1][0];
        }

        for (int i=1;i<=n;i++) {
            char pchar = p.charAt(i-1);
            for (int j=1;j<=m;j++) {
                char schar = s.charAt(j-1);
                if (pchar=='*') {
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }
                else if (pchar==schar || pchar=='?') dp[i][j] = dp[i-1][j-1];
            }
        }

        return dp[m][n];
    }
}
