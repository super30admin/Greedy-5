//TC will be O(m * n)
//SC will be O(m * n)

class WildcardMatching {
    public boolean isMatch(String s, String p) {
        if(s.equals(p) || p.equals("*")){
            return true;
        }

        int m = p.length();
        int n = s.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        for(int i = 1; i < m + 1; i++){
            for(int j = 0; j < n + 1; j++){
                if(p.charAt(i - 1) != '*'){
                    if((j > 0) && (p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(i - 1)== '?')){
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
                else{
                    dp[i][j] = dp[i - 1][j];
                    if(j > 0){
                        dp[i][j] = dp[i][j] || dp[i][j - 1];
                    }
                }
            }
        }
        return dp[m][n];
    }


    public static void main(String[] args){
        WildcardMatching obj = new WildcardMatching();
        String s = "aa", p = "a";
        System.out.println(obj.isMatch(s,p));
    }
}