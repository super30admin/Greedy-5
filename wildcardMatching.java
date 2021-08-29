package Greedy5;

public class wildcardMatching {
	//TC : o(m*n) m and n are lengths of p and s
	//SC : o(m*n)
	//Approach : 1. check all possible patterns to match to input string 2. There are repeated sub problems as we choose 0,1,2... for *. so DP can be done.

	class Solution {
	    public boolean isMatch(String s, String p) {
	       //base case
	        if(s.equals(p) || p.equals('*')) return true;
	        int sl = s.length();
	        int pl = p.length();
	        
	        boolean[][] dp = new boolean[pl+1][sl+1];
	        dp[0][0] = true;
	        for(int i=1; i<dp.length; i++){
	            for(int j=0; j<dp[0].length; j++){
	                //* 
	                if(p.charAt(i-1) == '*'){
	                    //0 case and 1 case
	                    dp[i][j] = dp[i-1][j]; // 0 case
	                    if(j > 0){
	                        dp[i][j] = dp[i][j] || dp[i][j-1];//from above
	                    }
	                }
	                    else if(j > 0 && (p.charAt(i-1) == s.charAt(j-1) || p.charAt(i-1) == '?')){
	                        dp[i][j] = dp[i-1][j-1];
	                    }
	                //not*
	            }
	        }
	        return dp[pl][sl];
	    }
	}
}
