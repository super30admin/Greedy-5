//time complexity-O(s*P)
//Space complexity-O(s*p)
//Ran on leetcode-Yes
//Solution with comments-
//      _ a b c
//    _ t f f f
//    a f t f f
//    * f t t t
//    c f f f t

class Solution {
    public boolean isMatch(String s, String p) {
        int n= p.length(); int m = s.length();
        boolean[][] dp = new boolean[n+1][m+1];
        
        dp[0][0]=true;
        
        for(int i=1;i<=n;i++){
            if(p.charAt(i-1)=='*')
                dp[i][0]=dp[i-1][0];
        }
        
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                char schar= s.charAt(j-1);
                char pchar= p.charAt(i-1);
                
                if(pchar=='*'){
                    boolean DC= dp[i-1][j];
                    boolean C =dp[i-1][j-1];
                    
                    dp[i][j]= C || DC;
                    
                    if(dp[i][j]){//if i,j is true then we need to mark every other column in this row as true
                        while(j<=m){
                            dp[i][j]=true;
                            j++;
                        }
                    }
                }
                else if(pchar==schar || pchar=='?'){
                    dp[i][j]=dp[i-1][j-1];//check diagonal
                }
                else if (schar!=pchar){
                    dp[i][j]=false;
                }
            }
        }
        
        return dp[n][m];
    }
}