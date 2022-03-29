// Time Complexity : O(mn)    
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

//Approach

// We use dp to solve this problem
// We take a dp 2d boolean array and make dp[0][0] = true
// when the p char is not '*' and if the chars in s and p match we take the value of diagonal
// Else when j>0, we take of the left and top value

class Solution {
    public boolean isMatch(String s, String p) {
        if (s.equals(p) || p.equals("*"))
            return true;

        int m = p.length();
        int n = s.length();
        boolean[][] dp = new boolean[m + 1][n + 1];

        dp[0][0] = true;

        for (int i = 1; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (p.charAt(i - 1) != '*') {
                    if (j > 0 && (p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(i - 1) == '?'))
                        dp[i][j] = dp[i - 1][j - 1];
                } else {
                    if (j > 0) {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }

            }
        }
        return dp[m][n];
    }
}

// Time Complexity : O(n)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Approach

// We use dp to solve this problem
// We take a dp 2d boolean array and make dp[0][0] = true
// when the p char is not '*' and if the chars in s and p match we take the
// value of diagonal
// Else when j>0, we take of the left and top value
// Approach

// We make use of two pointers to solve this problem
// we loop over the strings
// we check the values match then we increment the pointers
// we note the points where * are present
// we roll back to the last star position if mismatch occurs
class Solution {
    public boolean isMatch(String s, String p) {
        if (s.equals(p) || p.equals("*"))
            return true;
        int m = p.length();
        int n = s.length();
        int sp = 0, pp = 0;
        int sStar = -1, pStar = -1;

        while (sp < n) {
            if (pp < m && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')) {
                pp++;
                sp++;
            } else if (pp < m && (p.charAt(pp) == '*')) {
                sStar = sp;
                pStar = pp;
                pp++;
            } else if (pStar == -1)
                return false;
            else {
                sp = sStar + 1;
                pp = pStar + 1;
                sStar = sp;
            }
        }

        while (pp < m) {
            if (p.charAt(pp) != '*')
                return false;
            pp++;
        }
        return true;
    }
}
