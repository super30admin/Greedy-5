// Time Complexity : O(N*M) 
// Space Complexity : O(N*M) 
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
class Solution {
    public boolean isMatch(String s, String p) {
        int n = p.length();
        int m = s.length();

        boolean[][] dp = new boolean[n + 1][m + 1];

        dp[0][0] = true;

        for(int i = 1; i <= n; i++){
            if(p.charAt(i - 1) == '*'){
                dp[i][0] = dp[i-1][0];
            }
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                char schar = s.charAt(j - 1);
                char pchar = p.charAt(i - 1);

                if(pchar == '*'){
                    boolean C = dp[i - 1][j - 1];
                    boolean DC = dp[i - 1][j];

                    dp[i][j] = C || DC;

                    if(dp[i][j]){
                        while(j <= m){
                            dp[i][j] = true;
                            j++;
                        }
                    }
                }else if(schar == pchar || pchar == '?'){ 
                    dp[i][j] = dp[i - 1][j -1];
                }else if(schar != pchar){
                    dp[i][j] = false;
                }
            }
        }

        return dp[n][m];
    }
}
