// Time Complexity : O(N*N) 

// Space Complexity : O(N)

// Did this code successfully run on Leetcode : YES

// Appoarch: DP - match and dont match

// 44. Wildcard Matching


import java.util.Arrays;

class Solution {
    int helper(int i,int j,String s, String p, int [][] dp){
        if(i < 0 && j < 0) 
            return 1;
        if(i < 0 && j >= 0) 
            return 0;
        if(j < 0 && i >= 0){
            for(int k = 0;k <=i;k++){
                if(p.charAt(k) != '*'){
                    return 0;
                } 
            }
            return 1;
        }
        if(dp[i][j] != -1){
            return dp[i][j];
        }
        if(s.charAt(j) == p.charAt(i) || p.charAt(i) == '?'){
            return dp[i][j] = helper(i-1,j-1,s,p,dp);
        }
        if(p.charAt(i) == '*'){
            return dp[i][j] = (helper(i-1,j,s,p,dp) == 1 || helper(i,j-1,s,p,dp) == 1 ) ? 1 : 0;
        }
        return 0;
        
    }
    public boolean isMatch(String s, String p) {
        int n = p.length();
        int m = s.length();
        int [][] dp = new int[n+1][m+1];
        for (int row[]: dp){
            Arrays.fill(row,-1);
        }
        int res = helper(n-1,m-1,s,p,dp);
        if(res != 1){
            return false;
        }
        return true;
    }
}