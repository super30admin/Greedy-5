import java.util.ArrayList;
import java.util.List;

// Time Complexity : O(mn) where m = number of workers, n = number of bikes
// Space Complexity : O(m) + O(n) where m = number of workers, n = number of bikes
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
//44. Wildcard Matching (Hard) - https://leetcode.com/problems/wildcard-matching/
// Time Complexity : O(m*n) where m = length of source string, n = length of pattern string
// Space Complexity : O(m*n) where m = length of source string, n = length of pattern string
//class Solution {
//	public boolean isMatch(String s, String p) {
//		if (s.equals(p) || p.equals("*"))
//			return true;
//
//		int sl = s.length(), pl = p.length();
//		boolean[][] dp = new boolean[pl + 1][sl + 1];
//		dp[0][0] = true;
//
//		for (int i = 1; i <= pl; i++) {
//			if (p.charAt(i - 1) == '*') {
//				dp[i][0] = dp[i - 1][0];
//			}
//		}
//
//		for (int i = 1; i <= pl; i++) {
//			for (int j = 1; j <= sl; j++) {
//				if (p.charAt(i - 1) != '*') {
//					if (p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(i - 1) == '?') {
//						dp[i][j] = dp[i - 1][j - 1];
//					}
//				} else {
//					dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
//				}
//			}
//		}
//
//		return dp[pl][sl];
//	}
//}

// Time Complexity : O(m*log(n)) where m = length of source string, n = length of pattern string
// Space Complexity : O(1)
class Solution {
	public boolean isMatch(String s, String p) {
		if (s.equals(p) || p.equals("*"))
			return true;

		int sl = s.length(), pl = p.length();
		int sp = 0, pp = 0;
		int sStar = -1, pStar = -1;

		while (sp < sl) {
			if (pp < pl && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')) {
				sp++;
				pp++;
			} else if (pp < pl && p.charAt(pp) == '*') {
				sStar = sp;
				pStar = pp;
				pp++;
			} else if (pStar == -1) {
				return false;
			} else {
				sp = sStar + 1;
				sStar = sp;
				pp = pStar + 1;
			}
		}

		while (pp < pl) {
			if (p.charAt(pp) != '*') {
				return false;
			}
			pp++;
		}

		return true;
	}
}