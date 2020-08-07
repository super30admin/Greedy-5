//time o(mn)
//space o(mn)
class Solution {
    public boolean isMatch(String s, String p) {
        if(s == null || p==null)
            return s==p;
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[n+1][m+1];
        dp[0][0] = true;
        
        int col = 0;
        for(int row =1; row<dp.length; row++) {
            if(p.charAt(row-1)=='*') {
                dp[row][col] = true;
            }
            else {
                break;
            }
        }
        
        for(int i=1; i<dp.length; i++) {
            for(int j=1; j<dp[0].length; j++) {
                char r = p.charAt(i-1);
                char c = s.charAt(j-1);
                if(r == '*') {
                    if(dp[i][j-1]) {
                        dp[i][j] = true;
                    }
                    else {
                        dp[i][j] = dp[i-1][j-1] || dp[i-1][j];
                    }
                }
                else if(r== '?'|| r == c) {
                    dp[i][j] = dp[i-1][j-1];
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}

//time avg case mlogn
//worst case o(mn)
//space o(1)
class Solution {
    public boolean isMatch(String s, String p) {
        if(s.equals(p) || p.equals("*")) {
            return true;
        }
        int m = s.length(), n = p.length();
        int sp = 0, pp = 0;
        int sstar = -1, pstar =-1;
        
        while(sp<m) {
            if(pp < n && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')) {
                sp++;
                pp++;
            }
            else if(pp<n && p.charAt(pp) == '*') {
                sstar = sp;
                pstar = pp;
                pp++;
            }
            else if(pstar == -1) {
                return false;
            }
            else {
                sp = sstar+1;
                pp = pstar+1;
                sstar = sp;
            }
        }
        
        while(pp<n) {
            if(p.charAt(pp) != '*') 
                return false;
            pp++;
        }
        return true;
    }
}