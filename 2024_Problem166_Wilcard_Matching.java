//44. Wildcard Matching - https://leetcode.com/problems/wildcard-matching/description/

//DP
//Time Complexity: O(m*n)
//Space Complexity: O(m*n)
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;

        for(int j=1; j<=n; j++){
            char pChar = p.charAt(j-1);
            if(pChar == '*'){
                dp[0][j] = dp[0][j-1];
            }
        }

        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                char pChar = p.charAt(j-1);
                if(pChar == '*'){
                    dp[i][j] = dp[i][j-1] || dp[i-1][j]; //choose || no choose case
                } else {
                    if(pChar == s.charAt(i-1) || pChar == '?'){ //character match
                        dp[i][j] = dp[i-1][j-1];
                    } else {
                        dp[i][j] = false;
                    }
                }
            }
        }
        return dp[m][n];

    }
}

//Greedy (2 pointer)
//Time Complexity: O(m*n)
//Space Complexity: O(1)
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        int pstar = -1, sstar = -1; //pointers to know how many characters are matching to 1 star
        int sp = 0, pp = 0; //pointers for traversing through string

        while (sp < m) {
            if (pp < n && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')) {
                sp++;
                pp++;
            } else if (pp < n && (p.charAt(pp) == '*')) {
                pstar = pp;
                sstar = sp;
                pp++;
            } else {
                if(pstar == -1) return false;
                sstar++;
                //reset positions of sp, pp
                sp = sstar;
                pp = pstar + 1;
            }
        }

        while(pp < n){
            if(p.charAt(pp) != '*'){
                return false;
            }
            pp++;
        }
        return true;
    }
}