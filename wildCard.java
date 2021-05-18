class Solution {
    public boolean isMatch(String s, String p) {
        int n = p.length();
        int m = s.length();
        
        boolean[][] dp = new boolean[n + 1][m + 1];
        
        dp[0][0] = true;
        
        for(int i = 1; i <= n; i++){
            //if charcater of pattern is * at an index, 
            //check if the previous and assign it
            if(p.charAt(i - 1) == '*'){
                dp[i][0] = dp[i-1][0];
            }
        }
        
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                char schar = s.charAt(j - 1);
                char pchar = p.charAt(i - 1);
                
                if(pchar == '*'){//if choosing or not choosing * is true, then we can make entire row as true
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
                    //if the pattern is ? or the character matches both the s and p 
                    //then we can decide based on previous value
                    dp[i][j] = dp[i - 1][j -1];
                }else if(schar != pchar){//if character dosent match, then make it false
                    dp[i][j] = false;
                }
            }
        }
        
        //return final boolean value in dp array
        return dp[n][m];
    }
}

// Time Complexity: O(n*m)
//Space Complexity: O(n*m)