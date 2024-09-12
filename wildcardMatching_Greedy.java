// Time Complexity : O(m+n) where m and n are lengths of strings s and p respectively
// Space Complexity : O(1) 
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
// Greedy Solution

class wildcardMatching_Greedy {
    public boolean isMatch(String s, String p) {
        if (s.equals(p) || p.equals("*")) return true;
        if (s.isEmpty() || p.isEmpty()) return false;
        int sLen = s.length(), pLen = p.length();
        int sPtr = 0, pPtr = 0, sStar = -1, pStar = -1;
        while (sPtr < sLen) {
            //case1: if same char in both, move both ptr fwd
            if (pPtr < pLen && (s.charAt(sPtr) == p.charAt(pPtr) || p.charAt(pPtr) == '?')) {
                sPtr++;
                pPtr++;
            }
            //case2: using * as empty sequence. So sPtr stays at place but pPtr moves fwd
            else if (pPtr < pLen && p.charAt(pPtr) == '*') {
                sStar = sPtr;
                pStar = pPtr;
                pPtr++;
            }
            //case3: no matching char neither a *, so return false
            else if (pStar == -1) return false;
            //case4: taking empty for * didn't work. So backtrack to pStar, sStar and take >= 1 chars for *
            else {
                sPtr = sStar + 1;
                pPtr = pStar + 1;
                sStar = sPtr;
            }
        }
        while (pPtr < pLen) {
            if (p.charAt(pPtr) != '*') return false;
            pPtr++;
        }
        return true;
    }
}