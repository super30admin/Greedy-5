//Time complexity:- O(mn^2)
//space complexity:- O(mn)

class Solution {
	
    public boolean isMatch(String s, String p) {
        int m= p.length();
        int n=s.length();
        boolean[][] dp=new boolean[m+1][n+1];
        dp[0][0]=true;
        for(int i=1;i<=m;i++){
           if(p.charAt(i-1) =='*'){ // here we will be having reppeated sub structures and optimal choice is that branch 
                                     // whether our branch provides answer or not.
               dp[i][0]=dp[i-1][0];
           }
        }
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(p.charAt(i-1)=='*'){
                    boolean choose=dp[i-1][j-1];
                    boolean nochoose=dp[i-1][j];
                    dp[i][j]=choose||nochoose;
                    if(dp[i][j]){
                        while(j<=n){
                            dp[i][j]=true;   // filling dp matrix based on 3 choices.
                            j++;
                        }
                    }
                }
                else if(p.charAt(i-1)==s.charAt(j-1)|| p.charAt(i-1)=='?'){
                    dp[i][j]=dp[i-1][j-1];
                }
                else if(p.charAt(i-1)!=s.charAt(j-1)){
                    dp[i][j]=false;
                }
                
            }
        }
        return dp[m][n];
    }
}