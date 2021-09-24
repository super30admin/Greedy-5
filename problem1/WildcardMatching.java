// Time Complexity : O(nlog m), n -> Length of String s, m -> Length of pattern p
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
package problem1;

public class WildcardMatching {
	/********************* Dynamic Programming *********************/
	// Time Complexity : O(m*n), n -> Length of string s, m -> Length of pattern p
	// Space Complexity : O(m*n)
	// Did this code successfully run on Leetcode : Yes
	// Any problem you faced while coding this : No
	public boolean isMatchDP(String s, String p) {
		if (s.equals(p) || p.equals("*")) {
			return true;
		}

		int m = p.length();
		int n = s.length();

		boolean[][] dp = new boolean[m + 1][n + 1];
		dp[0][0] = true;

		for (int i = 1; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				// *
				if (p.charAt(i - 1) == '*') {
					dp[i][j] = dp[i - 1][j];
					if (j > 0) {
						dp[i][j] = dp[i][j] || dp[i][j - 1];
					}
				}
				// Not *
				else if (j > 0 && (p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(i - 1) == '?')) {
					dp[i][j] = dp[i - 1][j - 1];
				}
			}
		}

		return dp[m][n];
	}

	/********************* Two Pointers *********************/
	// Time Complexity : O(nlog m), n -> Length of String s, m -> Length of pattern
	// p
	// Space Complexity : O(1)
	// Did this code successfully run on Leetcode : Yes
	// Any problem you faced while coding this : No
	public boolean isMatch(String s, String p) {
		if (s.equals(p) || p.equals("*")) {
			return true;
		}

		int m = p.length();
		int n = s.length();

		int sMem = -1;
		int pMem = -1;
		int sp = 0;
		int pp = 0;

		while (sp < n) {
			if (pp < m && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')) {
				sp++;
				pp++;
			} else if (pp < m && p.charAt(pp) == '*') {
				sMem = sp;
				pMem = pp;
				pp++;
			} else if (pMem == -1) {
				return false;
			} else {
				sMem++;
				sp = sMem;
				pp = pMem + 1;
			}
		}

		while (pp < m) {
			if (p.charAt(pp) != '*') {
				return false;
			}
			pp++;
		}
		return true;
	}

	public static void main(String[] args) {
		WildcardMatching obj = new WildcardMatching();
		String s = "aa";
		String p = "a";

		System.out.println("Does the given string match the pattern? " + (obj.isMatch(s, p) ? "Yes" : "No"));
	}

}
