class WildcardMatching {
    
    // Time Complexity: O(n * m)     (where n -> len(pattern) and m -> len(input string))
    // Space Complexity: O(n * m)
    
    public boolean isMatch(String s, String p) {
        int n = p.length();
        int m = s.length();
        
        //In DP Matrix --> horizontal --> string and vertical --> pattern
        
        boolean[][] dp = new boolean[n+1][m+1];
        dp[0][0] = true;    // because empty string --> gives empty string (TRUE)
        
        for(int i = 1; i <= n; i++){
            if(p.charAt(i-1) == '*')
                dp[i][0] = dp[i-1][0];
        }
        
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                char schar = s.charAt(j-1);
                char pchar = p.charAt(i-1);
                
                if(pchar == '*'){
                    boolean choose = dp[i-1][j-1];  // top-diagonal element
                    boolean dont_choose = dp[i-1][j];   // top element
                    
                    dp[i][j] = choose || dont_choose;  // if any of them is true --> we assign true
                    // if one character --> '*' is true --> then the entire row becomes true for all the other characters
                    if(dp[i][j]){
                        while(j <= m){
                            dp[i][j] = true;
                            j++;
                        }
                    }
                }else if(schar == pchar || pchar == '?')
                    dp[i][j] = dp[i-1][j-1];
                else if(schar != pchar)
                    dp[i][j] = false;
            }
        }
        return dp[n][m];
     }
}