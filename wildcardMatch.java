// Time Complexity : O( min(sl, pl) )
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// two pointer
class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p) || p.equals('*')) return true;
        int pl = p.length(); int sl = s.length();
        int sp = 0; int pp = 0;
        int sStar = -1; int pStar = -1;
        
        while(sp < sl){
            if(pp < pl && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')){
                sp++; pp++;
            } else if(pp < pl && p.charAt(pp) == '*'){
                pStar = pp;
                sStar = sp;
                pp++;
            } else if(pStar == -1){
                return false;
            } else{
                sp = sStar + 1;
                sStar = sp;
                pp = pStar + 1;
            }
        }
        
        while(pp < pl){
            if(p.charAt(pp) != '*')
                return false;
            pp++;
        }
        return true;
    }
}

// ***************************************

// Time Complexity : O(m x n)
// Space Complexity : O(m x n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// dp
class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p) || p.equals('*')) return true;
        int m = p.length(); int n = s.length();
        boolean[][] dp = new boolean[m+1][n+1];
        
        dp[0][0] = true;
        for(int i=1; i<dp.length; i++){
            for(int j=0; j<dp[0].length; j++){
                char c = p.charAt(i-1);
                if(c != '*'){
                    if(j>0 && (c == s.charAt(j-1) || c == '?')){
                        dp[i][j] = dp[i-1][j-1];
                    }
                } else{
                    if(j>0)
                        dp[i][j] = dp[i-1][j] || dp[i][j-1];
                    else
                        dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[m][n];
    }
}