//TC: O(MN)
//SC: O(MN)
class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p)) return true;

        int n = s.length();
        int m = p.length();

        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;

        for(int i=1;i<=m;i++)
        {
            for(int j=0;j<=n;j++)
            {
                if(p.charAt(i-1)!='*')
                {
                    if(j>0)
                    {
                        if(p.charAt(i-1)==s.charAt(j-1) || p.charAt(i-1)=='?')
                            dp[i][j] = dp[i-1][j-1];
                    }
                }
                else
                {
                    if(j==0)
                        dp[i][j] = dp[i-1][j];
                    else
                        dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }
            }
        }

        return dp[m][n];
    }
}