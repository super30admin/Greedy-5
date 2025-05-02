

/*
//TC: O(m*n)  SC: O(n)
class Solution {
  public boolean isMatch(String s, String p) {
      int m=s.length();
      int n=p.length();
      boolean[] dp=new boolean[n+1];
      dp[0]=true;
      boolean diagup=true;
      for(int i=0;i<=m;i++){
          for(int j=0;j<=n;j++){
              boolean temp=dp[j];
              if(i==0 && j==0){
                  continue;
              }
              if(i>0 && j==0){
                  dp[j]=false;
              }else if(i>0 && (s.charAt(i-1)==p.charAt(j-1) || p.charAt(j-1)=='?')){
                  dp[j]=diagup;
              }else if(p.charAt(j-1)=='*'){
                  if(i==0){
                      dp[j]=dp[j-1];
                  }else{
                      dp[j]=dp[j-1] || dp[j];
                  }                   
              }else{
                  dp[j]=false;
              }
              diagup=temp;
          }
      }
      return dp[n];
  }
}
*/
/*

class Solution {
  public boolean isMatch(String s, String p) {
      int m=s.length();
      int n=p.length();
      boolean[][] dp=new boolean[m+1][n+1];
      dp[0][0]=true;
      for(int i=0;i<=m;i++){
          for(int j=1;j<=n;j++){
              if(i>0 && (s.charAt(i-1)==p.charAt(j-1) || p.charAt(j-1)=='?')){
                  dp[i][j]=dp[i-1][j-1];
              }else if(p.charAt(j-1)=='*'){
                  if(i==0){
                      dp[i][j]=dp[i][j-1];
                  }else{
                      dp[i][j]=dp[i][j-1] || dp[i-1][j];
                  }                   
              }
          }
      }
      return dp[m][n];
  }
}

*/
//TC: bestcase O(m+n)
//worstcase O(m*n)
class WildcardMatching {
  public boolean isMatch(String s, String p) {
      int m=s.length();
      int n=p.length();
      int sstar=-1;
      int pstar=-1;
      int i=0;
      int j=0;
      while(i<m){
          if(j<n && (s.charAt(i)==p.charAt(j) || p.charAt(j)=='?')){
              i++;
              j++;
          }else if(j<n && p.charAt(j)=='*'){
              sstar=i;
              pstar=j;
              j++;
          }else if(pstar==-1){
              return false;
          }else{
              sstar++;
              i=sstar;
              j=pstar+1;
          }
      }
      while(j<n){
          if(p.charAt(j)!='*'){
              return false;
          }
          j++;
      }
      return true;
  }
}
