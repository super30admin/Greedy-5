// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Yes

class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p)){
            return true;
        } 
        if(p == null || p.length() == 0){
            return false;
        }
        int row = p.length();
        int col = s.length();
        boolean dp [][] = new boolean[row+1][col+1];  
        dp[0][0] = true;

        for(int i = 1; i < row+1; i++){
            for(int j = 0; j < col+1; j++){
                if(p.charAt(i-1) != '*'){
                    if(j > 0 && ((p.charAt(i-1) == s.charAt(j-1)) || (p.charAt(i-1) == '?'))){
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
                else{
                    dp[i][j] = dp[i-1][j];
                    if(j>0){
                        dp[i][j] = dp[i][j] || dp[i][j-1];
                    }
                }
            }
        }
        return dp[row][col];
    }
}