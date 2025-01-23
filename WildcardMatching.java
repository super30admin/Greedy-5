// Time Complexity O(m * n)
// space Complexity O(m * n)
public class WildcardMatching {
    public boolean isMatch(String s, String p){
        int m = s.length();
        int n = p.length();

        boolean [][] dp = new boolean [m + 1][n + 1];

       // empty string matches empty pattern
        dp[0][0] = true;
        // fill the first row for patterns starting with '*'
        for( int j =1; j <=n; j++){
            if(p.charAt(j - 1) == '*'){
                dp[0][j] = dp[0][j - 1];
            }
        }

        // fill the dp table
        for ( int i = 1; i <=m; i ++){
            for ( int j =1; j <= n;j++){
                char sChar = s.charAt(i - 1);
                char pChar = p.charAt(j - 1);

                if( pChar == sChar || pChar =='?') {
                    // characters match or ? matches any single character
                    dp[i][j] = dp[i - 1][j - 1];
                } else if( pChar == '*'){
                    dp[i][j] = dp[i][j-1] || dp[i-1][j];
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args){
        WildcardMatching solution = new WildcardMatching();
        System.out.println(solution.isMatch("adceb", "*a*b")); // Output: true
        System.out.println(solution.isMatch("aa", "a"));       // Output: false
        System.out.println(solution.isMatch("cb", "?a"));      // Output: false
        System.out.println(solution.isMatch("acdcb", "a*c?b")); // Output: false
    }
}
