// Time Complexity : O(S * P)
// Space Complexity : O(S * P)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

/**
 * use Dp. Start iterating from 0th index of s and p. check if both are equal.
 * or if the pattern current index is ?, if so, iterate next index of each
 * string. Else if current index of p is *, then three possibilities are
 * possible. 1. update both s and p indices. 2. Update s index. 3. Update p
 * index. Else return false. when both strings meet end index, return true. Else
 * if s reached end, then check remaining characters of p are *. If not return
 * false. If either of them reach end but not the other, then also false.
 *
 */
class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p) || p.equals("*")) return true;
        int sl = s.length();
        int pl = p.length();
        boolean[][] dp = new boolean[pl+1][sl+1];
        dp[0][0] = true;
        
        //first column
        for(int i=1; i<=pl;i++){
            if(p.charAt(i-1) == '*'){
                dp[i][0] = dp[i-1][0];
            }
        }
        for(int i=1; i<=pl;i++){
            for(int j=1;j<=sl;j++){
                if(p.charAt(i-1) != '*'){
                    if(p.charAt(i-1) == s.charAt(j-1) || p.charAt(i-1) == '?'){
                         dp[i][j] = dp[i-1][j-1]; //diag
                        
                    }
                }else{
                    //0 || 1
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }
            }
        }
        return dp[pl][sl];
    }
}
