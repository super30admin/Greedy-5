// TC: O(m * n) as the dp matrix is traversed based on the s and p lengths
// SC: O(m * n) which is the dimensions of the dp matrix.

// Runs successfully on leetcode
// Another optimized space complexity approach was discussed. But it didn't seem intuitive for me.

// Based on current char '*' or not.
// if any character is matched, then the diagonal top left value is retrieved.
// otherwise, the 0 and 1 condition cased is ORed.
public class WildCardMatching {

    public static void main(String[] args) {
        System.out.println(isMatch("aa", "a")); // false
        System.out.println(isMatch("aa", "*")); // true
        System.out.println(isMatch("cb", "?a")); // false
    }

    private static boolean isMatch(String s, String p) {
        if (s.equals(p))
            return true;
        if (p == null || p.length() == 0)
            return false;
        int n = s.length();
        int m = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (p.charAt(i - 1) != '*') {
                    if (j > 0 && (p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(i - 1) == '?'))
                        dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j];
                    if (j > 0)
                        dp[i][j] = dp[i][j] || dp[i][j - 1];
                }
            }
        }
        return dp[m][n];
    }
}