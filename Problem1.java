//Time Complexity: O(m*n)
//Space Complexity: O(m*n)
//Code run successfully on LeetCode.

public class Problem1 {

    public boolean isMatch(String s, String p) {
        
        int m = s.length();
        int n = p.length();
        
        boolean[][] dp = new boolean[n+1][m+1];
        
        dp[0][0] = true;
        
        for(int i =1; i < n +1; i++)
        {
            for(int j =0; j < m +1; j++)
            {
                if(p.charAt(i-1) != '*')
                {
                    if(j > 0){
                        if(p.charAt(i-1) == s.charAt(j-1) || p.charAt(i-1) == '?')
                           dp[i][j] = dp[i-1][j-1];
                    }
                }
                
                else
                {
                    if(j > 0)
                    {
                        dp[i][j] = dp[i-1][j] || dp[i][j-1];
                    }
                    
                    else
                        dp[i][j] = dp[i-1][j];
                }
            }
        }
        
        return dp[n][m];
    }
}
