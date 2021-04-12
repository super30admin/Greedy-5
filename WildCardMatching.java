/*
method 1: using dp
Tc: O(mn)
SC:O(mn)

method 2: pattern matching technique 
TC: O(m+n)
SC :O(1)
*/
class Solution {

    //method1    
//     public boolean isMatch(String s, String p)
//     {
//         if(s.equals(p) || p.equals("*"))return true;
        
        
//         int sl = s.length();
//         int pl = p.length();
        
//         boolean[][]dp = new boolean[pl+1][sl+1];
        
//         dp[0][0] = true;
        
//         for(int i = 1; i < dp.length;i++){
            
//             char ch = p.charAt(i-1);
            
//             if(ch == '*')
//             {
//                 int j = 0;
//              while(j < dp[0].length)
//              {   
//                  if(j == 0){
//                      dp[i][j] = dp[i-1][j];
//                  }
//                  else{
//                      dp[i][j] = dp[i-1][j-1] || dp[i-1][j];
//                  }
                
//                  if(dp[i][j]){
//                      while(j < dp[0].length){
//                          dp[i][j] = true;
//                           j++;
//                      }
//                     }
//                  j++;
//                 }
                
//             }
            
//             else if(ch == '?'){
//                 for(int j = 1; j < dp[0].length;j++){
//                     dp[i][j] = dp[i-1][j-1];
//                 }
//             }
//             else{
//                 for(int j = 1; j < dp[0].length;j++){
//                     dp[i][j] = (p.charAt(i-1) == s.charAt(j-1)) && dp[i-1][j-1];
//                 }
//             }
//         }
        
//         return dp[pl][sl];
//     }
    
    //method 2
    public boolean isMatch(String s, String p){
        if(s.equals(p) || p.equals("*"))return true;
        
        int len1 = s.length();
        int len2 = p.length();
        
        //pointers
        int sp = 0;
        int pp = 0;
        int sStar = -1;
        int pStar = -1;
        
        while(sp < len1){
            
            if(pp < len2 && (p.charAt(pp) == '?' ||  p.charAt(pp) == s.charAt(sp))){
                //increment both
                sp++;pp++;
            }
            else if(pp < len2 && p.charAt(pp) == '*'){
                sStar = sp;
                pStar = pp;
                pp++;
            }
            
            //this means no char has matched at all not even * or ?
            else if(pStar == -1){
                return false;
            }
            
            //here we are beautifully matching chars with *
            else{
                sStar++;
                sp = sStar;
                pp = pStar+1;
            }
        }
        
        while(pp < len2){
            if(p.charAt(pp) != '*'){
                return false;
            }
            pp++;
        }
        return true;
    }
}