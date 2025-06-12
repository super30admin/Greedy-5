// Time complexity: O(m * n)
// Space Complexity: O(n)
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[] dp = new boolean[n+1];
        dp[0] = true;

        for(int j=1; j<=n; j++){
            char pChar = p.charAt(j-1);
            if(pChar == '*'){
                dp[j] = dp[j-1];
            }
        }

        for(int i=1; i<=m; i++){
            boolean diagUp = dp[0];
            dp[0] = false;
            for(int j=1; j<=n; j++){
                boolean temp = dp[j];
                char pChar = p.charAt(j-1);
                if(pChar == '*'){
                    dp[j] = dp[j-1] || dp[j];
                }else{
                    if(pChar == s.charAt(i-1) || pChar == '?'){
                        dp[j] = diagUp;  // diagUp;
                    }else{
                        dp[j] = false;
                    }
                }
                diagUp = temp;
            }
        }

        return dp[n];
    }
}
