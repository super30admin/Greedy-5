/*
* Did this code successfully run on Leetcode : YES
* 
* Any problem you faced while coding this : NO
* 
* Time Complexity: O(l1 *l2)
    l1 - length of s
    l2 - length of p
* 
* Space Complexity: O(l1*l2)
* 
*/
public class WildCardMatchingTabulation {
    public boolean isMatch(String s, String p) {
        int m = s.length();

        int n = p.length();

        boolean[][] dp = new boolean[m + 1][n + 1];

        // top row
        for (int index = 1; index <= n; index++) {
            if (p.charAt(index - 1) == '*') {
                dp[0][index] = true;
            } else {
                break;
            }
        }

        dp[0][0] = true;

        for (int row = 1; row <= m; row++) {
            for (int col = 1; col <= n; col++) {
                if (s.charAt(row - 1) == p.charAt(col - 1) || p.charAt(col - 1) == '?') {
                    dp[row][col] = dp[row - 1][col - 1];
                } else if (p.charAt(col - 1) == '*') {
                    boolean emptySequence = dp[row][col - 1];

                    boolean oneCharMatch = dp[row - 1][col - 1];

                    boolean multipleCharMatch = dp[row - 1][col];

                    dp[row][col] = (emptySequence || oneCharMatch || multipleCharMatch);
                } else {
                    dp[row][col] = false;
                }
            }
        }

        return dp[m][n];
    }
}
