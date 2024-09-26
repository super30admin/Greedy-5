/*
Problem: https://leetcode.com/problems/wildcard-matching/

Similar to regular expression matching (https://github.com/Vaishnavik22/DP-7) expect that in that case,
'*' will match zero or more occurrences of the prev char but here, it could be 0 or more occurrences of any character.
*/

// Solution 1: DP
// TC: O(m * n)
// SC: O(m * n)
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

// Solution 2: 2 pointers -> SIMPLE
// TC: O(min(m , n))
// SC: O(1)
class Solution {
    public boolean isMatch(String s, String p) {
        if (s.equals(p))
            return true;
        
        // Pointers in s and p
        int pointerP = 0, pointerS = 0;
        // Records position of * in p
        int pStar = -1;
        // Records position of pointerS when * in p is found
        int sStar = -1;
        
        int pLen = p.length();
        int sLen = s.length();
        
        while (pointerS < sLen) {
            if (pointerP < pLen && (s.charAt(pointerS) == p.charAt(pointerP) || p.charAt(pointerP) == '?')) {
                ++pointerP;
                ++pointerS;
            } else if (pointerP < pLen && p.charAt(pointerP) == '*') {
                // When we encounter a * in p, we don't know if we need to consider 0 or more occurrences of * to match characters in s
                // To start off, let's assume that * matches 0 characters in p and so we move pointerP forward and note down the positions
                // of pStar and sStar.
                // If we need to match this * to one or more characters in s, we can come back to * using pStar to match characters
                pStar = pointerP;
                sStar = pointerS;
                ++pointerP;
            } else if (pStar == -1) {
                // This is the case where characters in p and s don't match
                // and we don't have a * in p to match characters in s -> ERROR
                return false;
            } else {
                // This is the case where characters in p and s don't match
                // but we have a * in p to match characters in s, so we need to move pointerP back to pStar to match characters
                pointerP = pStar;
                // We move pointerS and sStar forward to indicate that we've matched one character in s with * in p
                // But we could match more characters in s with p so we retain the position of pointerP at *
                ++sStar;
                pointerS = sStar;
                
            }
        }
        
        // To handle the case of trailing * in p
        while (pointerP < pLen) {
            if (p.charAt(pointerP) != '*') {
                return false;
            }
            ++pointerP;
        }
        return true;
    }
}