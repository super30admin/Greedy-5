// Time Complexity : O(S * P)
// Space Complexity : O(S * P)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

/**
 * use Dp. Start iterating from 0th index of s and p. check if both are equal.
 * or if the pattern current index is ?, if so, iterate next index of each
 * string. Else if current index of p is *, then three possibilities are
 * possible. 1. update both s and p indices. 2. Update s index. 3. Update p
 * index. Else return false. when both strings meet end index, return true. Else
 * if s reached end, then check remaining characters of p are *. If not return
 * false. If either of them reach end but not the other, then also false.
 *
 */
class Solution {
	public boolean isMatch(String s, String p) {
		int m = s.length();
		int n = p.length();
		Boolean[][] dp = new Boolean[m + 1][n + 1];
		return helper(s, p, dp, 0, 0);
	}

	public boolean helper(String s, String p, Boolean[][] dp, int m, int n) {
		if (m == s.length() && n == p.length())
			return true;
		if (m == s.length()) {
			for (int i = n; i < p.length(); i++) {
				if (p.charAt(i) != '*')
					return false;
			}
			return true;
		}
		if (m == s.length() || n == p.length())
			return false;

		if (dp[m][n] != null)
			return dp[m][n];

		if (s.charAt(m) == p.charAt(n) || p.charAt(n) == '?')
			return dp[m][n] = helper(s, p, dp, m + 1, n + 1);
		if (p.charAt(n) == '*')
			return dp[m][n] = helper(s, p, dp, m + 1, n + 1) || helper(s, p, dp, m + 1, n)
					|| helper(s, p, dp, m, n + 1);
		return dp[m][n] = false;
	}
}
