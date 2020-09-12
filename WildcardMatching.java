// Time Complexity : O(m+n) 
// Space Complexity : O(1) 

class Solution {
    public boolean isMatch(String s, String p) {
        if (s.equals(p) || p.equals("*")) return true;
        if (s.isEmpty() || p.isEmpty()) return false;
        int sLen = s.length(), pLen = p.length();
        int sPtr = 0, pPtr = 0, sStar = -1, pStar = -1;
        while (sPtr < sLen) {
            //Case 1
            if (pPtr < pLen && (s.charAt(sPtr) == p.charAt(pPtr) || p.charAt(pPtr) == '?')) {
                sPtr++;
                pPtr++;
            }
            //Case 2
            else if (pPtr < pLen && p.charAt(pPtr) == '*') {
                sStar = sPtr;
                pStar = pPtr;
                pPtr++;
            }
            //Case 3
            else if (pStar == -1) return false;
            //Case 4
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