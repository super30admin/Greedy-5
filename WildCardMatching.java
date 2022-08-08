// time: O(S log p) avg case | O(SP) worst | O(Max(S,P)) best
// space: O(1)

// optimal soln (Non DP)
class Solution {
    public boolean isMatch(String s, String p) {
        int sl = s.length();
        int pl = p.length();
        int sp = 0;
        int pp = 0;
        int sStar = -1;
        int pStar = -1;
        while(sp < sl) {
            //when character matches
            if(pp < pl && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')) {
                sp++; pp++;
            } else if(pp < pl && (p.charAt(pp) == '*')) {
                // when * is encountered
                sStar = sp;
                pStar = pp;
                pp++;
            } else if(pStar == -1) return false;
            else {
                pp = pStar +1;
                sp = sStar +1;
                sStar = sp;
            }
        }
        while(pp < pl) {
            if(p.charAt(pp) != '*') return false;
            pp++;
        }
        return true;
    }
}

// DP 1 - with Single Dimensional Array
// Time: O(SP) | Space: O(S)

// when p s character match, take value from diagonal up
// when p character is *, take 0 1 case
class Solution {
    public boolean isMatch(String s, String p) {
        int sl = s.length();
        int pl = p.length();
        boolean[] dp = new boolean[sl+1];
        dp[0] = true;
        for(int i=1;i<=pl;i++) {
            boolean diagonalUp = dp[0];
            for(int j=0;j<=sl;j++) {
                boolean temp = dp[j];
                if(p.charAt(i-1) != '*') {
                    if(j>0) {
                        if(s.charAt(j-1) == p.charAt(i-1) || p.charAt(i-1)=='?') {                      dp[j] = diagonalUp;
                        }else dp[j] = false;
                    } else dp[j] = false;
                } else {
                    if(j>0) {
                        dp[j] = dp[j] || dp[j-1];
                    }
                }
                diagonalUp = temp;
            }
        }
        return dp[sl];
    }
}

// DP 2 - with Two Dimensional Array
// Time: O(SP) | Space: O(SP)

class Solution {
    public boolean isMatch(String s, String p) {
        int sl = s.length();
        int pl = p.length();
        boolean[][] dp = new boolean[pl+1][sl+1];
        dp[0][0] = true;
        for(int i=1;i<=pl;i++) {
            for(int j=0;j<=sl;j++) {
                if(p.charAt(i-1) != '*') {
                    if(j>0) {
                        if(s.charAt(j-1) == p.charAt(i-1) || p.charAt(i-1)=='?') {                      dp[i][j] = dp[i-1][j-1];
                        }}
                } else {
                    dp[i][j] = dp[i-1][j];
                    if(j>0) {
                        dp[i][j] = dp[i][j] || dp[i][j-1];
                    }
                }
            }
        }
        return dp[pl][sl];
    }
}

