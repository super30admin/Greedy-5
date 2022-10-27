class Problem1{
    public boolean isMatch(String s, String p) {
        
        boolean [][]dp= new boolean[p.length()+1][s.length()+1];
        
        dp[0][0]=true;
        
        for(int i=1;i<dp.length;i++){
            
            if(p.charAt(i-1)=='*'){  dp[i][0]=dp[i-1][0];  }
           else dp[i][0]=false;            
        }
        
        for(int j=1;j<dp[0].length;j++){
            dp[0][j]=false;
            
        }
        
       
     
        
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                
                
                if(s.charAt(j-1)==p.charAt(i-1)||p.charAt(i-1)=='?'){
                    
                    dp[i][j]=dp[i-1][j-1];
                }
                
            else if(p.charAt(i-1)=='*'){ 
            // System.out.println(i+" "+j);
             dp[i][j]= (dp[i][j-1]||dp[i-1][j]);
             }   
                
            }
            
        }
        
        return dp[dp.length-1][dp[0].length-1];
        
        
    }

}