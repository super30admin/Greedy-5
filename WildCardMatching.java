//TC - O(s) * log(p) 
//Sc - O(1)
class Solution {
    public boolean isMatch(String s, String p) {
    if(s.equals(p) || p.equals('*')) return true;
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[n+1][m+1];
        dp[0][0] = true;
        
       
        for(int i = 1 ; i <= n; i++){
            for(int j = 0; j<= m; j++){
                if(p.charAt(i - 1) != '*'){
                    if(j > 0 && (s.charAt(j-1) == p.charAt(i-1) || p.charAt(i-1) == '?')){
                        dp[i][j] = dp[i-1][j-1];
                    }
                }else{
                    dp[i][j] = dp[i-1][j];
                    if(j > 0){
                        dp[i][j] = dp[i][j] || dp[i][j - 1];
                    }
                }
            }
        }
        return dp[n][m];
    }
}
