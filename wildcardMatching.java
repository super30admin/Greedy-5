/*
Problem: https://leetcode.com/problems/wildcard-matching/

Similar to regular expression matching (https://github.com/Vaishnavik22/DP-7) expect that in that case,
'*' will match zero or more occurrences of the prev char but here, it could be 0 or more occurrences of any character.

TC: O(m * n)
SC: O(m * n)
*/
class Solution {
    public boolean isMatch(String s, String p) {
        if (s.equals(p))
            return true;
        
        int n = s.length();
        int m = p.length();
        boolean dp[][] = new boolean[m + 1][n + 1];
        
        // Empty string matches empty pattern
        dp[0][0] = true;
        
        // Non-empty string will never match empty pattern
        for (int j = 1; j <= n; ++j) {
            dp[0][j] = false;
        }
        
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                char pChar = p.charAt(i - 1);
                
                // if character at p is either ? or a char in [a,z], we will have to compare this with the character in s
                if (pChar != '*') {
                    if (j > 0) {
                        if (pChar == s.charAt(j - 1) || pChar == '?') {
                            dp[i][j] = dp[i - 1][j - 1];
                        }
                    } else {
                        // We're trying to match a character / ? in p to an emptry string s
                        // This will always be false
                        dp[i][j] = false;
                    }
                } else {    // pChar = '*'
                    /**
                    Here we have two choices:
                    Case 1: either we match the * to a char.
                        - So we'll expand the pattern to include the char after *
                        - Then we'll have to look to see if dp[i][j-1] matches
                    Case 2: consider 0 occurrences of *
                        - Here we'll have to char if chars at i - 1 matches j
                    */
                    if (j > 0) {
                        dp[i][j] = dp[i][j-1] || dp[i-1][j];   
                    } else {
                        dp[i][j] = dp[i-1][j];
                    }
                    
                }
            }
        }
        
        return dp[m][n];
    }
}