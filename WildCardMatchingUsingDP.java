// Time Complexity : O(m*n)  m is the lenght of the source and n is the length of the pattern
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes

public class WildCardMatchingUsingDP {
    public boolean isMatch(String s, String p) {

        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for(int j=1; j<=n; j++){
            if(p.charAt(j-1) == '*'){
                dp[0][j] = dp[0][j-1];
            }
        }

        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){

                if(p.charAt(j-1) == '*')
                {
                    dp[i][j] = dp[i][j-1] || dp[i-1][j];
                }
                else{
                    if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?'){
                        dp[i][j] = dp[i-1][j-1];
                    }else{
                        dp[i][j] = false;
                    }
                }
            }
        }

        return dp[m][n];
    }

}
