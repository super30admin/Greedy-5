/*
* Did this code successfully run on Leetcode : YES
* 
* Any problem you faced while coding this : NO
* 
* Time Complexity: O(l1 *l2)
    l1 - length of s
    l2 - length of p
* 
* Space Complexity: O(l2)
* 
*/

public class WildCardMatchingSpaceOptimization {
    public boolean isMatch(String s, String p) {
        int m = s.length();

        int n = p.length();

        // boolean[][] dp = new boolean[m+1][n+1];
        boolean[] prev = new boolean[n + 1];
        boolean[] curr = new boolean[n + 1];

        // top row
        for (int index = 1; index <= n; index++) {
            if (p.charAt(index - 1) == '*') {
                prev[index] = true;
            } else {
                break;
            }
        }

        prev[0] = true;

        for (int row = 1; row <= m; row++) {
            for (int col = 1; col <= n; col++) {
                if (s.charAt(row - 1) == p.charAt(col - 1) || p.charAt(col - 1) == '?') {
                    curr[col] = prev[col - 1];
                } else if (p.charAt(col - 1) == '*') {
                    boolean emptySequence = curr[col - 1];

                    boolean oneCharMatch = prev[col - 1];

                    boolean multipleCharMatch = prev[col];

                    curr[col] = (emptySequence || oneCharMatch || multipleCharMatch);
                } else {
                    curr[col] = false;
                }
            }

            prev = curr.clone();
        }

        return prev[n];
    }
}