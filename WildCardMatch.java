// Time Complexity : 
/*                      Approach 1 : Using DP : O(sp) where s is the string length and p is the pattern length
                        Approach 2: Using Two Pointers : O(sp) in the worst case
// Space Complexity :
/*                      Approach 1 : Using DP: O(sp) where s is the string length and p is the pattern length
                        Approach 2: Using Two Pointers : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Two pointer solution is tough.
/* Your code here along with comments explaining your approach: In approach 1, we are creating a DP matrix, and observing if the * appears, we
check the row above the column on the left and the diagonal value, if anything is true, the cell is true, if characters are equal we simply match
the diagonals. In approach 2, we eliniminate the need of extra space, by maintaining two pointers, p1 and s1, if p1 and s1 has same characters, we go ahead
but if the p1 has a star, for null case, we keep p1 ahead of pStar and match with s but for one or more case, we keep incrementing the s and sStar
to cover the multiple characters that the * is capable of until the character in s becomes equal to the character in p. If s1 and p1, both has reached
its end, implies its a match.  
*/
// APPROACH 1 : USING DP
class Solution {
    public boolean isMatch(String s, String p) {
        if(s == null || p == null) return false;
        if(s == null && p == null) return true;                                                                 // Base Case
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];                                           // DP Matrix
        int i = 1;
        dp[0][0]= true;
        for(int k = 1; k  < dp[0].length; k++){
            if(p.charAt(k-1) == '*'){
               if(dp[0][k-1] == true){                                                                  // First row case of *
                   dp[0][k] = true;
               }
            }
        }
        while(i < dp.length){
            int j = 1;
            while(j < dp[0].length){
                if(p.charAt(j-1) == '*'){
                    if(dp[i][j-1] || dp[i-1][j] == true || dp[i-1][j-1] == true){                               // If * comes, pattern is this
                        dp[i][j] = true;                                                                        // If any one is true
                        }
                }
             else
            if((s.charAt(i-1) == p.charAt(j-1)) || (p.charAt(j-1) == '?')){                                     // If character match, take from diagonal
                    dp[i][j] = dp[i-1][j-1];
            }  
            j++;
        }
            i++;
        }     
      return dp[dp.length-1][dp[0].length-1];                                                                   // Return the last cell as result
    }
}

// APPROACH 2 : USING TWO POINTERS
class Solution {
    public boolean isMatch(String s, String p) {
        if(s == null || p == null) return false;                                                            // Base Case
        if(s == null && p == null) return true;
        int sStar = -1, pStar = -1, s1 = 0, p1 = 0;
        while(s1 < s.length()){
           if(p1 < p.length() && (s.charAt(s1) == p.charAt(p1) || p.charAt(p1) == '?')){                                    // If chracters match, move both pointers
               s1++;
               p1++;
           } 
            else if(p1 < p.length() && p.charAt(p1) == '*'){                                            // If star comes, we see null case first
                sStar = s1;
                pStar = p1;
                p1++;                                                                                   // Move p pointer to the next character
            } else
             if(pStar == -1)                                                                                // If pStar isnt moved yet
                 return false;                                                                          // Character mismatch and no star case
            else
            {
                s1 = sStar + 1;                                                                                 // Move sStar to cover 1 or more cases of star in p
                p1 = pStar + 1;
                sStar = s1;
            }
        }
        while(p1 < p.length()){
            if(p.charAt(p1) != '*') return false;                                                       // If * at the end then its match
            p1++;
        }
        return true;                                                                                // Its a match
    }
}