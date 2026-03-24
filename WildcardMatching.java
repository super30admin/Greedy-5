// Time Complexity : O(s*log p)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
// We try to match characters of s and p , and in case of a * we always consider the zero case of no char
// we also attach a fallback pointer at * so when it fails at the some point - we fallback and add one exact char to the *
// the avg time complexity is slogp acc to some integrals of all test cases

class Solution {
    public boolean isMatch(String s, String p) {
        int sLength = s.length();
        int pLength = p.length();
        int sp = 0;
        int pp = 0;
        int sStar = -1;
        int pStar = -1;

        while (sp < sLength) {
            if (pp < pLength && (s.charAt(sp) == p.charAt(pp)
                    || p.charAt(pp) == '?')) {
                sp++;
                pp++;
            } else if (pp < pLength && p.charAt(pp) == '*') {
                pStar = pp;
                sStar = sp;
                pp++;
            } else if (pStar == -1) {
                return false;
            } else {
                sp = sStar + 1;
                sStar = sp;
                pp = pStar + 1;
            }
        }

        while (pp < pLength) {
            if (p.charAt(pp) != '*')
                return false;
            pp++;
        }

        return true;
    }
}