//Approach-1 (dp)
//TC: O(sl*pl) where sl is length of s string and pl is the length of p string
//SC: O(sl*pl)

//CODE:

class Solution {
    public boolean isMatch(String s, String p) {
        if (s.equals(p) || p.equals('*'))
            return true;
        int sl = s.length();
        int pl = p.length();
        boolean[][] dp = new boolean[pl + 1][sl + 1];
        dp[0][0] = true;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                // if it is a normal character
                if (p.charAt(i - 1) != '*') {
                    if (j > 0
                            && (p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(i - 1) == '?'))
                        dp[i][j] = dp[i - 1][j - 1]; // taking value from diagonal
                } else {
                    // else if it a '*'
                    // zero case
                    dp[i][j] = dp[i - 1][j]; // just above
                    if (j > 0) {
                        // one case
                        dp[i][j] = dp[i][j] || dp[i][j - 1]; // one step back
                    }
                }
            }
        }
        return dp[pl][sl];
    }
}

// Approach-2 (Four pointers)
// TC: O(slogp) where s is the length of s string and p is length of p string
// SC: O(1)

// CODE
class Solution {
    public boolean isMatch(String s, String p) {
        if (s.equals(p) || p.equals('*'))
            return true;
        int sl = s.length();
        int pl = p.length();
        int sStar = -1, pStar = -1;
        int sp = 0, pp = 0;
        while (sp < sl) {
            if (pp < pl && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')) { // if chacarcter matches then move
                                                                                    // the ptrs
                sp++;
                pp++;
            } else if (pp < pl && p.charAt(pp) == '*') { // if there is a star then mark that position and consider the
                                                         // zero case
                sStar = sp;
                pStar = pp;
                pp++; // zero case i.e. matched star with empty string
            } else if (pStar == -1) { // if there is no star in the string and moreover the character does not matches
                return false;
            } else {
                // one case i.e. matching star with some character
                sp = sStar + 1;
                pp = pStar + 1;
                sStar = sp;
            }
        }
        while (pp < pl) { // if there are still some characters left in p string
            if (p.charAt(pp) != '*')
                return false;
            pp++;
        }
        return true;
    }
}
