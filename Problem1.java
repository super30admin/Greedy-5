
class Problem1 {
    //TC:O(M*N)
    //SC:O(M*N)
    public boolean isMatch(String s, String p) {
        boolean [][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;


        for(int l=1;l<=p.length();l++){
            if(p.charAt(l-1) == '*'){
                dp[0][l] = dp[0][l-1] ;
            }

        }

        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[1].length;j++){
                char pChar = p.charAt(j-1);
                if( pChar == '*'){
                    dp[i][j] =  dp[i][j-1] || dp[i-1][j] ;

                }else{
                    if(pChar =='?' || s.charAt(i-1) == pChar){
                        dp[i][j] = dp[i-1][j-1];
                    }else{
                        dp[i][j] = false ;
                    }

                }

            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}
