
//Time Complexity: O(m*n)
//Space Complexity: O(n)

//we use dynamic programming to solve this problem. We maintain a 1D dp array where dp[j] indicates 
// whether the first i characters of s match the first j characters of p. 
// We iterate through each character of s and p, updating the dp array based on the current characters and previous states.
//if both characters match or p has '?', we take the diagonal value.
//since we are using a 1D dp array, we need to store the previous diagonal value in a separate variable (diagUp).
//if p has '*', we can either ignore it (dp[j-1]) or consider it (dp[j]) .
//Finally, dp[n] will tell us if the entire strings match.

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
