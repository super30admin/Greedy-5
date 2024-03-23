class Solution {
    //TC: O(m*n)
    //SC: O(m*n)
    public boolean isMatch(String s, String p) {
        if(s.equals(p) || p.equals("*")) return true;
        int s1 = s.length();
        int p1 = p.length();
        boolean [][] dp = new boolean[s1+1][p1+1];
        dp[0][0] = true;
        //top row
        for(int j = 1; j <= p1; j++){
            if(p.charAt(j-1) == '*'){
                dp[0][j] = dp[0][j-1];
            }
        }
        for(int i = 1; i <= s1; i++){
            for(int j = 1; j <= p1; j++){
                if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?'){
                    dp[i][j] = dp[i-1][j-1];
                }else if(p.charAt(j-1) == '*'){
                    dp[i][j] = dp[i][j-1] || dp[i-1][j];
                }
            }
        }
        return dp[s1][p1]; 
    }
}
