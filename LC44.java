// TC: O(n*m)
// SC: O(n*m)

public class LC44 {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for(int j=1;j<=n;j++){
            char ch = p.charAt(j-1);
            if(ch=='*'){
                dp[0][j] = dp[0][j-1];
            }
        }
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                char ch1 = s.charAt(i-1);
                char ch2 = p.charAt(j-1);
                if(ch1==ch2 || ch2=='?'){
                    dp[i][j] = dp[i-1][j-1];
                } else if(ch2=='*'){
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }
            }
        }
        return dp[m][n];
    }
}