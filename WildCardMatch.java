package com.example.problems;

//Time Complexity : O(M*N) for DP Approach
//Space Complexity : O(M*N) DP Approach
//Did this code successfully run on Leetcode : Yes
//Any problem you faced while coding this : No
/* 
 * Approach-1 DP Approach
 * Here is the algorithm.
 * 1.Clean up the input by replacing more than one star in a row by a single star
 * 
If (s, p) is already known and stored in dp, return the value.

If the strings are equal p == s, or the pattern matches whatever string p == '*', add dp[(s, p)] = True.

Else if p is empty, or s is empty, add dp[(s, p)] = False.

Else if the current characters match p[0] == s[0] or p[0] == '?', then compare the next ones and add dp[(s, p)] = helper(s[1:], p[1:]).

Else if the current pattern character is a star p[0] == '*', then there are two possible situations: the star matches no characters, and the star matches one or more characters. dp[(s, p)] = helper(s, p[1:]) or helper(s, p[1:]).

Else p[0] != s[0], add dp[(s, p)] = False.

Now that the value is computed, return it dp[(s, p)]


Approach -2 BackTracking



    Let's use two pointers here: s_idx to iterate over the string, and p_idx to iterate over the pattern. While s_idx < s_len:

        If there are still characters in the pattern p_idx < p_len and the characters under the pointers match p[p_idx] == s[s_idx] or p[p_idx] == '?', then move forward by increasing both pointers.

        Else if there are still characters in the pattern p_idx < p_len, and p[p_idx] == '*', then first check "match zero characters" situation, i.e. increase only pattern pointer p_idx++. Write down for a possible backtrack the star position in star_idx variable, and the current string pointer in s_tmp_idx variable.

        Else if there is "no match" situation: the pattern is used up p_idx < p_len or the characters under the pointers doesn't match.

            If there was no stars in the pattern, i.e. no star_idx, return False.

            If there was a star, then backtrack: set pattern pointer just after the last star p_idx = star_idx + 1, and string pointer s_idx = s_tmp_idx + 1, i.e. assume that this time the star matches one more character. Save the current string pointer for the possible backtrack s_tmp_idx = s_idx.

    Return True if all remaining characters in the pattern are stars.

 * 
 * */
public class WildCardMatch {
	public boolean isMatch(String s, String p) {
		char str[] = s.toCharArray();
		char pattern[] = p.toCharArray();

		// replace multiple * with one *
		// e.g a**b***c --> a*b*c
		int writeIndex = 0;
		boolean isFirst = true;
		for (int i = 0; i < pattern.length; i++) {
			if (pattern[i] == '*') {
				if (isFirst) {
					pattern[writeIndex++] = pattern[i];
					isFirst = false;
				}
			} else {
				pattern[writeIndex++] = pattern[i];
				isFirst = true;
			}
		}
		boolean dp[][] = new boolean[str.length + 1][writeIndex + 1];
		if (writeIndex > 0 && pattern[0] == '*') {
			dp[0][1] = true;
		}
		dp[0][0] = true;
		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {
				if (pattern[j - 1] == '?' || pattern[j - 1] == str[i - 1]) {
					dp[i][j] = dp[i - 1][j - 1];
				} else if (pattern[j - 1] == '*') {
					dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
				}
			}
		}
		return dp[str.length][writeIndex];
	}

	public boolean isMatchBackTracking(String s, String p) {
		if (s.equals(p) || p.equals("*"))
			return true;
		if (s.isEmpty() || p.isEmpty())
			return false;
		int i = 0, j = 0, sPointer = -1, pPointer = -1;
		int size1 = s.length(), size2 = p.length();

		while (i < size1) {
			if (j < size2 && (p.charAt(j) == '?' || s.charAt(i) == p.charAt(j))) {
				++i;
				++j;
			} else if (j < size2 && p.charAt(j) == '*') {
				sPointer = i;
				pPointer = j;
				++j;
			} else if (pPointer == -1) {
				return false;
			} else {
				i = sPointer + 1;
				j = pPointer + 1;
				sPointer = i;
			}
		}
		for (i = j; i < size2; i++) {
			if (p.charAt(i) != '*') {
				return false;
			}
		}
		return true;
	}

	public static void main(String args[]) {
		String s = "aa";
		String p = "a";

		WildCardMatch match = new WildCardMatch();
		System.out.print(match.isMatch(s, p));
		System.out.print(match.isMatchBackTracking(s, p));

	}
}
