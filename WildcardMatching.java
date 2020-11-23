// Time Complexity : O(n^2)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
// DP
class Solution {
    public boolean isMatch(String s, String p) {
        boolean[][] dp=new boolean[s.length()+1][p.length()+1];
        
        dp[0][0]=true;
        
        int k=0;
        while(k<p.length() && p.charAt(k)=='*'){
            dp[0][k+1]=true;
            k++;
        }
        
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                
                char c=s.charAt(i-1);
                char pChar=p.charAt(j-1);
                
                if(c==pChar || pChar=='?'){
                    dp[i][j]=dp[i-1][j-1];
                }else if(pChar=='*'){
                    dp[i][j]=dp[i][j-1]||dp[i-1][j];
                 
                }else{
                    dp[i][j]=false;
                }
            }
        } 
        return dp[s.length()][p.length()];
        
    }
}