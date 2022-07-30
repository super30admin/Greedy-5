// Time Complexity :O(mn)where m is length of p and n is length of s
// Space Complexity :O(mn)
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :No
class Solution {
    public boolean isMatch(String s, String p) {
        int sl = s.length();
        int pl = p.length();
        boolean[][] dp = new boolean[pl + 1][sl + 1];
        dp[0][0] = true;
        // if there is first * in p dp[j][0] will be true else false
        for (int j = 1; j <= pl; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[j][0] = dp[j - 1][0];
            }
        }

        for (int i = 1; i <= pl; i++) {
            for (int j = 1; j <= sl; j++) {
                // if we have a character at p
                if (p.charAt(i - 1) != '*') {
                    // if it is matching or is equal to ?, take diagonal up
                    if (p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(i - 1) == '?') {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else {// * case take or of one step behind and just above
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        return dp[pl][sl];
    }
}

// ----------------USING ONE DIM ARRAY---------------------------
// Time Complexity :O(mn)where m is length of p and n is length of s
// Space Complexity :O(n)
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :No
class Solution {
    public boolean isMatch(String s, String p) {
        int sl = s.length();
        int pl = p.length();
        boolean[] dp = new boolean[sl + 1];
        dp[0] = true;
        for (int i = 1; i <= pl; i++) {
            boolean diagUp = dp[0];
            for (int j = 0; j <= sl; j++) {
                boolean temp = dp[j];
                if (p.charAt(i - 1) != '*') {

                    if (j > 0 && (p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(i - 1) == '?')) {
                        dp[j] = diagUp;
                    } else {
                        dp[j] = false;
                    }
                } else {
                    if (j > 0) {
                        dp[j] = dp[j] || dp[j - 1];
                    }
                }
                diagUp = temp;
            }
        }
        return dp[sl];
    }
}

// ------------------TWO POINTER SOLN-----------------------
// Time Complexity :SlogP
// Space Complexity :constant
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :no
class Solution {
    public boolean isMatch(String s, String p) {
        int sl = s.length();
        int pl = p.length();
        int sStar = -1;
        int sp = 0;
        int pStar = -1;
        int pp = 0;
        while (sp < sl) {
            // System.out.println("sp and pp"+sp+" and"+pp);
            // if characters are equal, go ahead
            if (pp < pl && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')) {
                sp++;
                pp++;
            }
            // if we found a *, record that
            else if (pp < pl && p.charAt(pp) == '*') {
                sStar = sp;
                pStar = pp;
                pp++;
            }
            // if char not matching and no star happened, return false
            else if (sStar == -1) {
                return false;
            }
            // if character didn't match and star is there in history, start taking one case
            else {
                pp = pStar + 1;
                sp = sStar + 1;
                sStar = sp;
            }
        }
        // if s string finished but p is still left, check if we have all stars or not
        // if we get even
        // one character,return false
        while (pp < pl) {
            if (p.charAt(pp) != '*') {
                return false;
            }
            pp++;
        }
        return true;
    }
}