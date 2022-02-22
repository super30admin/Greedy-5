// Time Complexity = O(n*m)
// Space Complexity = O(n*m)
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :No


// Your code here along with comments explaining your approach
// DP Solution
class Solution {
    public boolean isMatch(String s, String p) {
        if (s.equals(p) || p.equals('*')) return true;
        int m = p.length();
        int n = s.length();
        boolean[][] dp = new boolean[m+1][n+1];

        // blank will always be equal to blank
        dp[0][0]=true;

        for (int i=1; i<m+1; i++) {
            for (int j=0; j<n+1; j++) {
                if (p.charAt(i-1) != '*') {     //case 1
                    if (j>0 && (p.charAt(i-1) == s.charAt(j-1) || p.charAt(i-1) == '?') ) {
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
                else {                          // case 2
                    // we have 0 case and 1 case
                    if (j>0) {
                        dp[i][j] = dp[i-1][j] || dp[i][j-1];
                    }
                    else {
                        dp[i][j] = dp[i-1][j];
                    }
                }
            }
        }

        return dp[m][n];
    }
}

// --------------------------------------------------------------------------------------------------------------------
// Time Complexity = O(min(s,p))
// Space Complexity = O(1)
class Solution {
    public boolean isMatch(String s, String p) {
        if (s.equals(p) || p.equals('*')) return true;
        int m = p.length();
        int n = s.length();
        int sp = 0, pp = 0;
        int sStar = -1, pStar = -1;

        while (sp < n) {
            // case 1
            if ((pp<m) && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')) {
                sp++;
                pp++;
            }
            // case 2
            else if ((pp<m) && p.charAt(pp) == '*') {
                sStar = sp;
                pStar = pp;

                // try 0 case for *
                pp++;
            }
            // case 3
            else if (sStar == -1) {
                return false;
            }
            // case 4
            else {
                // try 1 case for *
                sp = sStar+1;
                pp = pStar+1;
                sStar = sp;
            }
        }

        while (pp < m) {
            if (p.charAt(pp) != '*') {
                return false;
            }
            pp++;
        }

        return true;
    }
}