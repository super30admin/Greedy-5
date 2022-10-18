//O(MxN) DP solution

class Solution {
    public boolean isMatch(String s, String p) {
        if (s.equals(p) || p.equals("*")) return true;
        int sl = s.length(); int pl = p.length();
        boolean [][] dp = new boolean[pl+1][sl+1];
        dp[0][0] = true;
        //filling 1st column, 1st row is by deafult false
        for(int i=1;i<=pl;i++){
            if (p.charAt(i-1) == '*'){
                dp[i][0] = dp[i-1][0];
            }
        }
        for(int i=1;i<=pl;i++){
            for(int j=1; j<= sl;j++){
                if (p.charAt(i-1) != '*'){
                    if (p.charAt(i-1) == s.charAt(j-1) || p.charAt(i-1) == '?'){
                        dp[i][j] = dp[i-1][j-1];
                    }
                }else {
                    //0 or 1 case
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }
            }
        }
        return dp[pl][sl];
     }
}
