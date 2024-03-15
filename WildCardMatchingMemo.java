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

import java.util.Arrays;

public class WildCardMatchingMemo {
    private boolean helper(String s, String p, int i, int j, int[][] dp) {
        // base cases
        if (i < 0 && j < 0) {
            return true;
        }

        if (i < 0) {
            while (j >= 0) {
                if (p.charAt(j) != '*') {
                    return false;
                }
                j--;
            }
            return true;
        }

        if (j < 0) {
            return false;
        }

        if (dp[i][j] != -1) {
            return dp[i][j] == 1;
        }

        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
            dp[i][j] = helper(s, p, i - 1, j - 1, dp) ? 1 : 0;
        } else if (p.charAt(j) == '*') {
            boolean emptySequence = helper(s, p, i, j - 1, dp);

            boolean oneCharMatch = helper(s, p, i - 1, j - 1, dp);

            boolean multipleCharMatch = helper(s, p, i - 1, j, dp);

            dp[i][j] = (emptySequence || oneCharMatch || multipleCharMatch) ? 1 : 0;
        } else {
            dp[i][j] = 0;
        }

        return dp[i][j] == 1;
    }

    public boolean isMatch(String s, String p) {
        int m = s.length();

        int n = p.length();

        int[][] dp = new int[m][n];

        for (int[] ar : dp) {
            Arrays.fill(ar, -1);
        }

        return helper(s, p, m - 1, n - 1, dp);
    }
}
