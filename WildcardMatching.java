public class WildcardMatching {
    public boolean isMatch(String s, String p) {
        // return isMatchTwoDimensionalDP(s, p);
        // return isMatchOneDimensionalDP(s, p);
        return isMatchTwoPointers(s, p);
    }

    // TC: O(S * P) where S is length of S and P is length of P
    // SC: O(1)
    private boolean isMatchTwoPointers(String s, String p) {
        int sPointer = 0, pPointer = 0;
        int sStar = -1, pStar = -1;
        while (sPointer < s.length()) {
            if (pPointer < p.length() && ((s.charAt(sPointer) == p.charAt(pPointer)) || p.charAt(pPointer) == '?')) {
                sPointer++;
                pPointer++;
            } else if (pPointer < p.length() && p.charAt(pPointer) == '*') {
                pStar = pPointer;
                sStar = sPointer;
                pPointer++;
            } else if (pStar == -1) {
                return false;
            } else {
                sStar++;
                sPointer = sStar;
                pPointer = pStar + 1;
            }
        }
        while (pPointer < p.length()) {
            if (p.charAt(pPointer) != '*') {
                return false;
            }
            pPointer++;
        }
        return true;
    }

    // TC: O(S * P) where S is length of S and P is length of P
    // SC: O(P) where P is length of P
    private boolean isMatchOneDimensionalDP(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[j] = dp[j - 1];
            }
        }
        for (int i = 1; i <= m; i++) {
            boolean diagUp = false;
            if (i == 1) {
                diagUp = true;
                dp[0] = false;
            }
            for (int j = 1; j <= n; j++) {
                boolean temp = dp[j];
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[j] = diagUp;
                } else if (p.charAt(j - 1) == '*') {
                    dp[j] = dp[j] || dp[j - 1];
                } else {
                    dp[j] = false;
                }
                diagUp = temp;
            }
        }
        return dp[n];
    }

    // TC: O(S * P) where S is length of S and P is length of P
    // SC: O(S * P) where S is length of S and P is length of P
    private boolean isMatchTwoDimensionalDP(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        return dp[m][n];
    }
}
