// Time Complexity : The time complexity is O(m*n) where m is the length of the s and n is the length of p
// Space Complexity : Te space complexity is O(m*n) where m is the length of the s and n is the length of p
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach

class Solution {
    public boolean isMatch(String s, String p) {

        if(s.equals(p) || p.equals("*")) return true;

        int pl = p.length();
        int sl = s.length();

        boolean[][] dp = new boolean[pl+1][sl+1];
        dp[0][0] = true;

        for(int i=1;i<=pl;i++){
            char ch = p.charAt(i-1);
            if(ch == '*'){
                dp[i][0] = dp[i-1][0];
            }
        }

        for(int i=1;i<=pl;i++){

            char ch = p.charAt(i-1);

            for(int j=1;j<=sl;j++){

                // if character is '*'
                if(ch == '*'){
                    if(dp[i][j-1]){
                        dp[i][j] = true;
                    }
                    else{
                        dp[i][j] = (dp[i-1][j] || dp[i-1][j-1]);
                    }
                }
                // if character is '?'
                else if(ch == '?'){
                    dp[i][j] = dp[i-1][j-1];
                }
                // if character is a-z
                else{
                    if(p.charAt(i-1) == s.charAt(j-1)){
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
            }
        }

        return dp[pl][sl];

    }
}