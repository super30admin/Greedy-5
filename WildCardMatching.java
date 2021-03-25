// Using 4 pointers
//TC: O(M*N), M: length of string s, N, length of pattern
//SC: O(1)
//Did it run successfully on Leetcode? : Yes
class Solution {
    public boolean isMatch(String s, String p) {
        if (s.equals(p) || p.equals('*'))
            return true;
        int sLen = s.length();
        int pLen = p.length();
        int sPtr = 0;
        int pPtr = 0;
        int sStar = -1;
        int pStar = -1;
        while (sPtr < sLen){
            //case1
            if (pPtr < pLen && (s.charAt(sPtr) == p.charAt(pPtr) || p.charAt(pPtr) == '?')){
                sPtr++;
                pPtr++;
            }
            //case 2 ..if char is '*'
            else if (pPtr < pLen && p.charAt(pPtr) == '*'){
                sStar = sPtr;
                pStar = pPtr;
                pPtr++;
            }
            //case 3  -> chars dont match and no star encountered before
            else if (pStar == -1){
                return false;
            } 
            //case 4 -> chars dont match but there is a star encountered before. So explore the solution by matching the char with star
            else {
                sStar++;
                sPtr = sStar;
                pPtr = pStar + 1;
            }    
        }
        while (pPtr < pLen){
            if (p.charAt(pPtr) != '*'){
              return false;
            }
            pPtr++;
        }
       return true;
    }
}
// DP bottom-up
//TC: O(M*N), M: length of string s, N, length of pattern
//SC: O(M*N)
//Did it run successfully on Leetcode? : Yes
// class Solution {
//     public boolean isMatch(String s, String p) {
//         if (s.equals(p) || p.equals('*'))
//             return true;
//         int sLen = s.length();
//         int pLen = p.length();
//         boolean dp[][] = new boolean[pLen+1][sLen+1];
//         dp[0][0] = true; //  empty string matches empty string 
//         for ( int i = 1; i < dp.length; i++){
//             char c = p.charAt(i-1);
//             if (c == '*'){
//                 // 2 cases
//                 //first case
//                 int j = 0;
//                 while (j < dp[0].length) {
//                   if (j == 0){ // only one case - copy value from above
//                      dp[i][j] = dp[i-1][j];
//                   }
//                   else{
//                       // if its not a first column, then 
//                       dp[i][j] =  dp[i-1][j-1] || dp[i-1][j];
//                   }
//                   if (dp[i][j]){
//                       while (j < dp[0].length) {
//                           dp[i][j] = true;
//                           j++;
//                       }
//                   }
//                  j++;
//                 }
//             } else if (c == '?'){
//                 for (int j = 1; j < dp[0].length; j++){
//                       dp[i][j] =  dp[i-1][j-1];
//                  }
//             } else {
//                 for (int j = 1; j < dp[0].length; j++){
//                     if (c == s.charAt(j-1)){
//                         dp[i][j] =  dp[i-1][j-1];
//                     }
//                 } 
//             }
//         }
//         return dp[dp.length-1][dp[0].length-1];
//      }
// }
