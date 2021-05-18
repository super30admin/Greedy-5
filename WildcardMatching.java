// TC - O(n X m)

// SC - O(n X m)

// LC - 44

class Solution {
    public boolean isMatch(String s, String p) {
		// lengths of both s and p
        int n = p.length();
        int m = s.length();
        // Create a dp array
        boolean[][] dp = new boolean[n+1][m+1];
        // Empty to empty is always true, so dp[0][0] is true
        dp[0][0] = true;
        
		
        for(int i=1; i<=n; i++){
            if(p.charAt(i-1) == '*'){
                dp[i][0] = dp[i-1][0];
            }
        }
        
		// Iteate over s and p
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                char schar = s.charAt(j-1);
                char pchar = p.charAt(i-1);
                
				// if pchar is *, C will be left top diagonal and DC will be just above
                if(pchar == '*'){
                    boolean C = dp[i-1][j-1];
                    boolean DC = dp[i-1][j];
                    
					// If C or DC is true, update dp i and j to true
                    dp[i][j] = C || DC;
                    
					// If dp[i][j] is true, update whole row to true
                    if(dp[i][j]){
                        while(j <= m){
                            dp[i][j] = true;
                            j++;
                        }
                    }
                    
				// If schar and pchar are equal, we can ignore pchar and schar, so value will be found left top diagonal, same with ?, ? replaces any character
                }else if(schar == pchar || pchar == '?'){
                    dp[i][j] = dp[i-1][j-1];
					// If schar and pchar are not equal, value will be false
                }else if(schar != pchar){
                    dp[i][j] = false;
                }
            }
        }
		// return last value in matrix
        return dp[n][m];
    }
}