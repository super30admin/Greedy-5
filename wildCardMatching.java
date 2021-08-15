// Time Complexity : O(Len(S)*Len(P))
// Space Complexity : O(Len(S)*Len(P))
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
class Solution {
    public boolean isMatch(String s, String p) {
        if(s == null || p == null) return false;
        int pLen = p.length();
        int sLen = s.length();
        
        boolean[][] dp = new boolean[pLen+1][sLen+1];
        for(int i = 0 ; i <= pLen ; i++){
            for(int j = 0 ; j <= sLen ; j++){
                if(i == 0 && j == 0) dp[i][j] = true;
                else if(i == 0) dp[i][j] = false;
                else if(j == 0){
                    if(p.charAt(i-1) == '*') dp[i][j] = dp[i-1][j];  
                }else{
                    char cP = p.charAt(i-1);
                    char cS = s.charAt(j-1);
                    if(cP == '*'){
                        dp[i][j] = dp[i-1][j] || dp[i-1][j-1];
                        if(dp[i][j]){
                            while(j < sLen){
                                j++;
                                dp[i][j] = true;
                            }    
                        } 
                    }
                    else if(cP == '?') dp[i][j] = dp[i-1][j-1];
                    else if(cP == cS) dp[i][j] = dp[i-1][j-1];    
                }               
            }
        }
        return dp[pLen][sLen];
    }
}